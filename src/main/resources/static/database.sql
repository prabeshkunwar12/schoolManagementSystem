USE school_management;

-- Table for School
CREATE TABLE School (
    school_id INT AUTO_INCREMENT PRIMARY KEY,
    school_name VARCHAR(100) NOT NULL,
    school_type ENUM('KINDER_GARDEN', 'PRIMARY', 'SECONDARY', 'HIGH', 'COLLEGE', 'UNIVERSITY') NOT NULL
);

-- Table for Department
CREATE TABLE Department (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100) NOT NULL,
    head_of_department INT,
    description VARCHAR(300),
    school_id INT
);

-- Table for Teacher
CREATE TABLE Teacher (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    email VARCHAR(100),
    department_id INT,
    schedule_id int
);

-- Table for Course
CREATE TABLE Course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL UNIQUE,
    course_description VARCHAR(300) NOT NULL,
    course_credit INT NOT NULL,
    department_id INT NOT NULL
);

-- Table for Course Section
CREATE TABLE Course_section (
    section_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT,
    room_id INT, 
    teacher_id INT,
    session_id INT,
    passing_grade FLOAT,
    course_section_schedule_id int
);

-- Table for Student 
CREATE TABLE Student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    address VARCHAR(100),
    date_of_birth DATETIME,
    email VARCHAR(100),
    phone_number VARCHAR(20),
    year_standing ENUM('FIRST_YEAR', 'SECOND_YEAR', 'THIRD_YEAR', 'FORTH_YEAR', 'FIFTH_YEAR', 'SIXTH_YEAR'),
    guardian_name VARCHAR(100),
    guardian_email VARCHAR(100),
    guardian_phone_number VARCHAR(20),
    schedule_id int
);

-- Table for Enrollment
CREATE TABLE Enrollment (
    enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT,
    course_section_id INT,
    enrollment_status ENUM('PLANNED', 'REGISTERED', 'IN-PROGRESS', 'COMPLETED', 'FAILED'),
    attendance_id INT,
    final_course_grade_id INT,
    pass_status ENUM('PASSED', 'FAILED', 'NOT_DECIDED')
);

-- Table for Assessment
CREATE TABLE Assessment (
    assessment_id int AUTO_INCREMENT PRIMARY KEY,
    assessment_type ENUM('NORMAL', 'BONOUS', 'MANDATORY_PASS'),
    description VARCHAR(300),
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    end_date TIMESTAMP DEFAULT '2030-12-31 23:59:59',
    duration_minutes int
);

-- Table for Attendance
CREATE TABLE Attendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    schedule_id INT
);

-- Table for Attendance_list
CREATE TABLE Attendance_record (
    attendance_record_id INT AUTO_INCREMENT PRIMARY KEY,
    attendance_id INT,
    date DATETIME,
    attendance_status ENUM('PRESENT', 'ABSENT', 'SICK_CALL', 'LATE', 'NA'),
    PRIMARY KEY (attendance_id, date)
);

-- Table for Grades
CREATE TABLE Grades (
    grades_id INT AUTO_INCREMENT PRIMARY KEY,
    scored_grade FLOAT,
    total_grade FLOAT,
    passing_grade FLOAT
);

-- Table for AssessmentGrade
CREATE TABLE Assessment_grade (
    grades_id INT,
    assessment_id INT,
    weightage FLOAT,
    PRIMARY KEY (grades_id, assessment_id)
);

-- Table for Final_course_grade is not required as it has same attributes as grades except it always has 100 tital grade

-- Table for Course_section_schedule
CREATE TABLE Course_section_schedule (
    course_section_schedule_id INT PRIMARY KEY,
    duration int,
    start_date DATE,
    end_date DATE
);

-- Table for Weekly_schedule 
CREATE TABLE Weekly_schedule_css (
    course_section_schedule_id int,
    day_of_week ENUM('SUNDAY', 'MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY'),
    start_time TIME,
    PRIMARY KEY (course_section_schedule_id, day_of_week)
);

-- Table for Schedule
CREATE TABLE Schedule (
    schedule_id int AUTO_INCREMENT PRIMARY KEY
);

-- Intermediate table for Schedule and Course_section
CREATE TABLE weekly_schedule_s (
    schedule_id int,
    course_section_schedule_id int,
    PRIMARY KEY (schedule_id, course_section_schedule_id)
);

-- Table for Building
CREATE TABLE Building (
    building_id INT AUTO_INCREMENT PRIMARY KEY,
    building_name VARCHAR(100) NOT NULL,
    school_id INT
);

-- Table for Room
CREATE TABLE Room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL,
    room_type ENUM('CLASSROOM', 'HALL', 'LABORATORY', 'ONLINE'),
    student_capacity INT,
    schedule_id INT,
    building_id INT,
    FOREIGN KEY (building_id) REFERENCES Building(building_id)
);

-- Table for School_year
CREATE TABLE School_year (
    school_year_id INT AUTO_INCREMENT PRIMARY KEY,
    school_id INT,
    start_date DATE,
    end_date DATE
);

-- Table for Session
CREATE TABLE Session (
    session_id INT AUTO_INCREMENT PRIMARY KEY,
    session_type ENUM('FALL', 'SUMMER', 'SUMMER_1', 'SUMMER_2', 'WINTER'),
    school_year_id int,
    start_date DATE,
    end_date DATE
);


-- adding the foreign key references

ALTER TABLE Department ADD FOREIGN KEY (school_id) REFERENCES School(school_id);
ALTER TABLE Department Add FOREIGN KEY (head_of_department) REFERENCES Teacher(teacher_id);

ALTER TABLE Teacher ADD FOREIGN KEY (department_id) REFERENCES Department(department_id);
ALTER TABLE Teacher ADD FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id);

ALTER TABLE Course ADD FOREIGN KEY (department_id) REFERENCES Department(department_id);

ALTER TABLE Course_section ADD FOREIGN KEY (course_id) REFERENCES Course(course_id);
ALTER TABLE course_section ADD FOREIGN KEY (room_id) REFERENCES Room(room_id);
ALTER TABLE Course_section ADD FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id);
ALTER TABLE Course_section ADD FOREIGN KEY (session_id) REFERENCES Session(session_id);
ALTER TABLE Course_section ADD FOREIGN KEY (course_section_schedule_id) REFERENCES Course_section_schedule(course_section_schedule_id);

ALTER TABLE Student ADD FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id);

ALTER TABLE Enrollment ADD FOREIGN KEY (student_id) REFERENCES Student(student_id);
ALTER TABLE Enrollment ADD FOREIGN KEY (course_section_id) REFERENCES Course_section(section_id);
ALTER TABLE Enrollment ADD FOREIGN KEY (attendance_id) REFERENCES Attendance(attendance_id);
ALTER TABLE Enrollment ADD FOREIGN KEY (final_course_grade_id) REFERENCES Grades(grades_id);

ALTER TABLE Assessment_grade ADD FOREIGN KEY (grades_id) REFERENCES Grades(grades_id);
ALTER TABLE Assessment_grade ADD FOREIGN KEY (assessment_id) REFERENCES Assessment(assessment_id);

ALTER TABLE Attendance ADD FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id);

ALTER TABLE Attendance_record ADD FOREIGN KEY (attendance_id) REFERENCES Attendance(attendance_id);

ALTER TABLE Weekly_schedule_css ADD FOREIGN KEY (course_section_schedule_id) REFERENCES Course_section_schedule(course_section_schedule_id);

ALTER TABLE weekly_schedule_s ADD FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id);
ALTER TABLE weekly_schedule_s ADD FOREIGN KEY (course_section_schedule_id) REFERENCES Course_section_schedule(course_section_schedule_id);

ALTER TABLE Building ADD FOREIGN KEY (school_id) REFERENCES School(school_id);

ALTER TABLE Room ADD FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id);
ALTER TABLE Room ADD FOREIGN KEY (building_id) REFERENCES Building(building_id);

ALTER TABLE School_year ADD FOREIGN KEY (school_id) REFERENCES School(school_id);

ALTER TABLE Session ADD FOREIGN KEY (school_year_id) REFERENCES School_year(school_year_id);





