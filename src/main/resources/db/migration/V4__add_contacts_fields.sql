ALTER TABLE contacts
  ADD surname varchar(255) NOT NULL,
  ADD middlename varchar(255) NOT NULL,
  ADD phonehome varchar(255),
  ADD address varchar(255),
  ADD email varchar(255);

ALTER TABLE contacts RENAME COLUMN phone TO phoneMobile;