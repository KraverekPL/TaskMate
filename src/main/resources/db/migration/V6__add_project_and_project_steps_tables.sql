drop table if exists project;
drop table if exists project_steps;

create table project
(
    id          integer AUTO_INCREMENT PRIMARY KEY,
    description varchar(255) not null
);

create table project_steps
(
    id               integer AUTO_INCREMENT PRIMARY KEY,
    description      varchar(255) not null,
    project_id       integer      not null,
    days_to_deadline integer      not null,
    foreign key (project_id) references project (id)
);

alter table task_group
    add column project_id int null;
alter table task_group
    add foreign key (project_id) references project (id);
