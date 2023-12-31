# School Management System
## Requirements
- ### Administrative Features: <a name="a"></a>
  - **User Management:**
    - Admin, Teachers, Students, Staff accounts with different access levels.
    - User authentication and authorization.
  - **Dashboard & Analytics:**
    - Overview of student enrollment, attendance, grades, and other key metrics.
    - Statistical reports for administrators.
  - **Course & Curriculum Management:**
    - Create, update, and delete courses.
    - Curriculum planning and syllabus management.
  - **Class & Schedule Management:**
    - Class allocation, timetable creation, and schedule management.
    - Room allocation, subject assignments to classes.
  - **Teacher & Staff Management:**
    - Manage teacher/staff information, assignments, and roles.
    - Staff attendance tracking.
  - **Student Admission & Enrollment:**
    - Admission forms, enrollment processes, and student information management.
    - Enrollment status tracking.
  - **Fees & Financial Management:**
    - Fee structure, payment tracking, and financial reports.
    - Manage scholarships, discounts, and late fees.

- ### Academic Features:
  - **Student Information System:**
    - Student profiles, academic records, and personal details.
    - Student performance tracking (grades, assessments).
  - **Attendance Management:**
    - Attendance tracking for students and teachers.
    - Automated notifications for absentees.
  - **Grading & Assessment:**
    - Grading system management, assessment creation, and evaluation.
    - Result publication, report card generation.
  - **Library Management:**
    - Cataloging of books, journals, and other resources.
    - Issuing, returning, and managing library resources.
  - **Examination & Evaluation:**
    - Exam scheduling, question paper creation, and evaluation.
    - Exam room allocation, invigilation assignments.
  - **Communication & Messaging:**
    - Internal messaging system for staff, students, and parents.
    - Announcement and notification broadcasts.

- ### Support Features: 
  - **Parent & Guardian Portal:**
    - Access to student information, grades, attendance, and communication with teachers.
  - **Health & Attendance Tracking:**
    - Health records of students, attendance patterns, and alerts for health concerns.
  - **Transportation Management:**
    - Bus routes, schedules, and tracking for school transportation.
  - **Event & Activity Management:**
    - Managing extracurricular activities, events, and school calendar.
  - **Reporting & Documentation:**
    - Generation of various reports for administrative and academic purposes.
    - Document management for policies, circulars, and notices.
  - **Integration & Scalability:**
    - Compatibility with other systems, data import/export functionalities.
    - Scalability to accommodate growing student and staff numbers.


## System Design
  ## System Design
  - ### Entities:
    | Core Entities    | Support Entities |
    | ---------------- | ---------------- |
    | [Student](#student) | [Attendance](#attendance) |
    | [Teacher/Staff](#teacher-staff) | [Grades](#grades) |
    | [Course](#course) | [Assessment](#assessment) |
    | [Class](#class) | [Department](#department) |
    | [Department](#department) | [Schedule](#schedule) |

    #### Student
      - Attributes:
        - Student ID
        - Name
        - Address
        - Date Of Birth
        - Email
        - Phone Number
        - Year/Grade Level
        - Guardian Full Name
    #### Teacher/Staff
    #### Class
    #### Department
    #### Course
    #### Attendance
    #### Grades
    #### Assessment
    #### Schedule

      
        

