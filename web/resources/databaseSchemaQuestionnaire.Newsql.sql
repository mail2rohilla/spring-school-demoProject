drop database studyApp;
create database studyApp;
use studyApp;

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
    unique (email),
    unique (phoneNumber),
    unique (username)
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






