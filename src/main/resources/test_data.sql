INSERT INTO pna6_11.classroom (classroom_id, classroom_name) VALUES (1, '1');
INSERT INTO pna6_11.classroom (classroom_id, classroom_name) VALUES (2, '11');

INSERT INTO pna6_11.subject (subject_id, subject_name) VALUES (1, 'Math');
INSERT INTO pna6_11.subject (subject_id, subject_name) VALUES (2, 'English');


INSERT INTO pna6_11.sudent_batch (student_batch_id, student_batch_name) VALUES (1, '2a');
INSERT INTO pna6_11.sudent_batch (student_batch_id, student_batch_name) VALUES (2, '1a');
INSERT INTO pna6_11.sudent_batch (student_batch_id, student_batch_name) VALUES (3, '1b');
INSERT INTO pna6_11.sudent_batch (student_batch_id, student_batch_name) VALUES (4, '2b');


INSERT INTO pna6_11.teacher (teacher_id, first_name, middle_name, second_name) VALUES (1, 'Fedor', 'Ivanovich', 'Soldatov');
INSERT INTO pna6_11.teacher (teacher_id, first_name, middle_name, second_name) VALUES (2, 'Alina', 'Aleksandrowna', 'Pieciuszka');


INSERT INTO pna6_11.timesheet (timesheet_id, timestamp) VALUES (1, '2012-12-20 09:30:00');
INSERT INTO pna6_11.timesheet (timesheet_id, timestamp) VALUES (2, '2012-12-12 08:30:00');
INSERT INTO pna6_11.timesheet (timesheet_id, timestamp) VALUES (3, '2012-12-12 08:30:00');
INSERT INTO pna6_11.timesheet (timesheet_id, timestamp) VALUES (4, '2012-12-12 08:30:00');


INSERT INTO pna6_11.time_classroom (timesheet_id, classroom_id) VALUES (1, 1);
INSERT INTO pna6_11.time_classroom (timesheet_id, classroom_id) VALUES (2, 1);
INSERT INTO pna6_11.time_classroom (timesheet_id, classroom_id) VALUES (3, 1);
INSERT INTO pna6_11.time_classroom (timesheet_id, classroom_id) VALUES (4, 1);


INSERT INTO pna6_11.time_student_batch (timesheet_id, student_batch_id) VALUES (1, 2);
INSERT INTO pna6_11.time_student_batch (timesheet_id, student_batch_id) VALUES (3, 2);


INSERT INTO pna6_11.time_subject (timesheet_id, subject_id) VALUES (1, 1);
INSERT INTO pna6_11.time_subject (timesheet_id, subject_id) VALUES (2, 1);
INSERT INTO pna6_11.time_subject (timesheet_id, subject_id) VALUES (3, 1);
INSERT INTO pna6_11.time_subject (timesheet_id, subject_id) VALUES (4, 1);


INSERT INTO pna6_11.time_teacher (timesheet_id, teacher_id) VALUES (3, 1);
INSERT INTO pna6_11.time_teacher (timesheet_id, teacher_id) VALUES (4, 1);
INSERT INTO pna6_11.time_teacher (timesheet_id, teacher_id) VALUES (1, 2);