select host, user from mysql.user; # mysql db 안에 존재하는 user table 대상
show databases;
create database db_b202012047;
-- database b202012047;

# DDL: Data Definition Language, 데이터베이스, 테이블 등을 정의하는데 사용하는 언어
show tables;
create table t_mb202012047 (
                               mid BIGINT PRIMARY KEY AUTO_INCREMENT,
                               fullname VARCHAR(30) NOT NULL,
                               email VARCHAR(30) NOT NULL,
                               pw VARCHAR(30) NOT NULL,
                               zipcode VARCHAR(5)
);

/* C.R.U.D : create는 insert 구문과 관련이 높음 - DML: Data Manipulation Language */
insert into t_mb202012047(fullname, email, pw) values('ngh', 'ngh1234@asdf.com', 'cometrue');
/* read는 select 구문과 관련 높음 */
select * from t_mb202012047;

update t_mb202012047 set zipcode = 01878 where mid = 1;

delete from mysql.user where user like 'u_b202012047';

use db_b202012047;
show tables;
drop table t_mb202012047;
drop database db_b202012047;

create database db_b202012047;
create user 'u_b202012047'@'%' identified by 'cometrue';
grant all privileges on db_b202012047.* to 'u_b202012047'@'%';
flush privileges;

insert into t_mb202012047(fullname, email, pw) values('admin', 'admin', 'cometrue');
insert into t_mb202012047(fullname, email, pw) values('test1', 'test1', 'cometrue');
insert into t_mb202012047(fullname, email, pw) values('test2', 'test2', 'cometrue');
insert into t_mb202012047(fullname, email, pw) values('test3', 'test3', 'cometrue');
delete from t_mb202012047 where mid = 2;