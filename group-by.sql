select * from r;

select a || ' euro', 'text' dummy, a, a*2, a*b from r;

select sum(a) from r;
select sum(a), sum(b) from r group by b;

--- group by

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

insert into lv values
(6, 'M', 2, 2022, 4);
insert into lv values
(7, 'E', 1, 2022, 4);

insert into s values
(10, 'Susi', 15, false, 1),
(20, 'Sepp', 16, false, 3),
(30, 'Max', 16, true, 3);

select * from lv;
-- wie viele stunden wurden 2022 insgesammt unterrichtet
select sum(stunden) from lv where lv.jahr = 2022;
-- wie viele stunden wurden pro jahr unterrichtet
select jahr, sum(stunden) from lv group by jahr;
-- select jahr, fach, sum(stunden) from lv group by jahr;

-- wie viele fächer wurden 2022 unterrichtet
select count(distinct fach) from lv where lv.jahr = 2022;

-- wie viele stunden wurden pro lehrer (lid) 2022 unterrichtet
select lid, sum(stunden) 
from lv
where jahr = 2022
group by lid;

-- wie viele stunden wurden pro lehrer (name) 2022 unterrichtet
select * from lv join l on lv.lid = l.id;
select l.name, sum(stunden) from lv 
join l on lv.lid = l.id
where lv.jahr = 2022
--in der projektionsliste dürfen nur gruppierungsattribute und aggregationsfuntionen vorkommen
--group by lid;
group by name, lid;

with 
llv as (select * from lv join l on lv.lid = l.id where lv.jahr = 2022)
select name, sum(stunden) from llv group by name, lid;

-- die gesamtsunden aller lehrer die 2022 min. 2 fächer unterrichtet haben
with 
llv as (select * from lv join l on lv.lid = l.id where lv.jahr = 2022)
select name, sum(stunden) 
from llv 
group by name, lid
having(count(fach) > 1);

-- die gesamtsunden aller lehrer die 2022 entweder D oder E unterrichtet haben
with 
llv as (select * from lv join l on lv.lid = l.id where lv.jahr = 2022)
--
select name, sum(stunden) from llv llv1 group by name, lid 
having(
  (select count(*) from llv llv2 where llv1.lid = llv2.lid and fach in ('E', 'D')) > 0 
);
--select * from llv;

-- die gesamtsunden aller lehrer die 2022 entweder D oder E unterrichtet haben
with 
llv as (select * from lv join l on lv.lid = l.id where lv.jahr = 2022)
--
select name, sum(stunden) from llv llv1 group by name, lid 
having(
  (select count(*) from llv llv2 where llv1.lid = llv2.lid and fach in ('E')) > 0 and 
  (select count(*) from llv llv2 where llv1.lid = llv2.lid and fach in ('D')) > 0
);


with 
llv as (select * from lv join l on lv.lid = l.id where lv.jahr = 2022)
--
select name, sum(stunden) from llv llv1 group by name, lid 
having(
  (select count(*) from llv llv2 where llv1.lid = llv2.lid and fach in ('E')) > 0 and 
  (select count(*) from llv llv2 where llv1.lid = llv2.lid and fach in ('D')) > 0
);

-- geht nicht!
with 
llv as (select * from lv join l on lv.lid = l.id where lv.jahr = 2022)
--
select name, sum(stunden) from llv llv1  
where exists 
  (select * from llv llv2 where llv1.lid = llv2.lid and fach in ('E')) 
and exists
  (select count(*) from llv llv3 where llv1.lid = llv3.lid and fach in ('D'))
group by name, lid;

