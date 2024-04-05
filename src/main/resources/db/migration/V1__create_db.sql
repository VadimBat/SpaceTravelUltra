CREATE TABLE client (
    id IDENTITY PRIMARY KEY,
    name VARCHAR(200) CHECK(CHAR_LENGTH(name)>3)
);

CREATE TABLE planet (
    id VARCHAR(100) PRIMARY KEY ,
    name VARCHAR(500) CHECK(CHAR_LENGTH(name)>1),
    CONSTRAINT UpperCaseCheck CHECK (id = UPPER(id)),
    CONSTRAINT LatinCharactersCheck CHECK (REGEXP_LIKE(id, '^[0-9A-Z]+$'))
);

CREATE TABLE ticket (
    id IDENTITY PRIMARY KEY,
    created_at TIMESTAMP,
    client_id BIGINT,
    from_planet_id VARCHAR(100),
    to_planet_id VARCHAR(100),
    FOREIGN KEY (client_id) REFERENCES client(id),
    FOREIGN KEY (from_planet_id) REFERENCES planet(id),
    FOREIGN KEY (to_planet_id) REFERENCES planet(id)
);