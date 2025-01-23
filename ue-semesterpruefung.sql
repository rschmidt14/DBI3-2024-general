select * from land;
select * from ort;

-- alle ortschaften (name, plz), die am fuschlsee liegen
-- als subselect nur mit dem IN Operator

select o.name, o.plz from ort o join sort so on o.plz = so.plz;

select name, plz from ort where plz in (
  select plz from sort
);

select name, plz from ort where plz in (
  select plz from sort where sid in (
    select sid from see where see = 'Fuschlsee'
  )
);

select sid from see where see = 'Fuschlsee';

select o.name, o.plz from ort o where exists (
  select * from sort so where so.plz = o.plz and exists (
    select * from see s where s.sid = so.sid and see = 'Fuschlsee'  
  )
);

select * from see;
select name, plz from ort natural join sort; 
select name, plz from ort natural join sort natural join see where see = 'Fuschlsee'; 

-- Fulschsee in der Join Bedingung
select ort.name, ort.plz from ort 
  join sort on ort.plz = sort.plz 
  join see on see.sid = sort.sid
  and see.see = 'Fuschlsee';
 
-- Fulschsee in die Selektion
select ort.name, ort.plz from ort 
  join sort on ort.plz = sort.plz 
  join see on see.sid = sort.sid
  where see.see = 'Fuschlsee'; 
 
-- namenskonflikt
select name, plz from ort natural join sort natural join (select sid, see, flache from see) where see = 'Fuschlsee'; 
select name, plz from ort natural join sort natural join (select sid, see as name, flache from see) where name = 'Fuschlsee'; 
--select * from see s where s.sid = so.sid and see = 'Fuschlsee';
select * from ort natural join sort natural join (select sid, see as name2, flache from see);
-- paare von ortschaften mit gleichem namen unterschiedlicher plz
-- keine duplikate! [kirchberg, 6365, 5232] [kirchberg, 5232, 6365]
-- [ort, plz1, plz2]


select * from ort;

select o1.name, o1.plz, o2.plz from ort o1, ort o2 where 
  o1.name = o2.name and o1.plz > o2.plz;
 
select o1.name, o1.plz, o2.plz from ort as o1 
  join ort as o2 on o1.name = o2.name and o1.plz > o2.plz;

 select o1.name, o1.plz, o2.plz from ort as o1 
  join ort as o2 on o1.name = o2.name
  where o1.plz > o2.plz;

select * from land;
select * from ort;

select * from land natural join ort;

with
nl as (select lid, name lname from land),
no as (select plz, name oname, land lid, ew from ort)
select * from nl natural join no;

select * from (select lid, name lname from land) natural join (select plz, name oname, land lid, ew from ort);


select * from berg;
select * from bort;
delete from bort where plz = 5232 and bid = 1;
select plz, bid from bort where bid in (select bid from berg where bort.bid = berg.bid);
select distinct plz from bort where 1000 < ALL (select hohe from berg where bort.bid = berg.bid);
select distinct plz from bort where 1000 < any (select hohe from berg where bort.bid = berg.bid);

