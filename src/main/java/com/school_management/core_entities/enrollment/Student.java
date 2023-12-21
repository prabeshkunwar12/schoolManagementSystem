/**
 * The `Student` class encapsulates student information and handles enrollment and course completion.
 * It manages personal details, enrollment in courses, and completed courses for a student.
 *
 * Usage:
 * Create a `Student` instance by providing mandatory personal details like ID, name, address, date of birth, and email.
 * Use methods like `addCourse`, `removeCourse`, and `setCourseAsComplete` to manage enrolled and completed courses.
 *
 * Functionalities:
 * - Initializes and manages student personal details and guardian information.
 * - Keeps track of the student's enrollment in courses and completed courses.
 * - Allows adding, removing, and marking courses as complete for the student.
 * - Provides methods to access and manipulate the student's schedule.
 *
 * @see Enrollment
 * @see YearStanding
 * @see StudentSchedule
 * @see Schedule
 */
package com.school_management.core_entities.enrollment;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.schedule.Schedule;
import com.school_management.support_entities.schedule.StudentSchedule;
import com.school_management.support_entities.time_frame.YearStanding;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Student {
    // Logger for logging messages related to the Student class
    private static Logger logger = LoggerFactory.getLogger(Student.class);

    // Unique identifier for the student
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int studentID;

    // Student's personal details
    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private long phoneNumber;

    // Year standing of the student (e.g., First year, Second year, etc.)
    @Column(name = "year_standing")
    private YearStanding yearStanding;

    // Guardian details
    @Column(name = "guardian_name")
    private String guardianName;

    @Column(name = "guardian_phone_number")
    private long guardianContactNumber;

    @Column(name = "guardian_email")
    private String guardianEmail;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;


    // Default constructor for JPA complaince
    public Student() {}

    // Constructor to initialize mandatory personal details of the student
    public Student(String name, String address, Date dateOfBirth, String email) {
        if(name==null || address==null || dateOfBirth==null || email==null) {
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;

        // Default values for other attributes
        this.phoneNumber = 0;
        this.yearStanding = YearStanding.FIRST_YEAR;
        this.guardianName = "not set";
        this.guardianContactNumber = 0;
        this.guardianEmail = "none";

        // Initialize lists for enrolled and completed courses
        this.schedule = new StudentSchedule();
        logger.info("New student initialized");
    }

    // Getters and setters 

    public int getStudentID() {
        return this.studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        logger.info("Student {} name modified", getStudentID());
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        logger.info("Student {} address modified", getStudentID());
        this.address = address;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        logger.info("Student {} date of birth modified", getStudentID());
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        logger.info("Student {} email modified", getStudentID());
        this.email = email;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        logger.info("Student {} phone number modified", getStudentID());
        this.phoneNumber = phoneNumber;
    }

    public YearStanding getYearStanding() {
        return this.yearStanding;
    }

    public void setYearStanding(YearStanding yearStanding) {
        logger.info("Student {} year standing modified", getStudentID());
        this.yearStanding = yearStanding;
    }

    public String getGuardianName() {
        return this.guardianName;
    }

    public void setGuardianName(String guardianName) {
        logger.info("Student {} gurdian name modified", getStudentID());
        this.guardianName = guardianName;
    }

    public long getGuardianContactNumber() {
        return this.guardianContactNumber;
    }

    public void setGuardianContactNumber(long guardianContactNumber) {
        logger.info("Student {} gurdian contact number modified", getStudentID());
        this.guardianContactNumber = guardianContactNumber;
    }

    public String getGuardianEmail() {
        return this.guardianEmail;
    }

    public void setGuardianEmail(String guardianEmail) {
        logger.info("Student {} gurdian email modified", getStudentID());
        this.guardianEmail = guardianEmail;
    }

    
    public Schedule getSchedule() {
        return schedule;
    }

    public boolean setSchedule(Schedule schedule) {
        if(schedule == null) {
            logger.error("schedule cannot be null", new IllegalArgumentException());
            return false;
        } 
        this.schedule = schedule;
        logger.info("Schedule set for Teacher {}", this.getStudentID() );
        return true;
    }

    public boolean addCourseSectionSchedule(CourseSectionSchedule css) {
        if(css==null) {
            logger.error("course section schedule cannot be null.", new IllegalArgumentException());
            return false;
        }
        if(schedule.addCourseSectionSchedule(css)) {
           logger.info("CourseSectionSchedule added to the student {}'s schedule", studentID); 
           return true;
        }
        logger.error("failed to add the coursesectionSchedule", new IllegalArgumentException());
        return false;
    }

    // Equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return studentID == student.studentID && Objects.equals(name, student.name) && Objects.equals(address, student.address) && Objects.equals(dateOfBirth, student.dateOfBirth) && Objects.equals(email, student.email) && phoneNumber == student.phoneNumber && Objects.equals(yearStanding, student.yearStanding) && Objects.equals(guardianName, student.guardianName) && guardianContactNumber == student.guardianContactNumber && Objects.equals(guardianEmail, student.guardianEmail) && Objects.equals(schedule, student.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, address, dateOfBirth, email, phoneNumber, yearStanding, guardianName, guardianContactNumber, guardianEmail, schedule);
    }

    @Override
    public String toString() {
        return "{" +
            " studentID='" + getStudentID() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", yearStanding='" + getYearStanding() + "'" +
            ", guardianName='" + getGuardianName() + "'" +
            ", guardianContactNumber='" + getGuardianContactNumber() + "'" +
            ", guardianEmail='" + getGuardianEmail() + "'" +
            "}";
    }
    
}
