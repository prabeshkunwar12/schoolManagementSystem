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

public class Teacher {
    private int teacherID;
    private boolean teacherIDGenerated;
    private String name;
    private long phoneNumber;
    private String email;
    private Department department;
    private List<CourseSection> sectionsTaughtList;
    private List<CourseSection> sectionsCurrentlyTeachingList;

    // Logger for logging messages related to the Teacher class
    private static final Logger logger = LoggerFactory.getLogger(Teacher.class);
    
    // Constructor for Teacher
    public Teacher(String name, long phoneNumber, String email) {
        setTeacherID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        sectionsTaughtList = new ArrayList<>();
        sectionsCurrentlyTeachingList = new ArrayList<>();
    }

    // Getter for teacher ID
    public int getTeacherID() {
        return this.teacherID;
    }

    // Setter for teacher ID
    public void setTeacherID() {
        this.teacherID = teacherIDGenerator();
    }

    // Private method to generate teacher ID
    private int teacherIDGenerator() {
        if (!teacherIDGenerated) {
            return 111111; // Temporary ID generation logic
            /*
             * generate the unique ID for teachers
             * (Actual logic for generating unique ID can be added here)
             */
        }
        return getTeacherID();
    }

    // Getters and setters for teacher details

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
            throw new IllegalArgumentException("department cannot be null");
        }
        this.department.removeTeacher(this);
        department.addTeacher(this);
        this.department = department;
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
        if(this.getDepartment().equals(courseSection.getCourse().getDepartment())) {
            sectionsCurrentlyTeachingList.add(courseSection);
            logger.info("CourseSection added to sectionsCurrentlyTeachingList;");
            return true;
        } 
        logger.error("Course section is from a different department.", new IllegalArgumentException());
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
            logger.info("CourseSection removed form the sectionsCurrentlyTeachingList.");
            return true;
        } 
        logger.error("courseSection not found in the sectionCurrentlyTeachingList", new IllegalArgumentException());
        return false;
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
