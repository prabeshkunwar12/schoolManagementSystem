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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.schedule.Schedule;
import com.school_management.support_entities.schedule.TeacherSchedule;

public class Teacher {
    private int teacherID;
    private String name;
    private long phoneNumber;
    private String email;
    private Department department;
    private List<CourseSection> sectionsTaughtList;
    private List<CourseSection> sectionsCurrentlyTeachingList;
    private Schedule schedule;

    // Logger for logging messages related to the Teacher class
    private static final Logger logger = LoggerFactory.getLogger(Teacher.class);

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
        sectionsTaughtList = new ArrayList<>();
        sectionsCurrentlyTeachingList = new ArrayList<>();
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
        this.name = name;
        logger.info("Teacher name modified.");
    }
    
    public long getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
        logger.info("Teacher phoneNumber modified.");
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
        logger.info("Teacher email modified.");
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
    public boolean setDepartment(Department department) {
        if(department == null) {
            logger.error("department cannot be null.", new IllegalArgumentException());
            return false;
        } else {
            this.department.removeTeacher(this);
            department.addTeacher(this);
            this.department = department;
            logger.info("Teacher deparment modified.");
            return true;
        }
    }


    /**
     * Retrieves an unmodifiable list of courses taught by the teacher.
     *
     * @return List<CourseSection> - Unmodifiable view of the courses taught by the teacher.
     * Returns a new ArrayList to prevent the modification of the original List.
     */
    public List<CourseSection> getCourseSectionTaught() {

        return Collections.unmodifiableList(sectionsTaughtList);
    }
    
    /**
     * Adds a course section to the list of courses taught by the teacher.
     *
     * @param courseSection The course section to be added (must not be null).
     * Moves the course section from sectionsCurrentlyTeachingList to sectionsTaughtList.
     * Logs an info message if the course section is successfully moved.
     * Logs an error message if the course section is not found in sectionsCurrentlyTeachingList.
     */
    public void addCourseSectionTaught(CourseSection courseSection) {
        if(sectionsCurrentlyTeachingList.remove(courseSection)) {
            this.sectionsTaughtList.add(courseSection);
            this.sectionsCurrentlyTeachingList.remove(courseSection);
            logger.info("CourseSection moved from sectionsCurrentlyTeachingList to sectionsTaughtList.");
        } else {
            logger.error("CourseSection not found in sectionCurrentlyTeachingList", new IllegalArgumentException());
        }
    }
    
    /**
     * Retrieves an unmodifiable list of sections currently taught by the teacher.
     *
     * @return List<CourseSection> - Unmodifiable view of the sections currently taught by the teacher.
     * Returns a new ArrayList to prevent the modification of the original List.
     */
    public List<CourseSection> getCourseSectionsCurrentlyTeachingList() {
        return Collections.unmodifiableList(sectionsCurrentlyTeachingList);
    }

    /**
     * Adds a course section to the list of sections currently taught by the teacher.
     *
     * @param courseSection The course section to be added (must not be null).
     * Checks if the course section's department matches the teacher's department.
     * Adds the course section to sectionsCurrentlyTeachingList if the departments match.
     * Logs an info message if the course section is added successfully.
     * Logs an error message if the course section is from a different department.
     * @return True if the course section is successfully added; otherwise, false.
     */
    public boolean addCourseSectionCurrentlyTeaching(CourseSection courseSection) {
        if (courseSection != null) {
            if (this.getDepartment().equals(courseSection.getCourse().getDepartment()) && this.addSchedule(courseSection.getSchedule())) {
                sectionsCurrentlyTeachingList.add(courseSection);
                logger.info("CourseSection added to sectionsCurrentlyTeachingList;");
                return true;
            } else {
                logger.error("Course section is from a different department.", new IllegalArgumentException());
            }
        } else {
            logger.error("Course section cannot be null", new IllegalArgumentException());
        }
        return false;
    }
    

    /**
    * Removes a course section from the list of sections currently taught by the teacher.
    *
    * @param courseSection The course section to be removed (must not be null).
    * Removes the course section from sectionsCurrentlyTeachingList.
    * Logs an info message if the course section is successfully removed.
    * Logs an error message if the course section is not found in sectionsCurrentlyTeachingList.
    * @return True if the course section is successfully removed; otherwise, false.
    */
    public boolean removeCourseSectionCurrentlyTeaching(CourseSection courseSection) {
        if(this.sectionsCurrentlyTeachingList.remove(courseSection)) {
            removeSchedule(courseSection.getSchedule());
            logger.info("CourseSection removed from the sectionsCurrentlyTeachingList.");
            return true;
        } 
        logger.error("courseSection not found in the sectionCurrentlyTeachingList", new IllegalArgumentException());
        return false;
    }

    public Schedule getSchedule() {
        return this.schedule;
    }

    /**
     * Adds a CourseSectionSchedule to the schedule list.
     *
     * @param sectionSchedule The CourseSectionSchedule to be added (must not be null).
     * @return True if the CourseSectionSchedule is successfully added; otherwise, false.
     * @throws IllegalArgumentException If the section schedule is null.
     */
    private boolean addSchedule(CourseSectionSchedule sectionSchedule) {
        if (sectionSchedule != null) {
            try {
                schedule.addCourseSectionSchedule(sectionSchedule);
                logger.info("Schedule added to the list.");
                return true;
            } catch (Exception e) {
                logger.error("Failed to add schedule: " + e.getMessage(), e);
            }
        } else {
            logger.error("Section schedule cannot be null", new IllegalArgumentException());
        }
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
    private boolean removeSchedule(CourseSectionSchedule sectionSchedule) {
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
        return teacherID == teacher.teacherID && Objects.equals(name, teacher.name) && phoneNumber == teacher.phoneNumber && Objects.equals(email, teacher.email) && Objects.equals(department, teacher.department) && Objects.equals(sectionsTaughtList, teacher.sectionsTaughtList) && Objects.equals(sectionsCurrentlyTeachingList, teacher.sectionsCurrentlyTeachingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID, name, phoneNumber, email, department, sectionsTaughtList, sectionsCurrentlyTeachingList);
    }

    @Override
    public String toString() {
        return "{" +
            " teacherID='" + getTeacherID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", department='" + getDepartment() + "'" +
            ", sectionsTaughtList='" + getCourseSectionTaught() + "'" +
            ", sectionsCurrentlyTeachingList='" + getCourseSectionsCurrentlyTeachingList() + "'" +
            "}";
    }
}
