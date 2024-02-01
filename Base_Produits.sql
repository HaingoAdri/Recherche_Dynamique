create database produit_search;
\c produit_search

create table types(
    id serial primary key,
    nom varchar(50)
);

create table aspect(
    id serial primary key,
    aspect varchar(256),
    requette varchar(256)
);

create table produits(
    id serial primary key,
    nom varchar(50),
    categorie varchar(50),
    qualite int,
    prix double precision,
    rapport double precision generated always as (qualite/prix) stored
);

insert into aspect(aspect, requette)
values 
('meilleur rapport qualite prix par categorie','select * from produits as p1 where rapport = (select Max(p2.rapport) from produits as p2 where p1.rapport = p2.rapport) group by categorie'),
('pire rapport qualite prix par categorie', 'select * from produits as p1 where rapport = (select Min(p2.rapport) from produits as p2 where p1.rapport = p2.rapport) group by categorie'),
('meilleur qualite par categorie', 'select * from produits as p1 where qualite>6 group by categorie'),
('meilleur prix par categorie', 'select * from produits as p1 where prix = (select Min(p2.prix) from produits as p2 where p1.prix = p2.prix) group by categorie order by prix asc limit 10'),
('produits moins chere', 'select * from produits as p1 where prix = (select Min(p2.prix) from produits as p2 where p1.prix = p2.prix) group by categorie order by prix asc limit 10'),

