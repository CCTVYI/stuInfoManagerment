-- 学生信息表

create table stu_info(
	stu_id varchar2(20) primary key,
	stu_name varchar2(20),
	stu_age int,
	stu_sex varchar2(10),
	stu_class varchar2(20)
);

--学生成绩表
create table stu_grades(
	stu_id varchar2(20) primary key,
	chinese number(5,2),
	math number(5,2),
	english number(5,2),
	svg  number(5,2),
	count number(5,2),
	constraint fk_stu_id foreign key(stu_id) references stu_info(stu_id) on delete set null
);


insert into stu_info values('A001','小刚',10,'男','一年级一班');
insert into stu_info values('B002','小红',12,'女','二年级三班');
insert into stu_info values('C003','小明',16,'男','三年级六班');

insert into stu_grades values('A001',88,95,100,94.33,283);
insert into stu_grades values('B002',72,66,83,73.66,221);
insert into stu_grades values('C003',95,85,95,91.66,275);