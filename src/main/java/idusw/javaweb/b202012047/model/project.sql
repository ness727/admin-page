drop table t_prjb202012047;

create table t_prjb202012047 (
    pid bigint auto_increment primary key,
    project_name varchar(30) not null,
    project_description varchar(200),
    status varchar(15) not null,
    client_company varchar(5),
    project_leader varchar(30),
    estimated_budget bigint,
    total_amount_spent bigint,
    estimated_project_duration bigint
);

/* C.R.U.D - insert, select, update, delete 구문으로 처리 */
insert into t_prjb202012047(project_name, status) values('DCT-3 Project', 'On Hold'); /* On Hold, Canceled, Success */
insert into t_prjb202012047(project_name, status, project_leader) values('Comso Project', 'Success', 'b202012047ngh');
insert into t_prjb202012047(project_name, status, project_leader) values('Induk Project', 'On Hold', 'induk');
insert into t_prjb202012047(project_name, status, project_leader) values('Seoul Project', 'Canceled', 'seoul');

select * from t_prjb202012047 where pid = 1;
select * from t_prjb202012047;
update t_prjb202012047 set status = 'Success' where pid = 1;
delete from t_prjb202012047 where pid = 1;