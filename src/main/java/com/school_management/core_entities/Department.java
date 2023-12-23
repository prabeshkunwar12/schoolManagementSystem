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

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Department {
    // Member variables for department details
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "department_id")
    private int departmentID;

    @Column(name = "department_name")
    private String departmentName;

    @ManyToOne
    @JoinColumn(name = "head_of_department")
    private Teacher headOfDepartment;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    // Logger for logging messages related to the Student class
    private static final Logger logger = LoggerFactory.getLogger(Department.class);

    // Default constructor for JPA entity compliance.
    public Department() {

    }
    
    /**
     * Constructor to initialize department details.
     *
     * @param departmentID The unique identifier for the department.
     * @param departmentName The name of the department (must not be null).
     * @param description The description of the department (must not be null).
     * @throws IllegalArgumentException If departmentName or description is null.
     */
    public Department(String departmentName, String description, School school) {
        setDepartmentName(departmentName);
        this.headOfDepartment = null; // Initially no head of department assigned
        setDescription(description);
        setSchool(school);
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
            throw new IllegalArgumentException("Department name is null");
        }
        if(departmentName.length() < 1 || departmentName.length() > 100) {
            logger.error("departmentName should be between 1 and 100 characters.", new IllegalArgumentException());
            throw new IllegalArgumentException("departmentName is too short(<1) or too long(>100)");
        }
        this.departmentName = departmentName;
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        if(school==null) {
            logger.error("School cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.school = school;
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
            throw new IllegalArgumentException("head of department cannot be null");
        }
        if (!headOfDepartment.getDepartment().equals(this)) {
            logger.error("teacher is not in the teacher's list of department", new IllegalArgumentException());
            throw new IllegalArgumentException("Teacher doesn't belong to this department.");
        }
        this.headOfDepartment = headOfDepartment;
        logger.info("head of department for {} changed to {}", this.departmentName, headOfDepartment.getName());
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
        if(description == null) {
            logger.error("Department description cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException("description cannot be null");
        }
        if(description.length()>300) {
            logger.error("Description cannot be more than 300 characters", new IllegalArgumentException());
            throw new IllegalArgumentException("Description cannot be more than 300 characters");
        }
        this.description = description;
        logger.info("description changed for {}", this.departmentName);
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
