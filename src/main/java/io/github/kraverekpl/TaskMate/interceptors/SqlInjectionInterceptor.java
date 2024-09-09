package io.github.kraverekpl.TaskMate.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;
import java.util.regex.Pattern;

@Component
public class SqlInjectionInterceptor implements HandlerInterceptor {

    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
            "(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|" +
                    "(\\b(select|union|insert|delete|update|drop|alter|create|truncate)\\b)",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
        if (HttpMethod.POST.matches(request.getMethod()) || HttpMethod.PUT.matches(request.getMethod())) {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String paramName = parameterNames.nextElement();
                String paramValue = request.getParameter(paramName);
                if (SQL_INJECTION_PATTERN.matcher(paramValue).find()) {
                    final String msg = "SQL Injection attempt detected!";
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
                    return false;
                }
            }
        }
        return true;
    }
}
