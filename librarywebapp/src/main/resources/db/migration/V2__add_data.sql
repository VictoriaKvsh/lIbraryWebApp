insert into user_table (email, first_name, last_name, role, id) values ('vika@vika.vika', 'Vika', 'Kyvshinova', 0, 1);
insert into user_table (email, first_name, last_name, role, id) values ('katy@katy.katy', 'Katy', 'Sanders', 0, 2);
insert into user_table (email, first_name, last_name, role, id) values ('lib@lib.lib', 'Librarian', 'Librarian', 1, 3);
insert into user_table (email, first_name, last_name, role, id) values ('admin@admin.admin', 'Admin', 'Admin', 2, 4);

insert into user_credentials (active, creation_date, password, id) values ('true', '2021-01-01 20:25:31.198', '$2a$10$ToMa3y.EcKsFSSeyzJMRFeXJf1flgL2T.fj.iNU5tvWpTZE.bIt92', 1);
insert into user_credentials (active, creation_date, password, id) values ('true', '2021-01-01 20:25:31.198', '$2a$10$ToMa3y.EcKsFSSeyzJMRFeXJf1flgL2T.fj.iNU5tvWpTZE.bIt92', 2);
insert into user_credentials (active, creation_date, password, id) values ('true', '2021-01-01 20:25:31.198', '$2a$10$ToMa3y.EcKsFSSeyzJMRFeXJf1flgL2T.fj.iNU5tvWpTZE.bIt92', 3);
insert into user_credentials (active, creation_date, password, id) values ('true', '2021-01-01 20:25:31.198', '$2a$10$ToMa3y.EcKsFSSeyzJMRFeXJf1flgL2T.fj.iNU5tvWpTZE.bIt92', 4);


insert into cred_user (u1, u2) values (1, 1);
insert into cred_user (u1, u2) values (2, 2);
insert into cred_user (u1, u2) values (3, 3);
insert into cred_user (u1, u2) values (4, 4);


insert into book_discription_table (autor, title, year, id) values ('Pyshkin', 'Starick', '1988', 10);
insert into book_discription_table (autor, title, year, id) values ('Bylgakov', 'Master', '1987', 11);
insert into book_discription_table (autor, title, year, id) values ('Mark Twain', 'Adventures of Huckleberry Finn', '1987', 12);
insert into book_discription_table (autor, title, year, id) values ('Arthur Conan Doyle', 'he Adventures of Sherlock Holmes', '1987', 13);
insert into book_discription_table (autor, title, year, id) values ('Paulo Coelho', 'The Alchemist', '1987', 14);
insert into book_discription_table (autor, title, year, id) values ('Jorge Luis Borges', 'The Aleph and Other Stories', '1987', 15);
insert into book_discription_table (autor, title, year, id) values ('George Orwell', 'Animal Farm', '1987', 16);
insert into book_discription_table (autor, title, year, id) values ('Aesop', 'Aesops Fables', '1987', 17);
insert into book_discription_table (autor, title, year, id) values ('Lewis Carroll', 'Alices Adventures in Wonderland', '1987', 18);
insert into book_discription_table (autor, title, year, id) values ('Leo Tolstoy', 'Anna Karenina', '1987', 19);
insert into book_discription_table (autor, title, year, id) values ('William Faulkner', 'As I Lay Dying', '1987', 20);
insert into book_discription_table (autor, title, year, id) values ('Toni Morrison', 'Beloved', '1987', 21);
insert into book_discription_table (autor, title, year, id) values ('Markus Zusak', 'The Book Thief ', '1987', 22);
insert into book_discription_table (autor, title, year, id) values ('Aldous Huxley', 'Brave New World', '1987', 23);
insert into catalog_table (status, book_discription_id, id) values ('3', '10', 25);
insert into catalog_table (status, book_discription_id, id) values ('3', '11', 26);
insert into catalog_table (status, book_discription_id, id) values ('3', '12', 27);
insert into catalog_table (status, book_discription_id, id) values ('3', '13', 28);
insert into catalog_table (status, book_discription_id, id) values ('3', '14', 29);
insert into catalog_table (status, book_discription_id, id) values ('3', '15', 30);
insert into catalog_table (status, book_discription_id, id) values ('3', '16', 31);
insert into catalog_table (status, book_discription_id, id) values ('3', '17', 32);
insert into catalog_table (status, book_discription_id, id) values ('3', '18', 33);

ALTER SEQUENCE hibernate_sequence RESTART WITH 100; 


