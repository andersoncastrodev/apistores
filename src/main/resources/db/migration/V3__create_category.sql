create table category (
  id serial not null,
  description varchar(100),
  date_register date not null,
  date_update date not null,
  constraint category_pk primary key (id)
);