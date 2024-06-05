-- Insert Roles
INSERT INTO roles (id, description, is_deleted) VALUES
(1, 'Admin', false),
(2, 'Instructor', false),
(3, 'Manager', false);

-- Insert Addresses for Users
INSERT INTO addresses (id, address_info, state, phone_number, is_deleted) VALUES
(1, '123 Elm Street', 'CALIFORNIA', '123-456-7890', false),
(2, '456 Oak Avenue', 'TEXAS', '987-654-3210', false),
(3, '789 Pine Road', 'NEW_YORK', '456-789-1234', false),
(4, '101 Maple Lane', 'FLORIDA', '321-654-9870', false),
(5, '202 Birch Blvd', 'WASHINGTON', '654-321-0987', false),
(6, '303 Cedar Court', 'OREGON', '210-987-6543', false),
(7, '404 Walnut Way', 'NEVADA', '432-109-8765', false),
(8, '505 Cherry Street', 'ARIZONA', '543-210-9876', false),
(9, '606 Spruce Drive', 'COLORADO', '678-543-2109', false),
(10, '707 Fir Circle', 'MONTANA', '789-654-3219', false),
(11, '808 Redwood Street', 'UTAH', '890-123-4567', false),
(12, '909 Cypress Avenue', 'IDAHO', '901-234-5678', false),
(13, '1010 Dogwood Lane', 'NEBRASKA', '012-345-6789', false),
(14, '1111 Magnolia Boulevard', 'KANSAS', '123-567-8901', false),
(15, '1212 Willow Drive', 'NEW_MEXICO', '234-678-9012', false);


-- Insert Users
INSERT INTO users (id, first_name, last_name, user_name, password, confirm_password, role_id, gender, is_deleted,address_id) VALUES
(1, 'John', 'Doe', 'jdoe@example.com', 'password123', 'password123', 1, 'MALE', false,1),
(2, 'Jane', 'Smith', 'jsmith@example.com', 'password123', 'password123', 2, 'FEMALE', false,2),
(3, 'Alice', 'Johnson', 'ajohnson@example.com', 'password123', 'password123', 3, 'FEMALE', false,3),
(4, 'Bob', 'Brown', 'bbrown@example.com', 'password123', 'password123', 2, 'MALE', false,4),
(5, 'Charlie', 'Davis', 'cdavis@example.com', 'password123', 'password123', 3, 'MALE', false,5);

-- Insert Courses
INSERT INTO courses (id, name, description, course_manager_id, start_date, end_date, is_deleted) VALUES
(1, 'Math 101', 'Basic Math Course', 1, '2024-09-01', '2024-12-15', false),
(2, 'Physics 201', 'Intermediate Physics Course', 2, '2024-09-01', '2024-12-15', false),
(3, 'Chemistry 301', 'Advanced Chemistry Course', 3, '2024-09-01', '2024-12-15', false),
(4, 'Biology 101', 'Basic Biology Course', 4, '2024-09-01', '2024-12-15', false),
(5, 'Computer Science 101', 'Intro to Computer Science', 5, '2024-09-01', '2024-12-15', false);


-- Insert Lessons
INSERT INTO lessons (id, name, description, instructor_id, course_id, is_deleted) VALUES
(1, 'Lesson 1', 'Introduction to Math', 1, 1, false),
(2, 'Lesson 2', 'Advanced Algebra', 1, 1, false),
(3, 'Lesson 1', 'Introduction to Physics', 2, 2, false),
(4, 'Lesson 2', 'Mechanics', 2, 2, false),
(5, 'Lesson 1', 'Introduction to Chemistry', 3, 3, false);


-- Insert Students
INSERT INTO students (id, first_name, last_name, email, gender, is_deleted,address_id) VALUES
(1, 'Emily', 'Wilson', 'ewilson@example.com', 'FEMALE', false,6),
(2, 'Daniel', 'Taylor', 'dtaylor@example.com', 'MALE', false,7),
(3, 'Sarah', 'Anderson', 'sanderson@example.com', 'FEMALE', false,8),
(4, 'Michael', 'Thomas', 'mthomas@example.com', 'MALE', false,9),
(5, 'Laura', 'Martinez', 'lmartinez@example.com', 'FEMALE', false,10),
(6, 'John', 'Doe', 'jdoe@example.com', 'MALE', false, 11),
(7, 'Jane', 'Smith', 'jsmith@example.com', 'FEMALE', false, 12),
(8, 'Robert', 'Brown', 'rbrown@example.com', 'MALE', false, 13),
(9, 'Mary', 'Johnson', 'mjohnson@example.com', 'FEMALE', false, 14),
(10, 'James', 'Williams', 'jwilliams@example.com', 'MALE', false, 15);






INSERT INTO assessments (id, lesson_id, date, grade, instructor_impression_of_student, is_deleted) VALUES
(1, 1, '2023-05-10', '85', 'Good understanding of the material. Needs to participate more in class.', false),
(2, 1, '2023-05-12', '90', 'Excellent participation and comprehension.', false),
(3, 2, '2023-06-15', '75', 'Struggles with some concepts. Requires additional help.', false),
(4, 2, '2023-06-17', '88', 'Shows improvement. Keep up the good work.', false),
(5, 3, '2023-07-20', '92', 'Outstanding performance.', false),
(6, 3, '2023-07-22', '78', 'Average performance. Needs to focus more on assignments.', false),
(7, 4, '2023-08-25', '81', 'Good effort, but needs to ask more questions.', false),
(8, 4, '2023-08-27', '89', 'Very good. Continues to improve.', false),
(9, 5, '2023-09-30', '95', 'Excellent work and participation.', false),
(10, 5, '2023-10-02', '80', 'Satisfactory performance. Can do better with more practice.', false),
(11, 1, '2023-11-15', '87', 'Great effort and consistent improvement.', false),
(12, 2, '2023-12-01', '73', 'Needs to focus more on core concepts.', false),
(13, 3, '2023-12-10', '82', 'Solid understanding but needs to participate more.', false),
(14, 4, '2024-01-05', '90', 'Excellent comprehension and class participation.', false),
(15, 5, '2024-01-15', '77', 'Struggles with some topics. Additional help recommended.', false),
(16, 2, '2024-02-10', '84', 'Good performance but can improve participation.', false),
(17, 3, '2024-02-20', '91', 'Outstanding understanding and active participation.', false),
(18, 4, '2024-03-05', '88', 'Consistent improvement. Keep it up.', false),
(19, 5, '2024-03-15', '85', 'Good effort but needs more practice on assignments.', false),
(20, 1, '2024-03-25', '79', 'Needs to focus more during class.', false),
(21, 2, '2024-04-01', '86', 'Good participation but needs to ask more questions.', false),
(22, 3, '2024-04-10', '89', 'Very good. Keep up the good work.', false),
(23, 4, '2024-04-20', '83', 'Solid performance but can do better with more effort.', false),
(24, 5, '2024-05-05', '92', 'Excellent understanding and class participation.', false),
(25, 1, '2024-05-15', '76', 'Needs additional help with some concepts.', false);


INSERT INTO assessments_student (assessment_id, student_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 3),
(7, 2),
(8, 1),
(9, 4),
(10, 5);




-- Reset the sequence
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('addresses_id_seq', (SELECT MAX(id) FROM addresses));
SELECT setval('courses_id_seq', (SELECT MAX(id) FROM courses));
SELECT setval('students_id_seq', (SELECT MAX(id) FROM students));
SELECT setval('lessons_id_seq', (SELECT MAX(id) FROM lessons));
SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));
/*SELECT setval('student_lessons_id_seq', (SELECT MAX(id) FROM student_lessons));
SELECT setval('lesson_grades_id_seq', (SELECT MAX(id) FROM lesson_grades));*/