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
