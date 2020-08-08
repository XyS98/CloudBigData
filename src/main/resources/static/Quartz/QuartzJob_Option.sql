use YJB;
SELECT * FROM YJB.QRTZ_CRON_TRIGGERS;
update QRTZ_CRON_TRIGGERS set CRON_EXPRESSION = "0/10 * * * * ?" WHERE TRIGGER_NAME = 'myCronJobTrigger';
select * from YJB.QRTZ_JOB_DETAILS;
select * from YJB.QRTZ_FIRED_TRIGGERS;
delete from QRTZ_FIRED_TRIGGERS where TRIGGER_NAME = 'myCronJobTrigger';
SET SQL_SAFE_UPDATES = 0;
rollback;

select * FROM QRTZ_SCHEDULER_STATE;
select * from QRTZ_JOB_DETAILS;
select * from QRTZ_TRIGGERS;
select * from QRTZ_CRON_TRIGGERS;
select * from QRTZ_JOB_DETAILS;

-- 删除Cron_Job
delete from QRTZ_CRON_TRIGGERS where TRIGGER_NAME = 'myCronJobTrigger'; -- CRON外键约束
delete from QRTZ_TRIGGERS where TRIGGER_NAME = 'myCronJobTrigger';
delete from QRTZ_JOB_DETAILS where JOB_NAME='myCronJob';
-- 删除Simple_JOB
delete from QRTZ_SIMPLE_TRIGGERS where TRIGGER_NAME = 'myJobTrigger'; -- SIMPLETRIGGER外键约束
delete from QRTZ_TRIGGERS where TRIGGER_NAME = 'myJobTrigger';
delete from QRTZ_JOB_DETAILS where JOB_NAME='myJob';
