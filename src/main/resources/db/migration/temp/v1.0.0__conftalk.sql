-- CREATE TABLE conftalk (
--     conf_id BIGINT NOT NULL,
--     talk_id BIGINT NOT NULL,
--     primary key (conf_id, talk_id),
--     foreign key (conf_id) references conference(id),
--     foreign key (talk_id) references talk(id)
-- );

-- auto-generated definition
create table CONFTALK
(
    CONF_ID BIGINT not null,
    TALK_ID BIGINT not null
        constraint UK_L11AQTGX0V37I4RGYISVV3YSO
            unique,
    constraint FK77444EPKX3OY6QCR68BXXYDEJ
        foreign key (CONF_ID) references CONFERENCE (CONF_ID),
    constraint FK9SV5RSQ7ABYERMU2BC0FN0F9F
        foreign key (TALK_ID) references TALK (TALK_ID)
);
