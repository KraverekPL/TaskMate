drop table if exists users;
CREATE TABLE users
(
    id         int primary key auto_increment,
    full_name  varchar(255) not null,
    email      varchar(255) not null,
    password   varchar(255) not null,
    created_on datetime null,
    updated_on datetime null
)
