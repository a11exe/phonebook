create table contacts (
  id      int8 not null,
  name    varchar(255),
  phone   varchar(255),
  user_id int8,
  primary key (id)
);

create table usr (
  id              int8    not null,
  active          boolean not null,
  email           varchar(255),
  password        varchar(255) not null,
  login           varchar(255) not null,
  name            varchar(255) not null,
  primary key (id)
);

alter table if exists contacts
  add constraint contacts_user_fk foreign key (user_id) references usr;
