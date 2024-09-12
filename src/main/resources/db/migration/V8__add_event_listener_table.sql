drop table if exists task_events;
create table task_events(
    id int PRIMARY KEY AUTO_INCREMENT,
    task_id int,
    occurrence datetime,
    task_name varchar(255)
)