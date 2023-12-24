/**
 * The {@code Course} class represents a course offered by a school or educational institution.
 * It holds information about the course ID, name, description, credits, and associated school years.
 * Courses can have multiple school years associated with them.
 * 
 * This class provides methods to manage and retrieve information about the course and its school years,
 * such as adding a school year, checking for the presence of a school year, getting the index of a school year,
 * and retrieving details about a specific school year.
 * 
 * Note: The list of school years associated with the course is managed internally and provides
 * methods to access this information in a controlled manner.
 * 
 * Usage:
 * Course course = new Course(courseID, courseName, description, credits);
 * course.addSchoolYear(schoolYear); // Adding a school year to the course
 * 
 * @see SchoolYear
 */
package com.school_management.model.core_entities;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private int courseID;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_credit")
    private int credits;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    // Logger for logging messages related to the Student class
    private static final Logger logger = LoggerFactory.getLogger(Course.class);

    // default constructor for JPA compliance
    public Course() {}

    /**
     * Constructor to initialize a Course with given parameters.
     *
     * @param courseName     The name of the course.
     * @param description    The description of the course.
     * @param department     The department of the course
     * @param credits        The number of credits for the course.
     */
    public Course(String courseName, String description, Department department, int credits) {
        setCourseName(courseName);
        setDescription(description);
        setDepartment(department);
        setCredits(credits);
        logger.info("New Course Initialized.");
    }

    // getters and setters
    
    public int getCourseID() {
        return this.courseID;
    }

    // JPA compliance
    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }
 
    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        if(courseName == null) {
            logger.error("courseName is null", new NullPointerException());
            throw new NullPointerException("courseName cannot be null");
        }
        if(courseName.length()<1 || courseName.length()>100) {
            logger.error("CourseName must contain in between 1 and 100 characters.", new IllegalArgumentException());
            throw new IllegalArgumentException("CourseName must contain in between 1 and 100 characters.");
        }
        this.courseName = courseName; 
        logger.info("courseName for course {} has been changed to {}", getCourseID(), getCourseName());
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        if(description == null) {
            logger.error("description is null", new NullPointerException());
            throw new NullPointerException("description cannot be null");
        }
        if(description.length()<1 || description.length()>300) {
            logger.error("description must contain in between 1 and 300 characters.", new IllegalArgumentException());
            throw new IllegalArgumentException("description must contain in between 1 and 300 characters.");
        }
        this.description = description; 
        logger.info("description for course {} has been changed to {}", getCourseID(), getDescription());
    
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        if(credits<0) {
            logger.error("Credits cannot be negative.", new IllegalArgumentException());
            throw new IllegalArgumentException("Credits cannot be negative.");
        }
        this.credits = credits;
        logger.info("Course credit value modified");
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        if(department == null) {
            logger.error("department is null", new NullPointerException());
            throw new NullPointerException("department cannot be null");
        }
        this.department = department; 
        logger.info("department for course {} has been changed to {}", getCourseID(), getDepartment().getDepartmentName());
    
    }

    // Overridden equals, hashCode, and toString methods

    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return courseID == course.courseID && Objects.equals(courseName, course.courseName) && Objects.equals(description, course.description) && credits == course.credits && Objects.equals(department, course.department);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseName, description, credits, department);
    }


    @Override
    public String toString() {
        return "{" +
            " courseID='" + getCourseID() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", credits='" + getCredits() + "'" +
            ", department='" + getDepartment() + "'" +
            "}";
    }
    
}
