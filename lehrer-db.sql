-- Bsp 1
drop table if exists l cascade;
drop table if exists lv cascade;
drop table if exists s cascade;
drop table if exists schule cascade;

create table L (
  id int,
  name text,
  ALTER int,
  pendler bool  
);

create table S (
  id int,
  name text,
  ALTER int,
  pendler bool  
);

create table LV (
  id int,
  fach char,
  stunden int,
  jahr int,
  sid int
);

create table Schule (
  sid int,
  typ int,
  name text
);

insert into l values
(1, 'Max', 25, true),
(2, 'Fritz', 31, false),
(3, 'Gabi', 31, true),
(4, 'Moritz', 33, false);

insert into s values
(10, 'Susi', 15, false),
(20, 'Sepp', 16, false),
(30, 'Max', 16, true);

insert into lv values 
(1, 'D', 4, 2022, 1),
(1, 'E', 4, 2022, 2),
(2, 'E', 6, 2022, 1),
(3, 'D', 2, 2022, 3);

insert into Schule values
(1, 1, 'HTL Spenglergasse'),
(2, 1, 'HTL Salzburg'),
(3, 2, 'BORG Braunau')
;

-- Mengenvereinigung ...union
select * from l
union
select * from s;

select name from l
union
select name from s;

select name from l
union
select name from s;

select name from (
select * from l
union
select * from s);


select id from l
union
select typ from schule;