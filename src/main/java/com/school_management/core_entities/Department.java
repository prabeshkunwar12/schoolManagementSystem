/**
 * Represents a Department in a school, encapsulating information such as department ID, name,
 * head of department, description, list of teachers, and courses offered by the department.
 * This class provides methods to manage and access department details, including adding/removing
 * teachers and courses, retrieving department information, and ensuring valid data manipulation.
 *
 * Key Methods:
 * - Constructors: Initialize department details.
 * - Getters and Setters: Access and modify department attributes.
 * - List Manipulation: Add, remove, and retrieve teachers and courses.
 * - Validation: Ensure non-null and avoid duplicate entries.
 * - Overrides: Equals, hashCode, and toString methods for object comparison and representation.
 *
 * Usage:
 * 1. Create a Department instance with its ID, name, and description.
 * 2. Add teachers and courses using respective methods.
 * 3. Retrieve and modify department details using getters and setters.
 * 4. Validate inputs for non-null and avoid duplicate entries.
 */
package com.school_management.core_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Department {
    // Member variables for department details
    private int departmentID;
    private String departmentName;
    private Teacher headOfDepartment;
    private String description;
    private List<Teacher> teachersList;
    private List<Course> coursesOffered;

    // Logger for logging messages related to the Student class
    private static final Logger logger = LoggerFactory.getLogger(Department.class);

    /**
     * Constructor to initialize department details.
     *
     * @param departmentID The unique identifier for the department.
     * @param departmentName The name of the department (must not be null).
     * @param description The description of the department (must not be null).
     * @throws IllegalArgumentException If departmentName or description is null.
     */
    public Department(int departmentID, String departmentName, String description) {
        if (departmentName == null || description == null) {
            logger.error("Department name and description cannot be null", new IllegalArgumentException());
        }
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.headOfDepartment = null; // Initially no head of department assigned
        this.description = description;
        this.teachersList = new ArrayList<>(); // Initialize an empty list of teachers
        this.coursesOffered = new ArrayList<>(); // Initialize an empty list of courses
        logger.info("New Department created");
    }

    // Getters and setters for department details

    public int getDepartmentID() {
        return this.departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * Set department name, ensuring it's not null.
     *
     * @param departmentName The name of the department (must not be null).
     * @throws IllegalArgumentException If departmentName is null.
     */
    public void setDepartmentName(String departmentName) {
        if (departmentName == null) {
            logger.error("Department name cannot be null",  new IllegalArgumentException());
        }
        this.departmentName = departmentName;
    }

    public Teacher getHeadOfDepartment() {
        return this.headOfDepartment;
    }

    /**
     * Sets the Head of Department for this department.
     *
     * @param headOfDepartment The Teacher to be assigned as Head of Department (must not be null).
     * @throws IllegalArgumentException If the provided headOfDepartment is null or not listed as a teacher in the department.
     */
    public void setHeadOfDepartment(Teacher headOfDepartment) {
        if (headOfDepartment == null) {
            logger.error("Head of department is null", new IllegalArgumentException());
        }
        if (!isInDepartment(headOfDepartment)) {
            logger.error("teacher is not in the teacher's list of department", new IllegalArgumentException());
        }
        this.headOfDepartment = headOfDepartment;
    }

    // Get department description
    public String getDescription() {
        return this.description;
    }

    /**
     * Set department description, ensuring it's not null.
     *
     * @param description The description of the department (must not be null).
     * @throws IllegalArgumentException If description is null.
     */
    public void setDescription(String description) {
        if (description == null) {
            logger.error("Department description cannot be null", new IllegalArgumentException());
        }
        this.description = description;
    }

    // Accessor methods for teachers list

    /**
     * Get an unmodifiable view of the teachers list.
     *
     * @return Unmodifiable list of teachers.
     */
    public List<Teacher> getTeachersList() {
        return Collections.unmodifiableList(new ArrayList<>(teachersList));
    }

    /**
     * Set the teachers list, ensuring it's not null.
     *
     * @param teachersList The list of teachers (must not be null).
     * @throws IllegalArgumentException If teachersList is null.
     */
    public void setTeachersList(List<Teacher> teachersList) {
        if (teachersList == null) {
            logger.error("Teacher's list cannot be null", new IllegalArgumentException());
        }
        this.teachersList = new ArrayList<>(teachersList);
    }

    /**
     * Add a teacher to the teachers list, ensuring it's not null.
     *
     * @param teacher The teacher to be added (must not be null).
     * @throws IllegalArgumentException If teacher is null.
     */
    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            teachersList.add(teacher);
        } else {
            logger.error("Teacher cannot be null",  new IllegalArgumentException());
        }
    }

    /**
     * Remove a teacher from the teachers list.
     *
     * @param teacher The teacher to be removed.
     * @throws IllegalArgumentException If teacher not found in the list.
     */
    public void removeTeacher(Teacher teacher) {
        if (!teachersList.remove(teacher)) {
            logger.error("TEacher not found in the list", new IllegalArgumentException());
        }
    }

    /**
     * Checks if the provided Teacher is part of the department's list of teachers.
     *
     * @param teacher The Teacher to check for inclusion in the department.
     * @return True if the teacher is present in the department's list of teachers, otherwise false.
     */
    public boolean isInDepartment(Teacher teacher) {
        return teachersList.contains(teacher);
    }

    /**
     * Checks if the provided Course is offered by the department.
     *
     * @param course The Course to check for inclusion in the department's offered courses.
     * @return True if the course is included in the department's offered courses, otherwise false.
     */
    public boolean isInDepartment(Course course) {
        return coursesOffered.contains(course);
    }

    // Accessor methods for coursesOffered list

    /**
     * Get an unmodifiable view of the coursesOffered list.
     *
     * @return Unmodifiable list of offered courses.
     */
    public List<Course> getCoursesOffered() {
        return Collections.unmodifiableList(new ArrayList<>(coursesOffered));
    }

    /**
     * Set the coursesOffered list, ensuring it's not null.
     *
     * @param coursesOffered The list of offered courses (must not be null).
     * @throws IllegalArgumentException If coursesOffered is null.
     */
    public void setCoursesOffered(List<Course> coursesOffered) {
        if(coursesOffered == null){
            logger.error("Courses offered cannot be null", new IllegalArgumentException());
        }
        this.coursesOffered = new ArrayList<>(coursesOffered);
    }

    /**
     * Add a course to the coursesOffered list, ensuring it's not null.
     *
     * @param course The course to be added (must not be null).
     * @throws IllegalArgumentException If course is null.
     */
    public void addCourse(Course course) {
        if (course != null) {
            coursesOffered.add(course);
        } else {
            logger.error("Course cannot be null", new IllegalArgumentException());
        }
    }

    /**
     * Remove a course from the coursesOffered list.
     *
     * @param course The course to be removed.
     * @throws IllegalArgumentException If course not found in the list.
     */
    public void removeCourse(Course course) {
        if (!coursesOffered.remove(course)) {
            logger.error("Course not found in the list", new IllegalArgumentException());
        }
    }

    // Overridden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Department)) {
            return false;
        }
        Department department = (Department) o;
        return departmentID == department.departmentID && Objects.equals(departmentName, department.departmentName) && Objects.equals(headOfDepartment, department.headOfDepartment) && Objects.equals(description, department.description) && Objects.equals(teachersList, department.teachersList) && Objects.equals(coursesOffered, department.coursesOffered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentID, departmentName, headOfDepartment, description, teachersList, coursesOffered);
    }

    @Override
    public String toString() {
        return "{" +
            " departmentID='" + getDepartmentID() + "'" +
            ", departmentName='" + getDepartmentName() + "'" +
            ", headOfDepartment='" + getHeadOfDepartment() + "'" +
            ", description='" + getDescription() + "'" +
            ", teachersList='" + getTeachersList() + "'" +
            ", coursesOffered='" + getCoursesOffered() + "'" +
            "}";
    }
    
}
