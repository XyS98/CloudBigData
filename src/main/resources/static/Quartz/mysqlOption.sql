-- 针对CloudBigData进行数据的操作

select * from lastedMemoryInfo;
show tables;
create table lastedMemoryInfo(
memID int primary key,          -- memroy表内存 (primary Key)
linux_id int,    -- (fk-->linuxsInfo)主机id 
memkid int ,     -- (fk -->memroyKind)内存类型: 1 mem --> 物理内存 2 Swap--> 硬盘交换分区
total int ,          --  used+free
used int ,           -- 分配给缓存（包含buffers 与cache ）使用的数量
free int ,           -- 未被使用的物理内存数量
shared int ,         -- 被共享使用的物理内存大小
buffORcache int ,    -- 被 buffer 和 cache 使用的物理内存大小
available int,    -- 还可以被 应用程序 使用的物理内存大小 
selectedTime varchar(40), -- Linux主机通过SSH工具获得命令返回信息的时间
constraint fk_linuxsInfo_lastedMemoryInfo_linux_id foreign key(linux_id) references linuxsInfo(linux_id),
constraint fk_memoryKind_lastedMemoryInfo_memkid foreign key(memkid) references memoryKind(memkid) 
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 对MemoryInfo创建触发器，当Insert操作后触发，获取insert的内容
DELIMITER $
create trigger addLastedMemoryInfo 
after insert on memoryInfo   -- 在完成插入数据到memoryInfo表后触发
for each row
begin
if exists(select memID from	lastedMemoryInfo l,memoryInfo m where l.memID=m.memID - 2) then
	delete from lastedMemoryInfo where l.memID=m.memID-2; -- 说明表中已有旧数据，删除插入新数据
else  -- 否则说明该表为空，直接插入新数据
	insert into lastedMemoryInfo select * from memoryInfo where memID=new.memID;
end if;
end$
DELIMITER ;

drop trigger addLastedMemInfo;

DELIMITER $
create trigger addLastedMemInfo 
after insert on memoryInfo   -- 在完成插入数据到memoryInfo表后触发
for each row
begin
	delete from lastedMemoryInfo where memID>0;
    -- 同步新数据(插入的两条新数据ex:ID 4001,ID4002 中只会自动插入最后的一条数据 ID:4002)
	insert into lastedMemoryInfo select * from memoryInfo where memID=new.memID; 
	insert into lastedMemoryInfo select * from memoryInfo where memID=new.memID-1; -- 插入两条新数据中第一条(ID:4001)
end$
DELIMITER ;

drop trigger deleteOldMemInfo;

DELIMITER $
create trigger deleteOldMemInfo 
before insert on memoryInfo   -- 在完成插入数据到memoryInfo表后触发
for each row
begin
	delete from lastedMemoryInfo where memID>0;
end$
DELIMITER 
delete from lastedMemoryInfo where memID>0;
delete from memoryInfo where memID>0;

select * from memoryInfo;
select * from lastedMemoryInfo;
insert into memoryInfo values(null,3002,2,2057,0,3047,null,null,null,'2020-08-01 18:00:26');
insert into memoryInfo values(null,3002,2,3057,0,4047,null,null,null,'2020-08-01 18:00:27');


