-- MemoryKind
create table memoryKind(
memkid int primary key, -- 1 mem 物理内存 2 Swap--> 硬盘交换分区
memoryKind VARCHAR(20)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;