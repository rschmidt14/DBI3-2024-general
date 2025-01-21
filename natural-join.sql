drop table if exists R cascade;
drop table if exists S cascade;

create table R (
  A int,
  B int,
  C int,
  D int
);

create table S (
  X int,
  Y int,
  B int,
  D int
);

insert into R values (0, 1, 2, 3), (4, 1, 3, 2);
insert into S values (1, 2, 1, 4), (3, 4, 1, 3), (3, 5, 1, 3);

select * from s;

-- natural join
select * from r natural join s;
-- semi join 
select distinct r.* from r natural join s;
-- anti semi join
select * from r 
except
select r.* from r natural join s;

-- lehrer db mit n:1 beziehungen
-- keine eigenen tabellen für "unterrichtet" bzw. "ist_klassenvorstand"

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

select l.name, lv.fach from l join lv on l.id = lv.lid;

select id, fach, stunden, jahr, lid from lv;
select id as lid, name, ALTER, pendler from l;

-- natural join durch umbenennung ermöglicht
select name, fach from (select id as lid, name, ALTER, pendler from l) natural join lv;

-- diesmal mit zuweisung
with
natural_l as (select id as lid, name, ALTER, pendler from l)
--select name, fach from natural_l natural join lv where fach = 'E';
-- semi join alle e-lehrer
--select natural_l.* from natural_l natural join lv where fach = 'E';
-- anti semi join alle nicht e-lehrer
select * from l 
except
select natural_l.* from natural_l natural join lv where fach = 'E';

-- datenbank für natural joins umbauen

select * from l;
alter table l rename id to lid;
delete from l where name = 'Gabi';

alter table s rename id to sid;
alter table s rename kv to lid;

-- alle e lehrer
select name, fach from l natural join lv where fach = 'E';
-- alle schüler mit den namenb ihrer klassenvorstände
-- problem natural join nimmt jetzt als join bedingung: lid und name und alter und pendler 
select s.name, l.name from s natural join l;

select lid, name lname, alter lalter, pendler lpendler from l;

-- umbenennung für natural join
-- alternativ datenbank ändern und gleiche attribute verhindern (außer PK/FK beziehungen)
with
nl as (select lid, name as lname, alter as lalter, pendler as lpendler from l)
select s.name, nl.lname from s natural join nl;
-- die schuler die einen e lehrer als kv haben
