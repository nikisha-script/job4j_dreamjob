CREATE TABLE if not exists post (
   id SERIAL PRIMARY KEY,
   name TEXT
   description TEXT,
   created timestamp,
   visible boolean,
   city text
);

CREATE TABLE if not exists candidate (
    id bigserial primary key,
    name TEXT,
    surname TEXT,
    description TEXT,
    date_of_birth timestamp,
    img bytea,
    visible boolean
);