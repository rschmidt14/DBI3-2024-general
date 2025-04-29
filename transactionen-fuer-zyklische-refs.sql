Integritaetsbedingungen
Datenbankmanagementsysteme sind füur die Erhaltung der Datenkonsistenz zustandig.
1) Konsistenzerhaltung nach Systemfehler (Recovery) bzw. bei Mehrbenutzerbetrieb (Concurrency Control) 
2) Semantische Integritaetsbedingungen (im Folgenden), welche eigenschaften der modellierten Miniwelt abgeleitet werden.

Semantische Integritaetsbedingungen werden eingeteilt in:
1) Statische Integritaetsbedingungen: Bedingungen (constraints) an den Zustand der Datenbasis, die in jedem Zustand der
Datenbank erfuellt sein muessen.
-> z.B. durch check
2) Dynamische Integrit¨atsbedingungen: Bedingungen an Zustandsaenderungen in der Datenbank
-> z.B. Löschbedingung

Implizite Anforderunge:
Schlüssel, Beziehungskardinaliäten, Attributdomänen, Inklusion bei Generalisierung

Statische Integritätsbingungen in SQL
* Schlüssel: PK, SK, FK
* Vermeidung von Nullwerten
* Default Werte
* Check Klausel

Dynamische Integritätsbingungen in SQL
* Referenzielle Integrität: onUpdate, onDelete
* Zyklische Abhängigkeiten / doppelte Verkettung

drop table if exists q cascade;
select * from q;
create table q (
  i int
);

begin;
drop table if exists person cascade; 
drop table if exists picture cascade; 
commit;

-- Chicken/Egg Problem

create table person (
  pid int primary key,
  name varchar  --,
  ---photo varchar references picture
);

-- SQL Error [42P01]: ERROR: relation "picture" does not exist

create table picture (
  fid varchar primary key,
  person int references person,
  data bytea
);
-- Lösung für DDL -> Constraint erst später hinzufügen mit ALTER
drop table if exists person cascade;
drop table if exists picture cascade;

create table person (
  pid int primary key,
  name varchar,
  photo varchar  
);

create table picture (
  fid varchar primary key,
  person int references person,
  data bytea
);

alter table person add constraint fk_foto foreign key (photo) references picture;

-- daten einfügen ##!!
insert into person values (1, 'Franz', 'F01.jpg');

-- SQL Error [23503]: ERROR: insert or update on table "person" violates foreign key constraint "fk_foto"
-- Detail: Key (photo)=(Franz.jpg) is not present in table "picture".

-- Lösung, Transaktion?
--> Auf manual Commit umstellen!

begin;
insert into person (pid, name, photo) values (1, 'Franz', 'F01.jpg');
insert into picture (fid, person) values ('F01.jpg', 1);
commit;

--SQL Error [23503]: ERROR: insert or update on table "person" violates foreign key constraint "fk_foto"
--  Detail: Key (photo)=(F01.jpg) is not present in table "picture".

-- Lösung: Verzögern Sie die Überprüufung der Bedingung bis ans Ende der Transaktion 
alter table person drop constraint fk_foto;
-- DEFAULT immediate validation, kann nicht ans Ende der Transaktion verschoben werden
-- alter table person add constraint fk_foto foreign key (photo) references picture immediate not deferrable
-- Validierung am Ende der Transaktion  
alter table person add constraint fk_foto foreign key (photo) references picture initially deferred deferrable;

begin;
insert into person (pid, name, photo) values (1, 'Franz', 'F01.jpg');
insert into picture (fid, person) values ('F01.jpg', 1);
commit;

select * from person;
select * from picture;

--- neue version

drop table if exists person cascade;
drop table if exists picture cascade;

create table person (
  pid int primary key,
  name varchar,
  photo varchar,
  fid varchar
);

create table picture (
  fid varchar primary key,
  person int references person initially deferred deferrable,
  data bytea
);

alter table person add constraint fk_foto foreign key (fid) references picture initially deferred deferrable;

begin;
insert into person (pid, name, photo) values (1, 'Franz', 'F01.jpg');
insert into picture (fid, person) values ('F01.jpg', 1);
commit;
