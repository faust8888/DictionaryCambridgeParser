CREATE SCHEMA cambridge;

CREATE TABLE IF NOT EXISTS cambridge.DICTIONARY(
    id BIGINT AUTO_INCREMENT,
    tag VARCHAR(100),
    dictionary_name VARCHAR(100) NOT NULL,
    create_date DATE,
    user_id BIGINT(10),
    dictionary_content_id BIGINT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cambridge.WORD(
    id BIGINT AUTO_INCREMENT,
    word VARCHAR(300) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS cambridge.TRANSLATION(
    id BIGINT AUTO_INCREMENT,
    word_id BIGINT,
    short_meaning VARCHAR(500),
    form VARCHAR(50),
    PRIMARY KEY (id),
    FOREIGN KEY (word_id) REFERENCES cambridge.WORD(id)
);

CREATE TABLE IF NOT EXISTS cambridge.MEANING(
    id BIGINT AUTO_INCREMENT,
    word_id BIGINT,
    word_translation_id BIGINT,
    explanation VARCHAR(400),
    examples VARCHAR(600),
    PRIMARY KEY (id),
    FOREIGN KEY (word_id) REFERENCES cambridge.WORD(id),
    FOREIGN KEY (word_translation_id) REFERENCES cambridge.TRANSLATION(id)
);

CREATE TABLE IF NOT EXISTS cambridge.DICTIONARY_CONTENT(
    id BIGINT AUTO_INCREMENT,
    dictionary_id BIGINT,
    word_id BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (dictionary_id) REFERENCES cambridge.DICTIONARY(id),
    FOREIGN KEY (word_id) REFERENCES cambridge.WORD(id)
);

ALTER TABLE cambridge.DICTIONARY ADD CONSTRAINT fk_dictionary_content_id FOREIGN KEY (dictionary_content_id) REFERENCES cambridge.DICTIONARY_CONTENT(id);
