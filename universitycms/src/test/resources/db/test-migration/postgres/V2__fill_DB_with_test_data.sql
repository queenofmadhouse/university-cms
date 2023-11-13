INSERT INTO university.classrooms (classroom_id)
VALUES (1);

INSERT INTO university.classrooms (classroom_id)
VALUES (2);

INSERT INTO university.courses (course_id, course_name, course_description)
VALUES (1, 'Math', 'Hard');

INSERT INTO university.courses (course_id, course_name, course_description)
VALUES (2, 'Biology', 'Hard');

INSERT INTO university.groups (group_id, group_name)
VALUES (1, 'A5');

INSERT INTO university.groups (group_id, group_name)
VALUES (2, 'B3');

INSERT INTO university.users (user_id, first_name, last_name, email, password)
VALUES (1, 'Alex', 'Kaplan', 'first@mail.com', 'passwd');

INSERT INTO university.teachers (user_id, department)
VALUES (1, 'Math Department');

INSERT INTO university.users (user_id, first_name, last_name, email, password)
VALUES (2, 'Boyana', 'Quell', 'second@mail.com', 'passwd');

INSERT INTO university.students (user_id, group_id)
VALUES (2, 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (1, 1, 2, 1, NOW()::DATE + interval '0 days' + '08:00:00'::interval, NOW()::DATE + interval '0 days' + '08:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (3, 1, 2, 1, NOW()::DATE + interval '1 days' + '08:00:00'::interval, NOW()::DATE + interval '1 days' + '08:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (4, 1, 2, 1, NOW()::DATE + interval '1 days' + '09:00:00'::interval, NOW()::DATE + interval '1 days' + '09:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (5, 1, 2, 1, NOW()::DATE + interval '1 days' + '10:00:00'::interval, NOW()::DATE + interval '1 days' + '10:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (6, 1, 2, 1, NOW()::DATE + interval '1 days' + '11:00:00'::interval, NOW()::DATE + interval '1 days' + '11:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (7, 1, 2, 1, NOW()::DATE + interval '1 days' + '12:00:00'::interval, NOW()::DATE + interval '1 days' + '12:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (8, 1, 2, 1, NOW()::DATE + interval '1 days' + '13:00:00'::interval, NOW()::DATE + interval '1 days' + '13:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (9, 1, 2, 1, NOW()::DATE + interval '1 days' + '14:00:00'::interval, NOW()::DATE + interval '1 days' + '14:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (10, 1, 2, 1, NOW()::DATE + interval '1 days' + '17:00:00'::interval, NOW()::DATE + interval '1 days' + '17:50:00'::interval, 'Description', 1);

INSERT INTO university.schedule (id, user_id, course_id, group_id, lesson_start, lesson_end, lesson_description, classroom_id)
VALUES (2, 1, 2, 1, NOW()::DATE + interval '5 days' + '08:00:00'::interval, NOW()::DATE + interval '5 days' + '08:50:00'::interval, 'Description', 2);

INSERT INTO university.teachers_courses_relation (user_id, course_id)
VALUES (1, 1);

INSERT INTO university.teachers_courses_relation (user_id, course_id)
VALUES (1, 2);

INSERT INTO university.groups_courses_relation (group_id, course_id)
VALUES (1, 1);

INSERT INTO university.groups_courses_relation (group_id, course_id)
VALUES (2, 1);
