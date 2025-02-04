-- neue 2024er Version der Lehrer-DB (1:N)
-- zu eins beziehungen
drop table if exists lv cascade;
drop table if exists s cascade;
drop table if exists l cascade;

create table L (
  id int primary key,
  name text,
  ALTER int,
  pendler bool  
);

create table LV (
  id int primary key,
  fach char,
  stunden int,
  jahr int,
  lid int references L
);

create table S (
  id int primary key,
  name text,
  alter int,
  pendler bool,
  kv int references l
);


insert into l values
(1, 'Max', 25, true),
(2, 'Fritz', 31, false),
(3, 'Gabi', 31, true),
(4, 'Moritz', 33, false);

insert into lv values 
(1, 'D', 4, 2022, 1),
(2, 'E', 4, 2022, 1),
(3, 'E', 6, 2022, 2),
(4, 'D', 2, 2022, 3),
(5, 'D', 6, 2023, 3);

insert into s values
(10, 'Susi', 15, false, 1),
(20, 'Sepp', 16, false, 3),
(30, 'Max', 16, true, 3);

-- generalisierte projektion in sql
select * from lv;
select 1+2;
select stunden from lv;
-- ρ [konstante, einheiten, stunden] (π 'Test', stunden, stunden * 0.75 (R))
select 'Test' konstante, stunden as einheiten, stunden*0.75 as stunden from lv;

create table artikel (
  id int,
  name text,
  preis numeric
);

insert into artikel values
(1, 'Brot', 3.5),
(2, 'Toast', 1.25),
(3, 'Chips', 2.0);

-- eine gruppe für alle artikel
select sum(preis) as gesamtsumme, count(id) as anz_artikel, avg(preis) durchschn_preis from artikel;
select * from artikel;

-- gesucht gruppen z.B. für obst, gemüse, brot, ...


