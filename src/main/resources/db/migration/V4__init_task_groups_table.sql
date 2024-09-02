drop table if exists task_group;
create table task_group
(
    id          int primary key auto_increment,
    description varchar(255) not null,
    done        bit
);
alter table task add column task_group_id int null;
alter table task add foreign key (task_group_id) references task_group(id);