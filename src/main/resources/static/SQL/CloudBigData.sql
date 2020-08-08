show databases;

create database YJB;

use YJB;
show tables;

-- 创建表
create table adminInfo(
aid int primary key auto_increment,
aname varchar(20),
pwd varchar(20),
phone varchar(20)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

select * from linuxInfo;
show tables;
-- 主机信息
-- drop table linuxInfo;
create table linuxInfo(
linux_id int primary key auto_increment,
linux_name varchar(40),
linux_ip  varchar(40) ,
linux_port int,
linux_userName varchar(40),
linux_passward varchar(40),
tips 		text
)ENGINE=InnoDB AUTO_INCREMENT=3001 DEFAULT CHARSET=utf8;
set FOREIGN_KEY_CHECKS=1;
insert into linuxInfo values(null,'e3base01','192.168.6.131',22,'root','root','大数据基础平台所属主机');
insert into linuxInfo values(null,'e3base02','192.168.6.132',22,'root','root','大数据基础平台所属主机');
insert into linuxInfo values(null,'e3base03','192.168.6.133',22,'root','root','大数据基础平台所属主机');
insert into linuxInfo values(null,'e3base04','192.168.6.134',22,'root','root','大数据基础平台所属主机');
insert into linuxInfo values(null,'e3base05','192.168.6.135',22,'root','root','大数据基础平台所属主机');
select * from linuxInfo;
select linux_id,linux_name,linxu_ip,linux_userName,linux_passward from linuxInfo;


-- 设置主键从 1001 自增
alter table adminInfo  auto_increment=1001;
insert into adminInfo(aname,pwd,phone) values('MrA','071899%ab','13468989115');
insert into adminInfo(aname,pwd,phone) values('MrB','071899%ac','13448669112');
select * from adminInfo;
rollback;
commit;
-- drop table memoryKind;
-- MemoryKind
create table memoryKind(
memkid int primary key , -- 1 mem 物理内存 2 Swap--> 硬盘交换分区
memKind VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into memoryKind values(1,'mem');
insert into memoryKind values(2,'Swap');
select * from memoryKind;


-- MemoryInfo

create table memoryInfo(
memID int primary key auto_increment,          -- memroy表内存 (primary Key)
linux_id int,    -- (fk-->linuxInfo)主机id 
memkid int ,     -- (fk -->memroyKind)内存类型: 1 mem --> 物理内存 2 Swap--> 硬盘交换分区
total int ,          --  used+free+buffORcache
used int ,           -- 分配给缓存（包含buffers 与cache ）使用的数量
free int ,           -- 未被使用的物理内存数量
shared int ,         -- 被共享使用的物理内存大小
buffORcache int ,    -- 被 buffer 和 cache 使用的物理内存大小
available int,    -- 还可以被 应用程序 使用的物理内存大小 
selectedTime varchar(40), -- Linux主机通过SSH工具获得命令返回信息的时间
constraint fk_linuxInfo_linux_id foreign key(linux_id) references linuxInfo(linux_id),
constraint fk_memoryKind_memkid foreign key(memkid) references memoryKind(memkid) 
)ENGINE=InnoDB AUTO_INCREMENT=4001 DEFAULT CHARSET=utf8;
insert into memoryInfo values(null,3001,1,1823,270,1103,8,448,1376,"2020-08-01 16:15:22");
insert into memoryInfo values(null,3001,2,2047,0,2047,null,null,null,'2020-08-01 16:15:40');
insert into memoryInfo values(null,3002,2,2047,0,2047,null,null,null,'2020-08-01 16:16:22');
insert into memoryInfo values(null,3002,2,2047,0,2047,null,null,null,'2020-08-01 17:15:22');
insert into memoryInfo values(null,3001,2,3,4,5,6,7,8,null);
insert into memoryInfo (linux_id, memkid, total, used, free, shared,buffORcache, available ,selectedTime) values(3001,1,1,2,3,4,5,6,null);
select * from  memoryInfo where selectedTime between('2020-08-01 16:15:25')and ('2020-08-01 17:15:20');
select * from  memoryInfo where used = 330;
-- delete  from memoryInfo;
select * from  memoryInfo;
alter table memoryInfo  auto_increment = 4001;
set SQL_SAFE_UPDATES = 0;
-- drop table memoryInfo;

drop table  machineStats;
select * from machineStats;
-- Linux 信息
create table machineStats(
stats_id int primary key auto_increment,
linux_id int,
ip varchar(40),
cpuUsage varchar(40),
loads varchar(40),
traffic varchar(40), --  统计当前网络流量
memoryUsageRatio varchar(40),
memoryFree varchar(40),
memoryAllocated varchar(40),
memoryTotal varchar(40), 
selectedTime varchar(40),
diskUsageMap varchar(2000),
constraint fk_linuxInfo_machineStats foreign key(linux_id) references linuxInfo(linux_id)
)ENGINE=InnoDB AUTO_INCREMENT=5001 DEFAULT CHARSET=utf8;
select * from machineStats;
ALTER TABLE machineStats ADD selectedTime varchar(40) AFTER memoryTotal;
-- 存储 machineStats最新插入的一批数据
create table lastedStats(
stats_id int primary key auto_increment,
linux_id int,
ip varchar(40),
cpuUsage varchar(40),
loads varchar(40),
traffic varchar(40), --  统计当前网络流量
memoryUsageRatio varchar(40),
memoryFree varchar(40),
memoryAllocated varchar(40),
memoryTotal varchar(40), 
selectedTime varchar(40),
diskUsageMap varchar(2000),
constraint fk_linuxInfo_lastedStats foreign key(linux_id) references linuxInfo(linux_id)
)ENGINE=InnoDB AUTO_INCREMENT=5001 DEFAULT CHARSET=utf8;
select * from lastedStats;
select * from machineStats;
delete from lastedStats where stats_id>0;
delete from machineStats where stats_id>0;

DELIMITER $  -- 将触发器结束符号从;替换为$ 
create trigger statsTrigger
after insert on machineStats   -- 在完成插入数据到memoryInfo表后触发
for each row
begin
	delete from  lastedStats where linux_id=new.linux_id;
	INSERT into lastedStats select * from machineStats where stats_id=new.stats_id; 
end$
DELIMITER ;-- 将触发器结束符号还原为为; 

DROP trigger statsTrigger;