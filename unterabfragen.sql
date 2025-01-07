drop table if exists exam cascade;
drop table if exists student cascade;
drop table if exists teacher cascade;

CREATE TABLE teacher (
    account varchar(255) primary key,
    lastname varchar(255),
    firstname varchar(255),   
    birth_date date
);

CREATE TABLE student (
    account varchar(255) primary key,
    lastname varchar(255),
    firstname varchar(255),
    mnumber varchar(255),
    email varchar(255),
    telephone varchar(255)
);

CREATE TABLE exam (
    student varchar(255) references student(account) on delete set null on update cascade,
    teacher varchar(255) references teacher(account) on delete cascade,
    course varchar(255),
    year int,
    grade int,
    check(year > -1 and year <5050 and (teacher = 'a001' or teacher = 'b002' or teacher = 'b003')),
    constraint pk_exam primary key (course, year)
);

Insert into student (account, lastname, firstname, mnumber, email, telephone)
values 
('bef003', 'Berger', 'Franz', 'a2001', 'bef003@mail.at', '06641234'),
('hum002', 'Huber', 'Maria', 'a2104', 'hum002@mail.at', '06446456435'),
('bah12', 'Bacher', 'Heinz', 'a9904', 'bah13@mail.at', '0699234234'),
('grs005', 'Gruber', 'Steffi', 'a1908', 'grs005@mail.at', '06761231');

Insert into teacher (account, lastname, firstname, birth_date)
values 
('a001', 'Carson', 'Rachel', '1907-05-27'),
('b002', 'Euler', 'Leonhard', '1707-04-15'),
('b003', 'Shakespeare', 'William', '1564-04-23');

delete from exam;

-- what if error occours after 3 insert?
-- when is here the commit issued?
Insert into exam (student, teacher, course, year, grade)
values 
('bef003', 'a001', 'Biology', '2020', '1'),
('hum002', 'b002', 'Math','2019', 4),
('hum002', 'b003', 'English','2023', 2),
('bah12',  'a001', 'Biology','2022', -1),
('bah12',  'b003', 'English','2022', 3),
('grs005', 'b003', 'English','2021', 4),
('grs005', 'a001', 'Biology','2021', -1),
('grs005', 'b002', 'Math','2021', -1);

-- name aller studenten (vn, nn) mit ihren prüfungen (fach) und der note (grade)
-- zusätzlich den lehrernamen
select student.firstname, student.lastname, exam.course, exam.grade from student join exam on student.account = exam.student;

select s.firstname, s.lastname, e.course, e.grade from student s join exam e on s.account = e.student;

select s.firstname, s.lastname, e.course, e.grade, t.lastname from student s 
join exam e on s.account = e.student 
join teacher t on t.account = e.teacher
where s.firstname = 'Franz' and s.lastname = 'Berger';

select s.firstname, s.lastname, e.course, e.grade, t.lastname from student s, exam e, teacher t
where s.account = e.student 
and t.account = e.teacher
and s.firstname = 'Franz' and s.lastname = 'Berger';

--

select * from teacher, exam;

-- unterabfrage mit einem ergebnis (eine spalte/attribut, ein wert)
select account from teacher where lastname = 'Shakespeare';
select * from exam where teacher = (select account from teacher where lastname = 'Shakespeare')

-- unterabfran mit mehreren ergebnis tupeln
select * from exam where grade < 0;
select distinct teacher from exam where grade < 0;
-- unkorreliert
select teacher from exam where grade < 0;
select firstname, lastname from teacher where account in (select teacher from exam where grade < 0);
-- korreliert EXISTS
select firstname, lastname from teacher t where exists (select * from exam e where grade < 0 and t.account = e.teacher);
--select * from exam e where grade < 0 and t.account = e.teacher;

select firstname, lastname from teacher t where -1 = any (select grade from exam e where e.teacher = t.account);

--select firstname, lastname from teacher t where -1 = any (select grade from exam e where t.account = 'b002') and t.account = 'b002';
--select grade from exam e where teacher = 'b002';
select grade, teacher from exam order by teacher

select firstname, lastname from teacher t where -1 = all (select grade from exam e where e.teacher = t.account);
select firstname, lastname from teacher t where 0 > all (select grade from exam e where e.teacher = t.account);
select firstname, lastname from teacher t where 0 < all (select grade from exam e where e.teacher = t.account);

-- die namen aller studenten mit unbeurteilten tests
select firstname,lastname from student s where s.account in (select student from exam where grade < 0);

select firstname,lastname from student s where exists (select * from exam e where grade <0 and e.student = s.account);

-- wie oben, eingeschränkt auf 'Euler'
select * from teacher where lastname = 'Euler';
select firstname,lastname from student s where exists (select * from exam e where grade <0 and e.student = s.account and e.teacher = 'b002');

select firstname,lastname from student s where exists 
  (select * from exam e where grade <0 and e.student = s.account and e.teacher = 
  (select account from teacher where lastname = 'Euler')
 );

-- prüfungen (*) die bessere noten haben alls alle vom schüler 'bah12'
select * from student;
select * from exam;
insert into exam values ('bah12', 'a001', 'Biology', 2023, null);
update exam set grade = 4 where student = 'bah12' and year = 2023;
select grade from exam where student = 'bah12' and grade > 0; 

-- mit all
select * from exam where grade < all (select grade from exam where student = 'bah12' and grade > 0) and grade > 0;
-- besser als irgndeine note
select * from exam where grade < any (select grade from exam where student = 'bah12' and grade > 0) and grade > 0;

-- mit min
select min(grade) from exam where student = 'bah12' and grade > 0;
select * from exam where grade < (select min(grade) from exam where student = 'bah12' and grade > 0) and grade > 0;

-- prüfungen (*) die bessere noten haben alls alle vom schüler 'Bacher'
select * from exam where grade < (select min(grade) from exam where grade > 0 and student = 
  (select account from student where lastname = 'Bacher')
) and grade > 0;

select grade, teacher from exam order by teacher;

-- alle lehrer, die eine prüfung betreuen
select * from teacher;
select firstname, lastname from teacher where account in (select teacher from exam);
select firstname, lastname from teacher where exists (select * from exam where teacher.account = exam.teacher);

select count(*) from exam where teacher = 'a001';
select count(*) from exam where teacher = 
--'a001'
(select account from teacher where lastname = 'Carson')
;

-- alle lehrer, die mehr als zwei prüfung betreuen
select firstname, lastname from teacher t where 2 < (select count(*) from exam e where t.account = e.teacher);

-- alle lehrer, die mehr als eine prüfung positiv beurteilt haben 
select firstname, lastname from teacher t where 1 < (select count(*) from exam e where t.account = e.teacher and grade > 0 and grade < 5);

-- alle lehrer, die genau zwei prüfung betreuen
select firstname, lastname from teacher t where (select count(*) from exam e where t.account = e.teacher) = 2;



-- any, all bei einer ergebnisliste
-- =, <, < bei einzelergebnissen

select * from lv;
select * from u;
select * from l;
select * from kv;
select * from s;

-- alle fächer mit namen der unterrichtenden lehrer
-- lv x u x l

select fach, name from lv 
join u on lv.id = u.lvid 
join l on u.lid = l.id;

select fach, name from lv, u, l where 
lv.id = u.lvid and 
u.lid = l.id
;

-- alle fächer mit namen der unterrichtenden lehrer (nur englisch)
select fach, name from lv 
join u on lv.id = u.lvid 
join l on u.lid = l.id 
where lv.fach = 'E';

select fach, name from lv, u, l where 
lv.id = u.lvid and 
u.lid = l.id and 
fach = 'E'
;

-- alle schüler die einen englischlehrer als kv haben bekommen das zeugnis in englischer sprache
-- welche schüler sind das?

select lv.fach, l.name as kv, s.name schüler from lv 
join u on lv.id = u.lvid 
join l on u.lid = l.id 
join kv on kv.lid = l.id 
join s on s.id = kv.sid 
where lv.fach = 'E';


---
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


