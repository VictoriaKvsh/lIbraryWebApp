create sequence hibernate_sequence start 1 increment 1;

    create table book_discription_table (
       id int4 not null,
        autor varchar(255) not null,
        title varchar(255) not null,
        year int4,
        primary key (id)
    );

    create table catalog_table (
       id int4 not null,
        status int4 not null,
        book_discription_id int4,
        primary key (id)
    );

    create table cred_user (
       u2 int4,
        u1 int4 not null,
        primary key (u1)
    );

    create table readers_book_table (
       id int4 not null,
        date timestamp,
        due_date timestamp,
        catalog int4,
        user_id int4,
        primary key (id)
    );

    create table user_table (
       id int4 not null,
        address varchar(255),
        email varchar(255) not null,
        first_name varchar(20),
        last_name varchar(255),
        phone_number varchar(13),
        role int4 not null,
        user_request_token varchar(255),
        primary key (id)
    );

    create table user_credentials (
       id int4 not null,
        active boolean,
        creation_date timestamp,
        password varchar(255),
        primary key (id)
    );

    create table user_picture (
       id int4 not null,
        file_location varchar(255),
        file_name varchar(255),
        user_id int4,
        primary key (id)
    );

    alter table cred_user 
       add constraint UK_9yjjp6j76ypudwl83561liitn unique (u2);

    alter table user_table 
       add constraint UK_eamk4l51hm6yqb8xw37i23kb5 unique (email);

    alter table catalog_table 
       add constraint FK6yh7h30wch3b4ge1xda47rrd9 
       foreign key (book_discription_id) 
       references book_discription_table;

    alter table cred_user 
       add constraint FKeie26o4ltb4mxfmmtxq89yrm7 
       foreign key (u2) 
       references user_credentials;

    alter table cred_user 
       add constraint FKdel6ls6ur3ublwtromhc11x1e 
       foreign key (u1) 
       references user_table;

    alter table readers_book_table 
       add constraint FKe1ffwbcrmspax6u2wb14rn4i3 
       foreign key (catalog) 
       references catalog_table;

    alter table readers_book_table 
       add constraint FKib2ohepq5nk2m40ux47t3wf4f 
       foreign key (user_id) 
       references user_table;

    alter table user_picture 
       add constraint FKbhkea3rh5bk0k6qo3s0ni5dey 
       foreign key (user_id) 
       references user_table;
       
