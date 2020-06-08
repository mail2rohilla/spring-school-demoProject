-- drop database hb_student_tracker;
-- create database hb_student_tracker;
-- use hb_student_tracker;


create table school(
	id varchar(128) PRIMARY KEY,
    schoolName varchar(128) not null,
    demo text,
    meta varchar(1000)
    );
    
    
    create table student(
	id varchar(128) PRIMARY KEY,
    firstName varchar(128) not null,
    lastName varchar(128) not null,    
    email varchar(128) not null,
    school varchar(128),
    meta varchar(1000),
	constraint foreign key (school) references school (id)
    );
    
    
    
--     insert into student (firstName, lastName, email, school) values ("himanshu", "rohilla", "asfa@afa.com", 1);
-- 	insert into school (schoolName) values ("AIR FORCE SCHOOL");


create table user(
	id varchar(128),
    firstName varchar(128) not null,
    lastName varchar(128) not null,
    middleName varchar(64),
    email varchar(128) not null,
    phoneNumber varchar(32) not null,
    type varchar(32) not null,
    accountExpired boolean  not null default false,
    passwordRetriesLeft TINYINT default 3,
    username varchar(256) not null,
    password varchar(256) not null,
    passwordExpired boolean not null default false,
    status varchar(128),
    meta text,
    primary key (id),
    constraint unique_email unique (email),
    constraint unique_phoneNumber unique (phoneNumber),
    constraint unique_username unique (username)
);


-- a questionnaire can only be deleted if no responses have been submitted for it--;

create table questionnaire(
	id varchar(128),
    name varchar(1000) not null,
    subject varchar(128),
    type varchar(128),
    identifier varchar(64),
    description varchar(4096),
    author varchar(128) not null,
    meta text,
    totalScore varchar (32),
    unique (name, author, subject),
    primary key (id),
    constraint `autor_questionnaire_fk` foreign key (`author`)
    references `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

create table response(
	id varchar(128),
    performer varchar(128) not null,
    questionnaire varchar(128) not null,
    description varchar(4096),
    score varchar(32),
    primary key (id),
    constraint `performer_fk` foreign key (`performer`)
    references `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    constraint `questionnaire_response_fk` foreign key (`questionnaire`)
    references `questionnaire` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);


create table options(
	id varchar(128),
    text varchar(1024) not null,
--     type varchar(1024) not null,
    description varchar(1024) not null,
    meta varchar(4096) not null,
    primary key (id),
    unique (text)
);

create table answer(
	id varchar(128),
    text varchar(1024) not null,
    `option` varchar(128) not null,
    description varchar(1024) not null,
    primary key (id),
	unique (`option`),
	constraint `option_answer_fk` foreign key (`option`)
    references `options` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
    );
    
create table question(

	id varchar(128),
    `text` varchar(2048) not null,
    questionnaire varchar(128) not null,
    `type` varchar(128) not null,
    sequenceNumber int not null default 0,
    status varchar(256),
    required boolean default false,
    hint varchar(1024) ,
    repeats int default 0,
    description varchar(256),
    author varchar(128),
    answer varchar(128) not null,
    meta text,
    primary key (id),
    constraint `author_question_fk` foreign key (`author`)
    references `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    constraint `questionnaire_question_fk` foreign key (`questionnaire`)
    references `questionnaire` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    constraint `answer_question_fk` foreign key (`answer`)
    references `answer` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    unique (questionnaire, `text`,author)
);

create table responseAnswer(
	response varchar(128) not null,
    question varchar(128)  not null,
    optionSelected varchar(128) not null,
    meta text,
    constraint `response_responseAnswer_fk` foreign key (`response`)
    references `response` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    constraint `question_responseAnswer_fk` foreign key (`question`)
    references `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    constraint `optionSelected_responseAnswer_fk` foreign key (`optionSelected`)
    references `options` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    unique (response, question)
);



create table question_option_relation(
	option_id varchar(128),
    question_id varchar(128),
    constraint `option_question_option_relation_fk` foreign key (`option_id`)
    references `options` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
    constraint `question_question_option_relation_fk` foreign key (`question_id`)
    references `question` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- hard coded id's stored based on the categoryType 
create table bookmark(
	category_id varchar(128) not null,
    categoryType varchar(256)not null,
    author varchar(128) not null,
    constraint `author_bookmark_fk` foreign key (`author`)
    references `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);



select * from  user;

select * from  questionnaire;

select user0_.id as id1_3_0_, user0_.accountExpired as accountE2_3_0_, user0_.email as email3_3_0_, user0_.firstName as firstNam4_3_0_, user0_.lastName as lastName5_3_0_, user0_.meta as meta6_3_0_, user0_.middleName as middleNa7_3_0_, user0_.password as password8_3_0_, user0_.passwordExpired as password9_3_0_, user0_.passwordRetriesLeft as passwor10_3_0_, user0_.phoneNumber as phoneNu11_3_0_, user0_.status as status12_3_0_, user0_.type as type13_3_0_, user0_.username as usernam14_3_0_ from user user0_ where user0_.id="62c7b80e-4aaa-47e7-8ff4-a40b3cbe49fa"

