CREATE TABLE conftalk (
    conf_id BIGINT NOT NULL,
    talk_id BIGINT NOT NULL,
    primary key (conf_id, talk_id),
    foreign key (conf_id) references conference(id),
    foreign key (talk_id) references talk(id)
);
