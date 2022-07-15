CREATE TABLE if not exists post (
   id SERIAL PRIMARY KEY,
   name TEXT
);

CREATE TABLE if not exists candidate (
    id bigserial primary key,
    name TEXT
);