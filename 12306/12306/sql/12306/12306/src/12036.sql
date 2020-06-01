-----------------------------------------------------------------------------------------------��·�����-----------------------------------------------------------------------------------------

drop table railway;
create table railway(
       railway_name varchar2(50) not null,--��·��
       railway_bh int not null,
       railway_station varchar2(20)
); 
select * from railway;
create sequence seq
select railway_station from railway where railway_bh>(select railway_bh from railway where railway_station='����') and railway_name='������'
select railway_bh from railway where railway_station='����'
insert into railway values('������',1,'����');
insert into railway values('������',2,'����');
insert into railway values('������',3,'ʯ��ׯ');
insert into railway values('������',4,'֣��');
insert into railway values('������',5,'�人');
insert into railway values('������',6,'��ɳ');
insert into railway values('������',7,'����');
insert into railway values('������',8,'����');
insert into railway values('������',9,'����');
insert into railway values('������',10,'����');
select railway_name from railway
union 
select railway_name from railway
--��ѯ�����е���·��
select railway_name from  railway union select railway_name from  railway 
delete from addbian where railway_name='aa'
select tid,basc from addbian
select *from addbian
commit
select railway_bh from railway where railway_station='����'--��ѯ�����
select *from railway;
select railway_bh ,railway_station from railway where railway_name='������' order by 1--��������
-------------------------------------------------------����г�������·����---------------------------------------------------
select s.s_statname,d.railway_bh from stationinfo s inner join railway  d on(s.s_statname=d.railway_station)  where railway_name='������' order by 2
DROP table addbian;
create table addbian(
       railway_name varchar2(20)  not null,                 
       tid varchar(10) not null      ,    
       basc varchar(10) not null
       );           
       select *from addbian 
 select *from addbian s
 left join railway d on(s.tid=d.railway_name)
insert into addbian values('������','G1001','asc');
insert into addbian values('������','G1002','asc');
insert into addbian values('������','G1003','asc');
insert into addbian values('������','k1002','desc');
insert into addbian values('������','k1001','desc');
commit;
select tid,d.railway_station ,d.railway_bh ����վ,e.railway_station ,e.railway_bh ����վ ,basc  from addbian s
inner join  railway d on (d.railway_name= s.railway_name)
inner join  railway e on(d.railway_name=e.railway_name) where d.railway_station='����'and e.railway_station='����' --�����Ǻ����������վ���յ�վ�Ϳ��Բ�ѯ���������г�
--��ѯ�����վ���յ�վ�ı�ţ�Ϊ��ȷ����˳��������ʹ��
select d.railway_bh ���վ ,f.railway_bh �յ�վ from railway d inner join railway f on(d.railway_name=f.railway_name) where d.railway_station='����' and f.railway_station='����';
--ͨ�����sql�����бȽ�֮��Ϳ���֪�����˳�������������



 
select railway_station from railway where railway_bh>(select railway_bh from railway where railway_station='�人')
commit
------------------------------------------------------------------------------------�û���Ϣ��_------------------------------------------------------------------------------------------
drop table userinfo;
drop sequence seq_userinfo_id
create sequence seq_userinfo_id start with 1001 increment by 1;
create table userInfo(
       usid int primary key, --�û����
       uname varchar2(60) not null unique --�û���
           constraint CK_userInfo_uname check(length(uname) between 2 and 10),
       pwd varchar2(40) not null   --�û�����
           constraint CK_userInfo_pwd check(length(pwd)>=2),
       email varchar2(30) not null   --����  
           constraint CK_passengerInfo_email check((instr(email,'@')>0) and (instr(email,'.')>0)),
       balance number(8,2) default 0,
       question varchar2(100),--����
       answer varchar2(100)--��
);
select *from userinfo ;

insert into userinfo values(seq_userinfo_id.nextval,'super','abc','2991180057@qq.com',200,'hello?','hi');
insert into userinfo values(seq_userinfo_id.nextval,'����','aaa','2363582258@qq.com',200,'�����ѧ��������ʦ�̵���','�ǵ�')
select usid,uname,email,balance from userinfo where email = '2363582258@qq.com';--�����������鿴���е���Ϣ
select usid from userinfo where uname = 'superice'--�����������鿴���е���Ϣ
select pwd from userinfo where email = '2363582258@qq.com';--�����������鿴���е�����
select *from userinfo where uname='super';
select question from userinfo where uname = 'superice';
select answer from userinfo where uname = 'superice';
select email from userinfo where uname = 'superice';

update userinfo set pwd = where uname = 'superice';--�����û������޸�����

commit
-----------------------------------------------�˿���Ϣ��-------------------------------------------------------------------
drop table passengerInfo1; 
drop sequence seq_passenge;
create sequence seq_passenge start with 1001 increment by 1;
select *from passengerinfo1
create table passengerInfo1(
       pid int primary key, --�˿ͱ��
       pname varchar(20) not null,  --�˿�����
       sex varchar(4) not null,  --�˿��Ա�
           constraint CK_passengerlInfo_sex check(sex='��' or sex='Ů'), 
       carId varchar2(30) not null , --�˿����֤
       tel varchar2(15),   --�˿��ֻ���
       ttl varchar2(20) --�����ֶ�
);
--��ӳ˿���Ϣ
 insert into passengerinfo1 values(seq_passenge.nextval,'����','��','432503199709214014','16673478081',null);
 insert into passengerinfo1 values(seq_passenge.nextval,'���㽡','��','330327199810127235','13115816167',null);
 --ͨ���˿ͱ��ʵʱƱ�����±����������Կ�
 select r_id, kid,pname,calender, s_loclation,s_getloc,softseAtp,carriage,seat ,status from rraletime s
 left join passengerinfo1 d on(d.pid=s.pid)
  
 select pname,carId from passengerInfo1;
select *from passengerInfo1
--�������֤��Ϣ��ȡ�˿ͱ��
select pid from passengerInfo1 where carId = 330327199810127235
DELETE from trainTime where ttid=1003;
commit;
-----------------------------------------------------�г�ʱ�̱�----------------------------------------------------------------
create sequence seq_train_ttid start with 1001 increment by 1;
drop sequence seq_train_ttid;
drop table trainTime;
create table trainTime(
       tid varchar2(10) primary key   ,   --����
       starts varchar2(10) not null,  --����վ         
       arrives varchar2(10) not null , --����վ
       sleeperp number(8,2) ,  --����Ʊ��
       softseatp number(8,2),  --����Ʊ��
       hardseatp number(8,2),  --Ӳ��Ʊ��
       gotime varchar2(20),  --����ʱ��
       arrivetime varchar2(20) --����ʱ��
);
select s.s_statname,d.railway_bh from stationinfo s inner join railway  d on(s.s_statname=d.railway_station)  where s_name='G1001' order by 2
select *from trainTime;
select *from trainTime;
insert into trainTime values('G1001','����','����',763.7,536,352,'2019-9-1 2:30','2019-9-1  5:60');
insert into trainTime values('G1002','����','����',763.7,536,352,'2019-9-1  6:30','2019-9-1  11:60');
insert into trainTime values('G1003','֣��','����',763.7,536,352,'2019-9-1 6:30',' 2019-9-1 11:60');

commit;
-----------------------------------------------------ʵʱƱ�����±�----------------------------------------------------------------
alter table rraletime drop constraint FK_tictInfoj_passgerInfo_pid
drop sequence seq_
create sequence seq_rraltime_udate start with 10000001 increment by 1; 
drop table rraletime;
select max(kid) from rraletime;
select 100-count(*) GG from rraletime where softseatp='����' and not ( 1> s_getloc or  10 <s_loclation ) and r_id='G1001'  and calender='2019-1-11'
select *from rraletime;
--��ʵʱƱ�����±�ͳ˿ͱ�ȥ������һ�ԣ���������ͬ�������˵�����
select *from rraletime s
inner join passengerinfo1 d on(s.pname=d.pname);
create table rraletime(
         kid int primary key, --������
                pname varchar(10) ,--�˿�
       --  constraint FK_tictInfoj_passgerInfo_pid references passengerInfo(pid),
       r_id varchar2(10)  ,--�г����
       s_loclation int,--�г�����վ
       s_getloc int,--�г�����վ
       calender varchar(10),--����г�������
         softseatp varchar2(10),--��λ��ϯ��ͨ�����ӵ��г���Ϣ����Բ�ѯ����λ��Ʊ��
         ticket_price varchar2(10),--Ʊ��
         ticket_species varchar2(10),--Ʊ��
         carriage varchar2(10) ,--����
         caid varchar2(20),--�˿����֤����
         --�¼���
         orderup varchar2(20),--�µ�ʱ��
         seat varchar2(10) ,--��λ
       status int --��Ʊ״̬   0,δ֧����1����֧����2����Ʊ��3.��ǩ
);
insert into rraletime values(seq_rraltime_udate.nextval,'����','G1001',1,6,'2019-1-1','Ӳ��',180,'����Ʊ',1,'432503199709214014','26',0);
delete from rraletime where kid=10000020
--��˿ͱ����ӵĹҾ���Ҫ���˿ͱ�Ÿı�һ��
select r_id ,s.kid,d.pname,calender,s_loclation,s_getloc,softseatp,ticket_species,ticket_price,carriage,seat,status from passengerInfo1 d right  join rraletime s on(d.pname=s.pname)
 union 
 select r_id,s. kid,d.pname,calender,s_loclation,s_getloc,softseatp,ticket_species,ticket_price,carriage,seat,status from passengerInfo1 d right  join rraletime s on(d.pname=s.pname)
select r_id,kid ,d.pname,calender,s_loclation,s_getloc,softseatp,ticket_species,ticket_price,carriage,seat,status from passengerInfo1 d right  join rraletime s on(d.pname=s.pname) union select r_id,kid,d.pname,calender,s_loclation,s_getloc,softseatp,ticket_species,ticket_price,carriage,seat,status from passengerInfo1 d right  join rraletime s on(d.pname=s.pname)
commit
--��¥������ж�ѡ�յ�վ�Ŀ������ѡ����Щվ����������������ǻ�����ѡ��
select railway_station from railway where railway_bh<(select railway_bh from railway where railway_station='����') and railway_name='������';
select railway_station from railway whe6re railway_bh>(select railway_bh from railway where railway_station='����') and railway_name='������';

select *from rraletime
delete from rraletime where kid=10000017
create
--ͳ��ʵʱ��Ʊ����Ϣ
select 100-count(*) from rraletime where softseatp='Ӳ��' and not ( 4> s_getloc or  7 <s_loclation ) and r_id='G1001' and calender='2019-1-1' and (status=1 or status=0)

insert into rraletime values(seq_rraltime_udate.nextval,1001,?,?,?,?,?,1,null,0)
insert into rraletime values(seq_rraltime_udate.nextval,1003,'G1001',1,6,'1028-10-11','����','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1002',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1003',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1002',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1001',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1003',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1002',1,6,'2019-9-1','Ӳ��','1');
insert into rraletime values(seq_rraltime_udate.nextval,'G1003',1,6,'2019-9-1','Ӳ��','1');
commit;

-----------------------------------------------------վ����Ϣ��----------------------------------------------------------------
drop table stationInfo;
delete from stationinfo where s_name='G1001' and s_statname='';
create sequence seq_station_ttd start with 1001  increment by  1;
drop sequence seq_station_ttd;
delete table stationinfo;
drop table stationinfo;
select s.s_statname,d.railway_bh from stationinfo s 
inner join railway  d on(s.s_statname=d.railway_station)  where s_name='G1002' order by 2
delete from stationinfo where s_name='G1001';
create table stationInfo(
       s_name varchar2(20),--�г���
       s_statname varchar2(20),--վ̨��
       s_time1 varchar2(20) ,--�г�����ʱ��
       s_time2 varchar2(20),--�г�������ʱ��
       s_sprice number(5,2),--�г����յ�վ�ļ۸�
         s_lprice number(5,2),--�г����յ�վ�ļ۸�
           s_price number(5,2),--�г����յ�վ�ļ۸�
       s_add varchar2(20) --Ԥ���ֶ�
);
select tid,s.s_time1,d.s_time2,s.s_statname gs ,d.s_statname ge ,s.s_sprice-d.s_sprice ���� ,s.s_lprice-d.s_lprice ���� ,s.s_price-d.s_price Ӳ�� from stationinfo s 
				 inner join stationinfo d on(s.s_name=d.s_name)  
				inner join addbian e on(d.s_name=e.tid) where s.s_statname='����' and d.s_statname='����'
select d.s_sprice-s.s_sprice ����,d.s_lprice-s.s_lprice ����,d.s_price-s.s_price Ӳ��    from stationinfo s inner join stationinfo d on(s.s_name=d.s_name) where s.s_name='G1003' and s.s_statname='����' and d.s_statname='����';

rollback
--վ̨��Ϣ��
COMMIT;
--����ˮ������ļ۸�
select   max(s.s_sprice-d.s_sprice)  from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname='����' and d.s_statname='����'
select   s.s_time2 ,d.s_time1  from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname='����' and d.s_statname='����' 
select   * from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname='����' and d.s_statname='����'

select  *from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname='����' and d.s_statname='����' and s.s_name='G1002'
select   * from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name)  
select *from stationinfo;
commit;
--����������վ���յ�վ���ԾͿ��Եĵ��۸���Ϣ������ĺ��Ĵ��������·dao������Ĵ�����
select * from stationinfo s  inner join stationinfo d on(s.s_name=d.s_name)  inner join addbian e on(d.s_name=e.tid)
inner join addbian f on(f.tid=d.s_name)
 where s.s_statname='����' and d.s_statname='����' 

select *from stationinfo s

select tid,s.s_time1,d.s_time2,s.s_statname gs ,d.s_statname ge ,s.s_sprice-d.s_sprice ���� ,s.s_lprice-d.s_lprice ���� ,s.s_price-d.s_price Ӳ�� from stationinfo s 
		 inner join stationinfo d on(s.s_name=d.s_name)  
			inner join addbian e on(d.s_name=e.tid) where s.s_statname='����' and d.s_statname='����'
commit
select *from addbian
select *from stationinfo where s_name='G1001';
select *from stationinfo where s_name='G1001';
commit;
delete from stationinfo where s_name='G1001' and s_time1='2019-1-11 11:0';
update stationinfo set   s_bianhao=12,  s_name='����' where ttd=1021;
insert into stationinfo values('k1001','����',' 2018-9-1 2:30','2018-9-1 2:30 ',763,536,352,null);
insert into stationinfo values('k1001','����','2018-9-1 06:04','2018-9-1 06:09',725,500,300,null);
insert into stationinfo values('k1001','����','2018-9-1 07:29','2018-9-1 07.35',700,450,232,null);
insert into stationinfo values('k1001','��ɳ','2018-9-1 09:44','2018-9-1 09:47',625,430,210,null);
insert into stationinfo values('k1001','�人','2018-9-1 10:20','2018-9-1 10:23',600,415,200,null);
insert into stationinfo values('k1001','֣��','2018-9-1 11:21','2018-9-1 11:44',575,400,192,null);
insert into stationinfo values('k1001','����','2018-9-1 13:21','2018-9-1 13:44',575,400,192,null);
insert into stationinfo values('k1001','����','2018-9-1 15:21','2018-9-1 15:21',575,400,192,null);
insert into stationinfo values('G1001','����',' 2018-9-1 2:30','2018-9-1 2:30 ',763,536,352,null);
insert into stationinfo values('G1001','����','2018-9-1 06:04','2018-9-1 06:09',725,500,300,null);
insert into stationinfo values('G1001','֣��','2018-9-1 07:29','2018-9-1 07.35',700,450,232,null);
insert into stationinfo values('G1001','�人','2018-9-1 09:44','2018-9-1 09:47',625,430,210,null);
insert into stationinfo values('G1001','��ɳ','2018-9-1 10:20','2018-9-1 10:23',600,415,200,null);
insert into stationinfo values('G1001','����','2018-9-1 11:21','2018-9-1 11:44',575,400,192,null);
insert into stationinfo values('G1001','����','2018-9-1 13:21','2018-9-1 13:44',575,400,192,null);
insert into stationinfo values('G1001','����','2018-9-1 15:21','2018-9-1 15:21',575,400,192,null);

insert into stationinfo values('G1002','����',' 2018-9-1 2:30','2018-9-1 2:30 ',763,536,352,null);

insert into stationinfo values('G1002','����','2018-9-1 06:04','2018-9-1 06:09',725,500,300,null);
insert into stationinfo values('G1002','֣��','2018-9-1 07:29','2018-9-1 07.35',700,450,232,null);
insert into stationinfo values('G1002','�人','2018-9-1 09:44','2018-9-1 09:47',625,430,210,null);
insert into stationinfo values('G1002','��ɳ','2018-9-1 10:20','2018-9-1 10:23',600,415,200,null);
insert into stationinfo values('G1002','����','2018-9-1 11:21','2018-9-1 11:44',575,400,192,null);
insert into stationinfo values('G1003','����','2018-9-1 13:21','2018-9-1 13:21',0,0,0,null);
insert into stationinfo values('G1003','֣��',' 2018-9-1 16:30','2018-9-1 16:30 ',763,536,352,null);
insert into stationinfo values('G1003','�人','2018-9-1 21:35','2018-9-1 18:09',725,500,300,null);

insert into stationinfo values('G1003','��ɳ','2018-9-1 21:35','2018-9-1 21:37',700,450,232,null);
insert into stationinfo values('G1003','����','2018-9-2 2:40','2018-9-2 2:47',625,430,210,null);
insert into stationinfo values('G1003','����','12018-9-2 4:23','null',600,415,200,null);
--�������վ������վ�ĳ���ʱ��͵���ʱ��

commit;

-------------------------------------------------------------ʵʱƱ�����±�---------------------------------------------------------------------------
create sequence seq_rraletime start with 01 increment by 1;


select * from rraletime;
select * from rraletime where 5<=s_loclation and 5<=s_getloc
select d.railway_bh Startsta ,f.railway_bh staend from railway d 
				inner join railway f on(d.railway_name=f.railway_name)
				where d.railway_station='����' and f.railway_station='����'

------------------------------------------------------�г���Ϣ��-------------------------------------------------------
drop table traininfo;
create table traininfo(
       ttid varchar2(10) ,
       softseatp varchar2(20) not null,--��λϯ��
       gotime date,--�г���Ӫ��ʱ��
       arrivetime date ,--�г�������Ӫ��ʱ��
       soft1seatp int not null --��Ӧ��Ʊ��
);
insert into traininfo values('G1001','����',null,null,50);
insert into traininfo values('G1001','����',null,null,100);
insert into traininfo values('G1001','Ӳ��',null,null,200);
commit

insert into traininfo values('G1001','����',null,null,50);
insert into traininfo values('G1001','����',null,null,100);
insert into traininfo values('G1001','Ӳ��',null,null,200);
update  traininfo set softseatp='����' where ttid='G1001';

commit
select * from traininfo s
full join rraletime d on(d.r_id=s.ttid)
select *from traininfo where softseatp='����'
insert into traininfo values('����',to_date('1028-10-11' ,'yyyy-MM--dd'),'1028-10-11'200);
select *from traininfo;
commit;

select *from rraletime;
                  
select COUNT(*) FROM RRALETIME;


-----------------------------------------------------��Ʊ��Ϣ��----------------------------------------------------------------
drop table returnTicket;

create table returnTicket(
      rid int primary key,  --��� 
      kid int ,  --������
         --constraint Fk_returnTicket_ticketInfo_kid references ticketInfo(kid),
      td varchar2(35) not null--�˶�ʱ�� 
);
drop sequence seq_returnTicket_rid
create sequence seq_returnTicket_rid start with 1001 increment by 1;
commit;

select *from returnTicket;

-----------------------------------------------------����Ա��Ϣ��----------------------------------------------------------------
drop table adminInfo;

create table adminInfo(
       aname varchar2(60) not null unique, --����Ա��
       apwd varchar2(40) not null    --����
);
select *from admininfo


insert into adminInfo values('admin','a');
commit;

select *from adminInfo;
commit;


select d.railway_bh ���վ ,f.railway_bh �յ�վ from railway d inner join railway f on(d.railway_name=f.railway_name) where d.railway_station='����' and f.railway_station='����'


--test
select d.pid,kid,pname,calender,s_loclation,s_getloc,softseatp,carriage,seat,status from passengerInfo1 d left join rraletime s on(d.pid=s.pid)
 select r_id, kid,pname,calender, s_loclation,s_getloc,softseAtp,carriage,seat ,status from rraletime s
 left join passengerinfo1 d on(d.pid=s.pid)
 
 select   max(s.s_sprice-d.s_sprice)  from stationinfo s inner join stationinfo d  on(s.s_name=d.s_name) where s.s_statname='����' and d.s_statname='����'
 
 select d.s_sprice-s.s_sprice ����,d.s_lprice-s.s_lprice ����,d.s_price-s.s_price Ӳ��    from stationinfo s
				inner join stationinfo d on(s.s_name=d.s_name) where s.s_name='G1003'
			and s.s_statname='����'  and d.s_statname='����'
      select soft1seatp from traininfo where softseatp='����' and ttid='G1001';
      select soft1seatp from traininfo;
 select *from traininfo where 
 select tid,s.s_time1,d.s_time2,s.s_statname gs ,d.s_statname ge ,s.s_sprice-d.s_sprice ���� ,s.s_lprice-d.s_lprice ���� ,s.s_price-d.s_price Ӳ�� from stationinfo s 
        inner join stationinfo d on(s.s_name=d.s_name)  
			  inner join addbian e on(d.s_name=e.tid) where s.s_statname='����' and d.s_statname='����' and basc='asc'
 
