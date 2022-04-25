create table users(
    user_id bigserial primary key,
    user_email varchar(50),
    user_password varchar(100),
    user_created timestamp,
    user_enabled bool

);

create table authorities(
    authority_id bigserial primary key,
    authority_name varchar(30)
);

create table users_authorities(
    user_id bigint,
    authority_id bigint
);