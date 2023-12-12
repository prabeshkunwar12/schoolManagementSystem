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
package com.school_management.core_entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.school_management.support_entities.SchoolYear;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Course {
    private int courseID;
    private String courseName;
    private String description;
    private int credits;
    private Department department;
    private List<SchoolYear> schoolYearList;

    // Logger for logging messages related to the Student class
    private static final Logger logger = LoggerFactory.getLogger(Course.class);

    /**
     * Constructor to initialize a Course with given parameters.
     *
     * @param courseID       The unique identifier for the course.
     * @param courseName     The name of the course.
     * @param description    The description of the course.
     * @param credits        The number of credits for the course.
     * @throws IllegalArgumentException if the number of sections is 0.
     */
    public Course(int courseID, String courseName, String description, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        schoolYearList = new ArrayList<>();
    }

    // getters and setters
    
    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        if(department == null) {
            logger.error("Department cannot be null", new IllegalArgumentException("Department cannot be null"));
        }
        this.department.removeCourse(this);
        department.addCourse(this);
        this.department = department;
    }

    /**
     * Retrieves an unmodifiable view of the list of school years associated with this course.
     *
     * @return An unmodifiable list of school years.
     */
    public List<SchoolYear> getSchoolYearList() {
        return Collections.unmodifiableList(schoolYearList);
    }

    /**
     * Adds a school year to the list of school years associated with this course.
     *
     * @param schoolYear The school year to be added.
     * @throws IllegalArgumentException If the school year is null or already exists in the list.
     */
    public void addSchoolYear(SchoolYear schoolYear) {
        if (schoolYear == null) {
            logger.error("School year cannot be null", new IllegalArgumentException("School year cannot be null"));
        } else if (containsYear(schoolYear.getYear())) {
            logger.error("This course already contains this school year", new IllegalArgumentException("This course already contains this school year"));
        } else {
            schoolYearList.add(schoolYear);
        }
    }

    /**
     * Gets the index of a specific school year in the list.
     *
     * @param year The year to search for.
     * @return The index of the school year in the list, or -1 if not found.
     */
    public int getIndexOfSchoolYear(Year year) {
        for (int i = 0; i < schoolYearList.size(); i++) {
            if (schoolYearList.get(i).getYear().equals(year)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a specific year exists in the list of school years associated with this course.
     *
     * @param year The year to check for.
     * @return True if the year exists in the list, false otherwise.
     */
    public boolean containsYear(Year year) {
        return getIndexOfSchoolYear(year) != -1;
    }

    /**
     * Retrieves the school year at a specified index from the list.
     *
     * @param index The index of the school year to retrieve.
     * @return The school year at the given index.
     * @throws IndexOutOfBoundsException If the index is invalid (negative or beyond the list size).
     */
    public SchoolYear getSchoolYear(int index) {
        if (index < 0 || index >= schoolYearList.size()) {
            logger.error("Index out of bounds for school years list", new IndexOutOfBoundsException("Index out of bounds for school years list"));
        }
        return schoolYearList.get(index);
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
        return courseID == course.courseID && Objects.equals(courseName, course.courseName) && Objects.equals(description, course.description) && credits == course.credits && Objects.equals(department, course.department) && Objects.equals(schoolYearList, course.schoolYearList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseName, description, credits, department, schoolYearList);
    }


    @Override
    public String toString() {
        return "{" +
            " courseID='" + getCourseID() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", credits='" + getCredits() + "'" +
            ", department='" + getDepartment() + "'" +
            ", schoolYearList='" + getSchoolYearList() + "'" +
            "}";
    }
    
}
