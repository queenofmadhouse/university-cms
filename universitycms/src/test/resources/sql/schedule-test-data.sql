INSERT INTO university.users (user_id, first_name, last_name, email, password)
VALUES (1, 'Alex', 'Kaplan', 'mail@mail.com', 'passwd');

INSERT INTO university.teachers (user_id, department)
VALUES (1, 'Math Department');

INSERT INTO university.courses (course_id, course_name, course_description)
VALUES (1, 'Math', 'Hard');

INSERT INTO university.courses (course_id, course_name, course_description)
VALUES (2, 'Biology', 'Hard');

INSERT INTO university.groups (group_id, group_name)
VALUES (1, 'A5');

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description)
VALUES (1, 1, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL '1' HOUR, 'Description');

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description)
VALUES (2, 1, 2, 1, CURRENT_TIMESTAMP + INTERVAL '5' DAY, CURRENT_TIMESTAMP + INTERVAL '5' DAY + INTERVAL '1' HOUR, 'Description');

INSERT INTO university.teachers_courses_relation (user_id, course_id)
VALUES (1, 1);

INSERT INTO university.teachers_courses_relation (user_id, course_id)
VALUES (1, 2);
