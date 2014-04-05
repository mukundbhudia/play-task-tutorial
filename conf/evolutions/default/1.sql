# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table project (
  id                        bigint not null,
  name                      varchar(255),
  folder                    varchar(255),
  constraint pk_project primary key (id))
;

create table task (
  id                        bigint not null,
  title                     varchar(255),
  done                      boolean,
  due_date                  timestamp,
  assigned_to_email         varchar(255),
  folder                    varchar(255),
  project_id                bigint,
  constraint pk_task primary key (id))
;

create table users (
  email                     varchar(255) not null,
  name                      varchar(255),
  password                  varchar(255),
  constraint pk_users primary key (email))
;


create table project_users (
  project_id                     bigint not null,
  users_email                    varchar(255) not null,
  constraint pk_project_users primary key (project_id, users_email))
;
create sequence project_seq;

create sequence task_seq;

create sequence users_seq;

alter table task add constraint fk_task_assignedTo_1 foreign key (assigned_to_email) references users (email) on delete restrict on update restrict;
create index ix_task_assignedTo_1 on task (assigned_to_email);
alter table task add constraint fk_task_project_2 foreign key (project_id) references project (id) on delete restrict on update restrict;
create index ix_task_project_2 on task (project_id);



alter table project_users add constraint fk_project_users_project_01 foreign key (project_id) references project (id) on delete restrict on update restrict;

alter table project_users add constraint fk_project_users_users_02 foreign key (users_email) references users (email) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists project;

drop table if exists project_users;

drop table if exists task;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists project_seq;

drop sequence if exists task_seq;

drop sequence if exists users_seq;

