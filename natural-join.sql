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