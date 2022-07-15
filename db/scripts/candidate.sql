create table candidate(
    id bigserial primary key,
    name text,
    surname text,
    description text,
    date_of_birth timestamp,
    img bytea,
    visible boolean
);
