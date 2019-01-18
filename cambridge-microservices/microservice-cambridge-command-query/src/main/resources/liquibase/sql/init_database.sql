CREATE SCHEMA cambridge;

CREATE TABLE IF NOT EXISTS cambridge.DICTIONARY(
    id INT(10),
    tag VARCHAR(100),
    dictionary_name VARCHAR(100) NOT NULL,
    create_date DATE,
    user_id INT(10),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cambridge.WORD(
    id INT(10),
    word VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cambridge.TRANSLATION(
    id INT(10),
    word_id int(10),
    short_meaning VARCHAR(500),
    form VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (word_id) REFERENCES cambridge.WORD(id)
);

CREATE TABLE IF NOT EXISTS cambridge.MEANING(
    id INT(10),
    word_id int(10),
    word_translation_id int(10),
    explanation VARCHAR(400),
    examples VARCHAR(600),
    PRIMARY KEY (id),
    FOREIGN KEY (word_id) REFERENCES cambridge.WORD(id),
    FOREIGN KEY (word_translation_id) REFERENCES cambridge.TRANSLATION(id)
);

CREATE TABLE IF NOT EXISTS cambridge.DICTIONARY_CONTENT(
    id INT(10),
    dictionary_id int(10),
    word_id int(10),
    PRIMARY KEY (id),
    FOREIGN KEY (dictionary_id) REFERENCES cambridge.DICTIONARY(id),
    FOREIGN KEY (word_id) REFERENCES cambridge.WORD(id)
);

