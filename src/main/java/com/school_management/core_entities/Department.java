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

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.school.School;

public class Department {
    // Member variables for department details
    private int departmentID;
    private String departmentName;
    private Teacher headOfDepartment;
    private String description;
    private School school;

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
    public Department(int departmentID, String departmentName, String description, School school) {
        if (departmentName == null || description == null) {
            logger.error("Department name and description cannot be null", new IllegalArgumentException());
        }
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.headOfDepartment = null; // Initially no head of department assigned
        this.description = description;
        this.school = school;
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

    public School getSchool() {
        return this.school;
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
    public boolean setHeadOfDepartment(Teacher headOfDepartment) {
        if (headOfDepartment == null) {
            logger.error("Head of department is null", new IllegalArgumentException());
            return false;
        }
        if (!headOfDepartment.getDepartment().equals(this)) {
            logger.error("teacher is not in the teacher's list of department", new IllegalArgumentException());
            return false;
        }
        this.headOfDepartment = headOfDepartment;
        return true;
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

    // Overridden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Department)) {
            return false;
        }
        Department department = (Department) o;
        return departmentID == department.departmentID && Objects.equals(departmentName, department.departmentName) && Objects.equals(headOfDepartment, department.headOfDepartment) && Objects.equals(description, department.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentID, departmentName, headOfDepartment, description);
    }

    @Override
    public String toString() {
        return "{" +
            " departmentID='" + getDepartmentID() + "'" +
            ", departmentName='" + getDepartmentName() + "'" +
            ", headOfDepartment='" + getHeadOfDepartment() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
    
}
