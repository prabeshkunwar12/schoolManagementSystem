-- Active: 1680199779105@@127.0.0.1@3306@school_management
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

-- Table for Building
CREATE TABLE Building (
    building_id INT AUTO_INCREMENT PRIMARY KEY,
    building_name VARCHAR(100) NOT NULL,
    school_id INT
);

-- Table for Teacher
CREATE TABLE Teacher (
    teacher_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone_number int,
    email VARCHAR(100),
    department_id INT
);

-- reference head_of_department as foreign after creating teacher
ALTER TABLE Department ADD FOREIGN KEY (head_of_department) REFERENCES Teacher(teacher_id);

-- Intermediate table for Building-Room relationship
CREATE TABLE Building_Room (
    building_id INT,
    room_id INT,
    PRIMARY KEY (building_id, room_id),
    FOREIGN KEY (building_id) REFERENCES Building(building_id),
    FOREIGN KEY (room_id) REFERENCES Room(room_id)
);


-- Table for Room
CREATE TABLE Room (
    room_id INT AUTO_INCREMENT PRIMARY KEY,
    room_name VARCHAR(100) NOT NULL,
    building_id INT,
    FOREIGN KEY (building_id) REFERENCES Building(building_id)
);

-- Table for Schedule
CREATE TABLE Schedule (
    schedule_id int AUTO_INCREMENT PRIMARY KEY;
);

-- Intermediate table for Schedule and Course_section
CREATE TABLE Schedule_Course_Section_Pivot (
    schedule_id int,
    course_section_id int,
    PRIMARY KEY (schedule_id, course_section_id)
) 