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
(12, '909 Cypress Avenue', 'IDAHO', '321-654-0987', false);

-- Insert Users
INSERT INTO users (id, first_name, last_name, user_name, password, confirm_password, role_id, gender, is_deleted, address_id) VALUES
(1, 'John', 'Doe', 'jdoe@example.com', 'password123', 'password123', 1, 'MALE', false, 1),
(2, 'Jane', 'Smith', 'jsmith@example.com', 'password123', 'password123', 2, 'FEMALE', false, 2),
(3, 'Alice', 'Johnson', 'ajohnson@example.com', 'password123', 'password123', 3, 'FEMALE', false, 3),
(4, 'Bob', 'Brown', 'bbrown@example.com', 'password123', 'password123', 2, 'MALE', false, 4),
(5, 'Charlie', 'Davis', 'cdavis@example.com', 'password123', 'password123', 3, 'MALE', false, 5);



-- Insert Courses
INSERT INTO courses (id, name, description, course_manager_id, start_date, end_date, is_deleted) VALUES
(1, 'Aerodynamics and Flight Mechanics', 'An in-depth study of the principles of aerodynamics and the mechanics of flight, including the forces and moments acting on aircraft, stability, and control.', 3, '2024-01-01', '2024-06-01', false),
(2, 'Programming for Aerospace Engineers', 'A comprehensive course on programming concepts, focusing on software development for aerospace applications using languages such as Python and C++.', 3, '2024-01-01', '2024-06-01', false),
(3, 'Computer Systems and Networks in Aerospace', 'An in-depth exploration of computer architecture, operating systems, and network protocols, essential for developing reliable aerospace systems.', 5, '2024-01-01', '2024-06-01', false),
(4, 'Flight Dynamics and Control Systems', 'Study of the dynamics of flight, including stability and control, and the design of flight control systems.', 5, '2024-01-01', '2024-06-01', false),
(5, 'Artificial Intelligence in Aerospace Engineering', 'Exploring the applications of artificial intelligence in aerospace, from autonomous flight to predictive maintenance.', 5, '2024-01-01', '2024-06-01', false);


-- Insert Students
INSERT INTO students (id, first_name, last_name, email, gender, is_deleted, address_id) VALUES
(1, 'Emily', 'Wilson', 'ewilson@example.com', 'FEMALE', false, 6),
(2, 'Daniel', 'Taylor', 'dtaylor@example.com', 'MALE', false, 7),
(3, 'Sarah', 'Anderson', 'sanderson@example.com', 'FEMALE', false, 8),
(4, 'Michael', 'Thomas', 'mthomas@example.com', 'MALE', false, 9),
(5, 'Laura', 'Martinez', 'lmartinez@example.com', 'FEMALE', false, 10),
(6, 'John', 'Doe', 'jdoe@example.com', 'MALE', false, 11),
(7, 'Jane', 'Smith', 'jsmith@example.com', 'FEMALE', false, 12);



-- Insert Lessons
INSERT INTO lessons (id, name, description, instructor_id, course_id, is_deleted) VALUES
(1, 'Introduction to Aerodynamics', 'Fundamentals of aerodynamics, covering basic principles of airflow and the generation of lift and drag.', 2, 1, false),
(2, 'Aircraft Stability and Control', 'Exploration of aircraft stability, control surfaces, and their effects on flight dynamics.', 2, 1, false),

(3, 'Introduction to Python for Engineers', 'Basic programming concepts using Python, tailored for solving engineering problems.', 2, 2, false),
(4, 'C++ for Aerospace Applications', 'Intermediate programming in C++ with a focus on developing applications for aerospace systems.', 2, 2, false),

(5, 'Computer Architecture Basics', 'Introduction to the fundamental components and organization of computer systems.', 2, 3, false),
(6, 'Operating Systems for Embedded Systems', 'Study of operating systems, focusing on those used in embedded aerospace applications.', 2, 3, false),

(7, 'Fundamentals of Flight Dynamics', 'Basic concepts of flight dynamics, including forces and moments on aircraft.', 4, 4, false),
(8, 'Aircraft Stability Analysis', 'Techniques for analyzing the stability of aircraft and the impact of control surfaces.', 4, 4, false),

(9, 'Introduction to AI in Aerospace', 'Overview of artificial intelligence and its potential applications in the aerospace industry.', 2, 5, false),
(10, 'Machine Learning for Aerospace Applications', 'Introduction to machine learning techniques and their use in aerospace engineering.', 2, 5, false);

-- Insert Course-Student relationships (Many-to-Many)
INSERT INTO course_students (course_id, student_id) VALUES
(1, 1),
(1, 2),
(2, 1);

-- Reset the sequence
SELECT setval('users_id_seq', (SELECT MAX(id) FROM users));
SELECT setval('addresses_id_seq', (SELECT MAX(id) FROM addresses));
SELECT setval('courses_id_seq', (SELECT MAX(id) FROM courses));
SELECT setval('students_id_seq', (SELECT MAX(id) FROM students));
SELECT setval('lessons_id_seq', (SELECT MAX(id) FROM lessons));
SELECT setval('roles_id_seq', (SELECT MAX(id) FROM roles));


/*SELECT setval('student_lessons_id_seq', (SELECT MAX(id) FROM student_lessons));
SELECT setval('lesson_grades_id_seq', (SELECT MAX(id) FROM lesson_grades));*/