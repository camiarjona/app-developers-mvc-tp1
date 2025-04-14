create database workers_jdbc;	
use workers_jdbc;

 create table developers(
	dev_dni long PRIMARY KEY,
    dev_name varchar(50),
    dev_surname varchar(50),
    dev_age int,
    main_language varchar(120),
    project_id int,
    foreign key (project_id) references projects (project_id)
    );
    
 create table desginers(
	des_dni long PRIMARY KEY,
    des_name varchar(50),
    des_surname varchar(50),
    des_age int,
    specialty varchar(120),
    project_id int,
    foreign key (project_id) references projects (project_id)
    );
    
create table projects(
 project_id int primary key auto_increment,
 pr_name varchar(50),
 pr_description varchar(150)
 );