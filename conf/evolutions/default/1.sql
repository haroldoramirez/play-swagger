# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aluno (
  id                        bigint auto_increment not null,
  nome                      varchar(255),
  ra                        varchar(255),
  email                     varchar(255),
  constraint pk_aluno primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists aluno;

SET REFERENTIAL_INTEGRITY TRUE;

