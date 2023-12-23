/**
 * Represents a Teacher within the school system, containing information such as teacher ID,
 * name, contact details, associated department, and the sections of courses taught.
 * This class provides methods to manage and access teacher details, including adding/removing
 * courses taught, setting the associated department, and ensuring valid data manipulation.
 *
 * Key Attributes:
 * - Teacher ID: Unique identifier for the teacher.
 * - Name: Full name of the teacher.
 * - Phone Number: Contact number of the teacher.
 * - Email: Email address of the teacher.
 * - Department: Department associated with the teacher.
 * - Sections Taught: List of sections taught by the teacher.
 * - Sections Currently Teaching: List of sections the teacher is currently teaching.
 *
 * Functionality:
 * - Constructor: Initializes a teacher with name, phone number, and email.
 * - Getters and Setters: Access and modify teacher attributes.
 * - Department Association: Set the department associated with the teacher.
 * - Course Management: Add/remove sections taught and currently teaching.
 * - Logging: Utilizes logging for informative and error messages.
 * - Overrides: Equals, hashCode, and toString methods for object comparison and representation.
 *
 * Usage:
 * 1. Create a Teacher instance by providing name, phone number, and email.
 * 2. Add or update department association using setter method setDepartment().
 * 3. Manage courses taught and currently teaching using respective methods.
 * 4. Access teacher details and sections taught/teaching using getters.
 * 5. Ensure valid data manipulation through defined constraints and logging messages.
 */
package com.school_management.core_entities;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.schedule.Schedule;
import com.school_management.support_entities.schedule.TeacherSchedule;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private int teacherID;

    @Column(name = "name")
    private String name;

    @Column(name = "phone_number")
    private long phoneNumber;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    // Logger for logging messages related to the Teacher class
    private static final Logger logger = LoggerFactory.getLogger(Teacher.class);

    //default constructor for JPA compliance
    public Teacher() {}
    
    /**
     * Constructor to initialize department details.
     *
     * @param teacherID The unique identifier for the teacher.
     * @param Name The name of the teacher
     * @param phoneNumber The phoneNumber of the teacher
     * @param email The email of the teacher
     */
    public Teacher(int teacherID, String name, long phoneNumber, String email) {
        this.teacherID = teacherID;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        schedule = new TeacherSchedule();
        logger.info("New Teacher initialized.");
    }

    public int getTeacherID() {
        return this.teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
        logger.info("Teacher Id modified.");
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        if(name == null) {
            logger.error("name is null", new IllegalArgumentException());
            throw new IllegalArgumentException("name cannot be null");
        }
        this.name = name;
        logger.info("Teacher name for id {} modified to {}.", this.getTeacherID(), this.getName());
    }
    
    public long getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        logger.info("Teacher {} phoneNumber modified to {}.", this.getTeacherID(), this.getPhoneNumber());
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        if(email == null) {
            logger.error("email is null", new IllegalArgumentException());
            throw new IllegalArgumentException("email cannot be null");
        }
        this.email = email;
        logger.info("Teacher {} email modified.", this.getTeacherID());
    }   


    public Department getDepartment() {
        return this.department;
    }

    /**
     * Sets the department associated with the teacher.
     *
     * @param department The department to be associated with the teacher (must not be null).
     * @throws IllegalArgumentException if the department provided is null.
     */
    public void setDepartment(Department department) {
        if(department == null) {
            logger.error("department is null", new IllegalArgumentException());
            throw new IllegalArgumentException("department cannot be null");
        }
        this.department = department;
        logger.info("Teacher {} deparment modified to {}.", getTeacherID(), getDepartment().getDepartmentName());
        
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        if(schedule == null) {
            logger.error("schedule is null", new IllegalArgumentException());
            throw new IllegalArgumentException("schedule cannot be null");
        }
        this.schedule = schedule;
        logger.info("Schedule set for Teacher {}", this.getTeacherID() );
    }

    /**
     * Adds a CourseSectionSchedule to the schedule list.
     *
     * @param sectionSchedule The CourseSectionSchedule to be added (must not be null).
     * @return True if the CourseSectionSchedule is successfully added; otherwise, false.
     * @throws IllegalArgumentException If the section schedule is null.
     */
    public boolean addCourseSectionSchedule(CourseSectionSchedule schedule) {
        if(schedule==null){
            logger.error("schedule cannot be null", new IllegalArgumentException());
            return false;
        }
        if(this.schedule.addCourseSectionSchedule(schedule)) {
            logger.info("course section schedule added to teacher's schedule");
            return true;
        }
        logger.error("failed to add the course section schedule", new IllegalArgumentException());
        return false;

    }
    

    /**
     * Removes a course section schedule.
     *
     * @param sectionSchedule The schedule associated with the course section to be removed (must not be null).
     * Removes the schedule from the list.
     * Logs an info message if the schedule is successfully removed.
     * Logs an error message if an exception occurs during removal.
     * @return True if the schedule is successfully removed; otherwise, false.
     */
    public boolean removeSchedule(CourseSectionSchedule sectionSchedule) {
        try {
            schedule.removeCourseSectionSchedule(sectionSchedule);
            logger.info("CourseSectionSchedule is removed");
            return true;
        } catch(Exception e) {
            logger.error("CourseSectionSchedule not removed", e);
            return false;
        }
    }
    
      

    // Overridden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return teacherID == teacher.teacherID && Objects.equals(name, teacher.name) && phoneNumber == teacher.phoneNumber && Objects.equals(email, teacher.email) && Objects.equals(department, teacher.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID, name, phoneNumber, email, department);
    }

    @Override
    public String toString() {
        return "{" +
            " teacherID='" + getTeacherID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", department='" + getDepartment() + "'" +
            "}";
    }
}
