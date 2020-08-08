-- 针对CloudBigData进行数据的操作
use YJB;
select * from lastedMemoryInfo;
delete from lastedMemoryInfo;
select * from  memoryInfo;
select * from  memoryInfo where linux_id = 3002;

delete from memoryInfo;
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

-- drop trigger memoryTrigger;
DELIMITER $  -- 将触发器结束符号从;替换为$ 
create trigger memoryTrigger
after insert on memoryInfo   -- 在完成插入数据到memoryInfo表后触发
for each row
begin
    -- 同步新数据(插入的两条新数据ex:ID 4001,ID4002 中只会自动插入最后的一条数据 ID:4002)
    -- 相同结构的两张表可以直接将A表查询的结果集直接插入B表
	DELETE FROM lastedMemoryInfo where linux_id = new.linux_id;
	insert into lastedMemoryInfo select * from memoryInfo where linux_id=new.linux_id; 
	insert into lastedMemoryInfo select * from memoryInfo where memID=new.memID-1; -- 插入两条新数据中第一条(ID:4001)
end$
DELIMITER ;-- 将触发器结束符号还原为为; 

rollback;

select * from memoryInfo where memID>6980;
select * from lastedMemoryInfo;
insert into lastedMemoryInfo values(4001,3001,1,12057,1000,3047,null,null,null,'2020-08-03 17:00:26');
insert into lastedMemoryInfo values(4002,3001,2,1057,1000,2047,null,null,null,'2020-08-03 17:00:27');
delete from memoryInfo;
delete from lastedMemoryInfo;

