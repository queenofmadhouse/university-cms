CREATE SCHEMA university;

CREATE TABLE university.users (
                                  user_id SERIAL PRIMARY KEY,
                                  first_name VARCHAR(255) NOT NULL,
                                  last_name VARCHAR(255) NOT NULL,
                                  email VARCHAR(255) NOT NULL UNIQUE,
                                  password VARCHAR(255) NOT NULL
);

CREATE TABLE university.groups (
                                   group_id SERIAL PRIMARY KEY,
                                   group_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE university.students (
                                     user_id INTEGER PRIMARY KEY,
                                     group_id INTEGER,
                                     FOREIGN KEY (user_id) REFERENCES university.users (user_id),
                                     FOREIGN KEY (group_id) REFERENCES university.groups (group_id)
);

CREATE TABLE university.teachers (
                                     user_id INTEGER PRIMARY KEY,
                                     department VARCHAR(255),
                                     FOREIGN KEY (user_id) REFERENCES university.users (user_id)
);

CREATE TABLE university.courses (
                                    course_id SERIAL PRIMARY KEY,
                                    course_name VARCHAR(255) NOT NULL UNIQUE,
                                    course_description TEXT
);

CREATE TABLE university.teachers_courses_relation (
                                                      id SERIAL PRIMARY KEY,
                                                      user_id INTEGER NOT NULL,
                                                      course_id INTEGER NOT NULL,
                                                      FOREIGN KEY (user_id) REFERENCES university.teachers (user_id),
                                                      FOREIGN KEY (course_id) REFERENCES university.courses (course_id),
                                                      UNIQUE (user_id, course_id)
);

CREATE TABLE university.schedule (
                                     id SERIAL PRIMARY KEY,
                                     user_id INTEGER NOT NULL,
                                     course_id INTEGER NOT NULL,
                                     group_id INTEGER NOT NULL,
                                     lesson_start TIMESTAMP NOT NULL,
                                     lesson_end TIMESTAMP NOT NULL,
                                     lesson_description TEXT,
                                     FOREIGN KEY (user_id) REFERENCES university.teachers (user_id),
                                     FOREIGN KEY (course_id) REFERENCES university.courses (course_id),
                                     FOREIGN KEY (group_id) REFERENCES university.groups (group_id)
);
