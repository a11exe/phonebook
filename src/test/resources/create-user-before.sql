delete from contacts;
delete from usr;

insert into usr(id, login, name, password, active, email)
values
  (100, 'user1', 'user1', '12345', true, 'user1@mail.com'),
  (102, 'user2', 'user2', '12345', true, 'user2@mail.com'),
  (103, 'user3', 'user3', '12345', true, 'user3@mail.com');

create extension if not exists pgcrypto;

update usr set password = crypt(password, gen_salt('bf', 8));