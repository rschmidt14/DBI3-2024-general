select * from ismember where country = 'A';
select distinct country from ismember;

select * from country;
select code from country;
select * from ismember;
select country from ismember;

select code from country
except
select country from ismember;

select name from country where code in
(select code from country
except
select country from ismember);

-- die namen aller österr. flughäfen und landeshauptstädte
select * from airport;
select * from province;

select capital from province where country = 'A'; 
select name from airport where country = 'A';

select * from country where name = 'Austria';
select capital from province where country = 'A'
union
select name from airport where country = 'A';

select capital from province where country = (select code from country where name = 'Austria')
union
select name from airport where country = (select code from country where name = 'Austria');


---
drop table if exists r;
drop table if exists s;
create table r (
 a int
);

select r.a from r;

drop table if exists s;
create table s (
 a int
);

select * from r,s where a = 1;

drop table if exists lv cascade;
drop table if exists s cascade;
drop table if exists l cascade;

create table LV (
  id int,
  fach char,
  stunden int,
  jahr int
);

create table L (
  lid int,
  lname text,
  ALTER int,
  pendler bool  
);

create table S (
  sid int,
  sname text,
  alter int,
  pendler bool,
  kv int
);

truncate l;
insert into l values
(1, 'Max', 25, true),
(2, 'Fritz', 31, false),
(3, 'Gabi', 31, true),
(4, 'Moritz', 33, false);

insert into lv values 
(1, 'D', 4, 2022),
(1, 'E', 4, 2022),
(2, 'E', 6, 2022),
(3, 'D', 2, 2022);

--,
insert into lv values(3, 'D', 6, 2023);


insert into s values
(10, 'Susi', 15, false, 1),
(20, 'Sepp', 16, false, 3),
(30, 'Max', 16, true, 3);

-- vorgänger 
insert into v values
('Max', 'Erich'),
('Gabi', 'Fritz'),
('Erich', 'Moritz'),
('Fritz', 'Hansi')
;

select * from l;
select * from lv;
select * from l, lv;
select * from l, lv where lid = id;
select lname, fach, stunden, jahr from l, lv where lid = id;
-- die namen aller englischlehrer
select lname from l, lv where lid = id and fach = 'E';
-- wie viele stunden hat Max 2022 unterrichtet
select stunden from l, lv where lid = id and lname = 'Max' and jahr = 2022;
-- wie viele stunden hat Max 2022 unterrichtet
select sum(stunden) from l, lv where lid = id and lname = 'Max' and jahr = 2022;
-- wie viele Englischstunden hat Max 2022 unterrichtet
select sum(stunden) from l, lv where lid = id and lname = 'Max' and jahr = 2022 and fach = 'E';
-- welchen klassenvorstand (name) hat sepp
select * from s;
select * from l;
select lname from s,l where kv = lid and sname = 'Sepp';
-- die namen aller lehrer und fächer
select lname as name from l
union
select fach as name from lv;

select lname as name from l;
select fach as name from lv;

-- l[lid, lname, lalter, lpendler]
select * from l;
-- s[sid, sname, salter, spendler, kv]
select * from s;
-- lv[id, fach, stunden, jahr]
select * from lv;

-- der kv welcher schüler ist 'Gabi'
select sname from l, s where lid = kv and lname = 'Gabi';
select sname from s where kv in (select lid from l where lname = 'Gabi'); 

-- alle schülernamen, alter v. l und s, und kvnamen
select sname, s.alter, lname, l.alter from l, s where lid = kv;

-- Gesucht ist eine Tabelle (Name), die die Namen aller...
-- die namen aller pendler (schüler und lehrer)
select sname as name from s where pendler = true
union 
select lname as name from l where pendler = true;

select sname as name from s where pendler = true;
select lname as name from l where pendler = true;

-- alle lehrer die ein fach unterrichten (mit unterabfrage, mit kreuzprodukt)
select * from l, lv;
select distinct lname from l, lv where l.lid = lv.id;

select * from lv;
select distinct id from lv;
select * from l;
select * from l where lid in (select id from lv);
select lname from l where lid in (select id from lv);
-- alle lehrer (name) die kein fach unterrichten
select lname from l
except
select lname from l, lv where l.lid = lv.id;

select lname from l where lid not in (select id from lv);

-- alle fächer mit namen der unterrichtenden lehrer (name, fach)
select lname as name, fach from l,lv where lid = id;

-- alle fächer mit namen der unterrichtenden lehrer (name, fach) im jahr 2022

-- alle lehrer die kv sind
select distinct lname from l, s where l.lid = s.kv;
-- alle lehrere die keine kv sind

-- name, lalter, salter bei namensgleichheit
select sname, s.alter, l.alter from s, l where sname = lname;

-- Name der Stadt, Name des Landes und Bevölkerung des Landes in dem Adamstown liegt;
select * from city;
select * from country;

select i.name, o.name, o.population from city i, country o;
select city.name, country.name, country.population from city, country where country.code = city.country and city.name = 'Adamstown';

-- Mengendurchschnitt 
-- R ∩ S = R - (R-S)

select name from l;
select name from s;

select name from l 
intersect
select name from s;

select name from l
except
(select name from l 
except
select name from s)
;

select * from s;
select * from l;
select * from l,lv where l.lid = lv.id;
select sname, lname, fach, jahr from l,lv,s where 
l.lid = lv.id and
s.kv = l.lid;

select * from l join lv on l.id = lv.id;

select sname, lname, fach, jahr from l 
join lv on l.lid = lv.id
join s on s.kv = l.lid;

---=====

drop table if exists lv cascade;
drop table if exists s cascade;
drop table if exists l cascade;
drop table if exists u cascade;
drop table if exists kv cascade;

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
  jahr int
);

-- unterrichtet
create table S (
  id int primary key,
  name text,
  alter int,
  pendler bool
);

create 
 (
  sid int references s,
  lid int references l,
  constraint pk primary key (sid,lid)
);

insert into l values
(1, 'Max', 25, true),
(2, 'Fritz', 31, false),
(3, 'Gabi', 31, true),
(4, 'Moritz', 33, false);

insert into lv values 
(1, 'D', 4, 2022),
(2, 'E', 4, 2022),
(3, 'E', 6, 2022),
(4, 'D', 2, 2022),
(5, 'D', 6, 2023);

create table U (
  lid int references l, 
  lvid int references lv primary key
);

insert into s values
(10, 'Susi', 15, false),
(20, 'Sepp', 16, false),
(30, 'Max', 16, true);

insert into kv values (10, 1), (20, 3), (30, 3);
insert into u values (1, 1), (1, 2),(2, 3),(3, 4), (3,5);

-- alle lehrer die ein fach unterrichten mit x und join
select l.name, lv.fach, lv.jahr from u 
join l on u.lid = l.id 
join lv on lvid = lv.id
where lv.jahr = 2022;

select l.name, lv.fach, lv.jahr from l, lv, u 
where u.lid = l.id 
and u.lvid = lv.id
and lv.jahr = 2022;

-- 

-- mögliche paarungen für fahrgemeinschaften
select * from l;
update l set pendler = true where name = 'Fritz';

select * from l;
select fahrer.name fahrer, beifahrer.name as beifahrer from l as fahrer, l as beifahrer
where 
--fahrer.id != beifahrer.id and 
fahrer.pendler = true and 
beifahrer.pendler = true and
fahrer.id > beifahrer.id;

select fahrer.name fahrer, beifahrer.name as beifahrer from l as fahrer 
join l as beifahrer on 
--fahrer.id != beifahrer.id and
fahrer.pendler = true and 
beifahrer.pendler = true and
fahrer.id > beifahrer.id;

select fahrer.name fahrer, beifahrer.name as beifahrer from l as fahrer 
join l as beifahrer on fahrer.id > beifahrer.id
where 
fahrer.pendler = true and 
beifahrer.pendler = true;

--
-- vorgänger 
truncate v;
insert into v values
('Max', 'Erich'),
('Gabi', 'Fritz'),
('Erich', 'Moritz'),
('Fritz', 'Hansi')
;
select * from v;

-- sch(VV) = [vvg, vg, nf]
select v1.vg as vvg, v2.vg as vg, v2.nf as nf from v as v1, v as v2
where v1.nf = v2.vg;


-- bsp kundensegment
drop table if exists kunde;
drop table if exists segment;

-- kdnr, vn, nn, alter
create table kunde (
  name text,
  alter int
);

-- id, alter, typ, naechster
create table segment (
  alter int,
  typ text
);

insert into kunde values
('Basti', 11),
('Kevin', 15),
('Ernst', 25),
('Max', 35),
('Susi', 45),
('Gabi', 55);

insert into kunde values ('Baby', 1);

insert into segment values
(2, 'Baby'),
(12, 'Kind'),
(25, 'Jung'),
(50, 'Mittel'),
(80, 'Alt');

insert into segment values (13, 'Kind');

select * from kunde;
select * from segment;

-- Alle Kunden im Segment Kind inkl. Babies
-- x
select * from kunde k, segment s where s.typ = 'Kind';
select * from kunde k, segment s where k.alter <= s.alter
and s.typ = 'Kind';

-- join
select * from kunde k join segment s on k.alter <= s.alter
and s.typ = 'Kind';

-- subselect
select alter from segment where typ = 'Kind';
select * from kunde where alter <= (select alter from segment where typ = 'Kind');
select * from kunde where alter <= any (select alter from segment where typ = 'Kind');
select * from kunde where alter <= all (select alter from segment where typ = 'Kind');

-- Hausübung
-- Finde alle jungen Kunden (exkl Kinder) mit kreuzprodukt bzw join
-- Finde alle jungen Kunden (exkl Kinder) mit kreuzprodukt mit subselect
select name from kunde k join segment s on k.alter <= s.alter and s.typ = 'Jung'
except
select name from kunde k join segment s on k.alter <= s.alter and s.typ = 'Kind';

select name from kunde k 
join segment so on k.alter <= so.alter and so.typ = 'Jung'
join segment su on k.alter > su.alter and su.typ = 'Kind';

select k.name kname, k.alter kalter,  su.alter sualter, su.typ sutyp, so.alter soalter, so.typ sotyp from kunde k, segment su, segment so;






