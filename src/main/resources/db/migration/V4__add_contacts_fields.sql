ALTER TABLE contacts
  ADD surname varchar(255) NOT NULL,
  ADD middle_name varchar(255) NOT NULL,
  ADD phone_home varchar(255),
  ADD address varchar(255),
  ADD email varchar(255);

ALTER TABLE contacts RENAME COLUMN phone TO phone_mobile;