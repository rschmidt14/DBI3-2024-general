drop table if exists r;
drop table if exists s;
drop table if exists q;

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

create table Q (
  A int,
  S int,
  T int
);

insert into R values (0, 1, 2, 3), (4, 1, 3, 2);
insert into S values (1, 2, 1, 4), (3, 4, 1, 3), (3, 5, 1, 3);
insert into q values (0, 1, 2), (4, 2, 3), (1, 1, 2);

select * from q;
select * from r;
select * from s;
delete from r;

select * from r natural join q;
select * from r natural inner join q;
--select * from q natural join r;

select * from r natural left outer join q;
select * from r natural left join q;

select * from r natural right join q;
select * from r natural full join q;

select a, b, c, d, x, y from r natural join s;

select a, s, t, b, c, d, x, y from q natural left join r natural join s;
select * from (q natural left join r) natural join s;

select a, s, t, b, c, d, x, y from q natural left join (r natural join s);
--select a, s, t, b, c, d, x, y from q natural left join r natural left join s;

with
x as (select * from r natural join s)
select a, s, t, b, c, d, x, y from q natural left join x;

select a, s, t, b, c, d, x, y from r natural join s natural right join q;

select a, b, c, d, x, y from r natural join s;
select a, b, c, d, x, y from r natural left join s;
select a, b, c, d, x, y from r natural right join s;
select a, b, c, d, x, y from r natural full join s;

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