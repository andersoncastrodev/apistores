create table category (
  id serial not null,
  description varchar(100),
  date_register date,
  date_update date,
  constraint category_pk primary key (id)
);