DROP TABLE IF EXISTS JOKE;
CREATE TABLE JOKE
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    joke_text   VARCHAR(250) NOT NULL,
    insert_time timestamp    NOT NULL,
    update_time timestamp    NULL
);
