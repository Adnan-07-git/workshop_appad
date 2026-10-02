INSERT INTO courses (code, title, duration_days, seats) VALUES
    ('CLD201', 'AWS Cloud Essentials',                    3, 40),
    ('DCK120', 'Docker and Containers',                   2, 45),
    ('DSO101', 'DevSecOps Foundations',                   2, 60),
    ('JVA110', 'Java and Spring Boot Basics',             5, 50),
    ('SEC150', 'Web Application Security (OWASP Top 10)', 3, 35);

INSERT INTO students (first_name, last_name, email, phone) VALUES
    ('Asha',    'Rao',    'asha.rao@example.edu',     '+91-90000-10001'),
    ('Rahul',   'Verma',  'rahul.verma@example.edu',  '+91-90000-10002'),
    ('Meena',   'Iyer',   'meena.iyer@example.edu',   '+91-90000-10003'),
    ('Karthik', 'Reddy',  'karthik.reddy@example.edu','+91-90000-10004');

INSERT INTO registrations (student_id, course_code) VALUES
    (1, 'DSO101'),
    (2, 'SEC150'),
    (3, 'DSO101'),
    (4, 'DCK120');
