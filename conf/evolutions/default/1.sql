# --- !Ups

create table contacts (
    id  bigint not null auto_increment,
    name varchar(255),
    email varchar(255),
    primary key (id)
);

# --- !Downs

drop table if exist contacts;