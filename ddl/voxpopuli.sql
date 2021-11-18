drop table if exists comment;

drop table if exists event;

drop table if exists event_participant;

drop table if exists event_suggestion;

drop table if exists rating;

drop table if exists role;

drop table if exists suggestion;

drop table if exists user;

drop table if exists user_role;

/*==============================================================*/
/* table: comment                                               */
/*==============================================================*/
create table comment
(
   id_comment           int not null,
   id_suggestion        int not null,
   id_event_participant int not null,
   comment              varchar(1024) not null,
   primary key (id_comment)
);

/*==============================================================*/
/* table: event                                                 */
/*==============================================================*/
create table event
(
   id_event             int not null,
   name                 varchar(1024) not null,
   date                 datetime,
   location             varchar(1024),
   primary key (id_event)
);

/*==============================================================*/
/* table: event_participant                                     */
/*==============================================================*/
create table event_participant
(
   id_event_participant int not null,
   id_user              int not null,
   id_event             int not null,
   organizer            boolean not null,
   primary key (id_event_participant)
);

/*==============================================================*/
/* table: event_suggestion                                      */
/*==============================================================*/
create table event_suggestion
(
   id_event_suggestion  int not null,
   id_event             int not null,
   position             int not null,
   primary key (id_event_suggestion)
);

/*==============================================================*/
/* table: rating                                                */
/*==============================================================*/
create table rating
(
   id_rating            int not null,
   id_suggestion        int not null,
   id_event_participant int not null,
   rating               int not null,
   primary key (id_rating)
);

/*==============================================================*/
/* table: role                                                  */
/*==============================================================*/
create table role
(
   id_role              int not null,
   role                 varchar(1024) not null,
   primary key (id_role)
);

/*==============================================================*/
/* table: suggestion                                            */
/*==============================================================*/
create table suggestion
(
   id_suggestion        int not null,
   id_event_suggestion  int,
   title                varchar(1024) not null,
   url                  varchar(1024) not null,
   primary key (id_suggestion)
);

/*==============================================================*/
/* table: user                                                  */
/*==============================================================*/
create table user
(
   id_user              int not null,
   username             varchar(1024) not null,
   password             varchar(1024) not null,
   email                varchar(1024) not null,
   name                 varchar(1024) not null,
   last_name            varchar(1024) not null,
   banned               boolean not null,
   primary key (id_user)
);

/*==============================================================*/
/* table: user_role                                             */
/*==============================================================*/
create table user_role
(
   id_role              int not null,
   id_user              int not null,
   primary key (id_role, id_user)
);

alter table comment add constraint fk_comments foreign key (id_event_participant)
      references event_participant (id_event_participant) on delete restrict on update restrict;

alter table comment add constraint fk_relationship_6 foreign key (id_suggestion)
      references suggestion (id_suggestion) on delete restrict on update restrict;

alter table event_participant add constraint fk_contains foreign key (id_event)
      references event (id_event) on delete restrict on update restrict;

alter table event_participant add constraint fk_is foreign key (id_user)
      references user (id_user) on delete restrict on update restrict;

alter table event_suggestion add constraint fk_shows foreign key (id_event)
      references event (id_event) on delete restrict on update restrict;

alter table rating add constraint fk_gives foreign key (id_event_participant)
      references event_participant (id_event_participant) on delete restrict on update restrict;

alter table rating add constraint fk_relationship_7 foreign key (id_suggestion)
      references suggestion (id_suggestion) on delete restrict on update restrict;

alter table suggestion add constraint fk_relationship_8 foreign key (id_event_suggestion)
      references event_suggestion (id_event_suggestion) on delete restrict on update restrict;

alter table user_role add constraint fk_has foreign key (id_role)
      references role (id_role) on delete restrict on update restrict;

alter table user_role add constraint fk_has2 foreign key (id_user)
      references user (id_user) on delete restrict on update restrict;

