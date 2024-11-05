select * from country;

select name, elevation from mountain where mountains = 'Alps' order by elevation desc;

select * from language;
select * from language where superlanguage is null and name  ~'^[A-E].*$' order by name;

--select * from city where population = min(population);
select min(population) from city where population > 0;

--sub-select oder unterabfrage
select * from city where population =
 (select min(population) from city where population > 0);
 
select * from country;
select country from city where name = 'Adamstown';
select name from country where code =
  (select country from city where name = 'Adamstown');
  
select * from city where name = 'Springfield';
select country from city where name = 'Victoria';
select name from country where code in
  (select country from city where name = 'Victoria');


select * from country where name = 'Austria';

select * from province where country in ('E', 'A') order by country;
select * from province where country = 'E' or country = 'A';

select name from province where country = 'A';
select code from country where name = 'Austria';
select name from province where country =
  (select code from country where name = 'Austria');

