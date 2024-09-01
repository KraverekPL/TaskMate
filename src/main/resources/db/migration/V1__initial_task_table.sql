drop table if exists task;
create table task(
    id int primary key auto_increment,
    name varchar(255) not null,
    description varchar(255) not null,
    done bit
)