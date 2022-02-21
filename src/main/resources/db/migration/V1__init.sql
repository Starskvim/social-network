CREATE TABLE social_user
(
    id         BIGINT NOT NULL,
    login      VARCHAR(255),
    password   VARCHAR(255),
    mail       VARCHAR(255),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    CONSTRAINT pk_social_user PRIMARY KEY (id)
);

CREATE TABLE user_friends
(
    friend_id BIGINT NOT NULL,
    user_id   BIGINT NOT NULL,
    CONSTRAINT pk_user_friends PRIMARY KEY (friend_id, user_id)
);

ALTER TABLE social_user
    ADD CONSTRAINT uc_social_user_login UNIQUE (login);

ALTER TABLE user_friends
    ADD CONSTRAINT fk_usefri_on_friendid FOREIGN KEY (friend_id) REFERENCES social_user (id);

ALTER TABLE user_friends
    ADD CONSTRAINT fk_usefri_on_userid FOREIGN KEY (user_id) REFERENCES social_user (id);