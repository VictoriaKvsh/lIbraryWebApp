insert into user_table (email, first_name, last_name, role, id) values ('v1k4a@yandex.by', 'Vika', 'Kvsh', 0, 1);
insert into user_table (email, first_name, last_name, role, id) values ('admin@admin.admin', 'admin', 'admin', 2, 2);

insert into user_credentials (active, creation_date, password, id) values ('true', '2021-01-26 20:25:31.198', '$2a$10$ToMa3y.EcKsFSSeyzJMRFeXJf1flgL2T.fj.iNU5tvWpTZE.bIt92', 1);
insert into user_credentials (active, creation_date, password, id) values ('true', '2021-01-26 20:25:31.198', '$2a$10$ToMa3y.EcKsFSSeyzJMRFeXJf1flgL2T.fj.iNU5tvWpTZE.bIt92', 2);


insert into cred_user (u1, u2) values (1, 1);
insert into cred_user (u1, u2) values (2, 2);



insert into book_discription_table (autor, title, year, id) values ('Pyshkin', 'Starick', '1988', 3);
insert into book_discription_table (autor, title, year, id) values ('Bylgakov', 'Master', '1987', 4);

ALTER SEQUENCE hibernate_sequence RESTART WITH 5; 


