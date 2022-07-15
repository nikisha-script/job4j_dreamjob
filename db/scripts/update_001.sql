CREATE TABLE IF NOT EXISTS POST (
   id SERIAL PRIMARY KEY,
   name TEXT,
   description TEXT,
   created TIMESTAMP,
   VISIBLE BOOLEAN,
   CITY TEXT
);

CREATE TABLE IF NOT EXISTS CANDIDATE (
    id SERIAL PRIMARY KEY,
    name TEXT,
    surname TEXT,
    description TEXT,
    date_of_birth TIMESTAMP,
    img BYTEA,
    VISIBLE BOOLEAN
);

