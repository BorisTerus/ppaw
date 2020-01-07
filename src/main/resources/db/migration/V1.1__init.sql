CREATE table students
(
    id         serial primary key,
    first_name text,
    last_name  text
);

create table courses
(
    id          serial primary key,
    name        text,
    description text
);

create table grades
(
    id         serial primary key,
    course_id  int references courses (id),
    student_id int references students (id),
    grade      int
);

create unique index courses_index on courses (name);
create unique index grades_index on grades (course_id, student_id);

insert into students(first_name, last_name)
values ('Boris', 'Terus');
insert into students(first_name, last_name)
values ('Stefan', 'Apopei');
insert into students(first_name, last_name)
values ('Bogdan', 'Olariu');