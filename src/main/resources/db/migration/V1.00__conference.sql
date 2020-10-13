create table CONFERENCE
(
    CONF_ID     BIGINT auto_increment
        primary key,
    DATE        date,
    NAME        VARCHAR(255),
    PRTSPSCOUNT INTEGER,
    THEME       VARCHAR(255)
);

create table TALK
(
    TALK_ID BIGINT auto_increment
        primary key,
    DESC    VARCHAR(255),
    NAME    VARCHAR(255),
    PERSON  VARCHAR(255),
    TYPE    VARCHAR(255)
);

create table CONFTALK
(
    CONF_ID BIGINT not null,
    TALK_ID BIGINT not null
            unique,
        foreign key (CONF_ID) references CONFERENCE (CONF_ID),
        foreign key (TALK_ID) references TALK (TALK_ID)
);
