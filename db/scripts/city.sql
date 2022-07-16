create table if not exists city (
    id seiral  primary key,
    name text
);

insert into city(name) values ('Moscow'), ('Severodvinsk'), ('Murmansk');