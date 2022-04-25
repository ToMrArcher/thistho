insert into users
values(nextval('users_user_id_seq'), 'admin@admin.com', '$2a$12$KtEIbNMhIQKIsjHi0Vd9LO6YJ0vKW7P.0p6JE31e5jBoaiMIGDBkW', now(), true);

insert into authorities
values(nextval('authorities_authority_id_seq'), 'USER');

insert into authorities
values(nextval('authorities_authority_id_seq'), 'ADMIN');

insert into users_authorities
values (1, 1);

insert into users_authorities
values (1, 2);