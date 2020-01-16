-- Create table
create table RC_DDLLOG
(
ddl_timestamp   DATE,
sysevent        VARCHAR2(100),
login_user      VARCHAR2(50),
instance_num    NUMBER,
database_name   VARCHAR2(50),
dict_obj_name   VARCHAR2(100),
dict_obj_type   VARCHAR2(100),
dict_obj_owner  VARCHAR2(50),
host            VARCHAR2(100),
ip              VARCHAR2(15),
os_user         VARCHAR2(50),
obj_current_ddl CLOB,
event_id        NUMBER
);

-- Create/Recreate indexes
create index IX_RC_DDLLOG_ID on DDLLOG (EVENT_ID);

grant select on RC_DDLLOG to public;

-- Create sequence
create sequence RC_DLLLOG_SEQ
minvalue 1
maxvalue 1000000000000
start with 1
increment by 1
cache 20;


create or replace trigger T_RC_DDLLOG_ID
before insert or update on rc_ddllog
for each row
declare
begin
    if (inserting and :new.event_id is null or :new.event_id = 0) then
    select RC_DLLLOG_SEQ.nextval into :new.event_id from dual;
end if ;
end ;


objdesc