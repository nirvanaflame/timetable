drop database if exists pna6_11;
create database pna6_11;

use pna6_11;

drop table if exists movie_actor;
drop table if exists movie;
drop table if exists actor;
drop table if exists producer;

create table actor
(
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    id         int auto_increment
        primary key
);

create table producer
(
    first_name varchar(20) not null,
    last_name  varchar(20) not null,
    id         int auto_increment
        primary key
);

create table movie
(
    id          int auto_increment
        primary key,
    name        varchar(40) not null,
    producer_id int         not null,
    constraint foreign_key_name
        foreign key (producer_id) references producer (id)
            on delete cascade
);

create table movie_actor
(
    id       int auto_increment
        primary key,
    movie_id int not null,
    actor_id int not null,
    constraint movie_actor_actor_null_fk
        foreign key (actor_id) references actor (id)
            on delete cascade,
    constraint movie_actor_movie_null_fk
        foreign key (movie_id) references movie (id)
            on delete cascade
);


