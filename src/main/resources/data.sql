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
(5, '202 Birch Blvd', 'WASHINGTON', '654-321-0987', false);


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


-- Reset the sequence
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('addresses_id_seq', (SELECT MAX(id) FROM addresses));
SELECT setval('courses_id_seq', (SELECT MAX(id) FROM courses));
SELECT setval('students_id_seq', (SELECT MAX(id) FROM students));
SELECT setval('lessons_id_seq', (SELECT MAX(id) FROM lessons));
SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));
/*SELECT setval('student_lessons_id_seq', (SELECT MAX(id) FROM student_lessons));
SELECT setval('lesson_grades_id_seq', (SELECT MAX(id) FROM lesson_grades));*/