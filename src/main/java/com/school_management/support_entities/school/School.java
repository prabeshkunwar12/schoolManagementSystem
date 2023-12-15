package com.school_management.support_entities.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.school_management.core_entities.Department;
import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class School {
    private String schoolName;
    private SchoolType schoolType;
    private List<Department> departmentList;
    private List<Building> buildingList;

    private Logger logger = LoggerFactory.getLogger(School.class);

    /**
     * Initializes a new School instance with a provided school name and school type.
     *
     * @param schoolName The name of the school.
     * @param schoolType The type of the school (e.g., elementary, high school, university).
     */
    public School(String schoolName, SchoolType schoolType) {
        this.schoolName = schoolName;
        this.schoolType = schoolType;
        this.departmentList = new ArrayList<>();
        this.buildingList = new ArrayList<>();
        logger.info("School is initialized");
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
        logger.info("New schoolName set.");
    }

    public SchoolType getSchoolType() {
        return this.schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        this.schoolType = schoolType;
        logger.info("schoolType modified");
    }

    public List<Department> getDepartmentList() {
        return Collections.unmodifiableList(this.departmentList);
    }

    /**
     * Adds a department to the school's list of departments.
     *
     * @param department The department to be added.
     * @return True if the department is successfully added; otherwise, false.
     * @throws IllegalArgumentException If the provided department is null.
     */
    public boolean addDepartment(Department department) {
        if(department == null) {
            logger.error("Department cannot be null", new IllegalArgumentException());
            return false;
        } else {
            departmentList.add(department);
            logger.info("Department added in the list.");
            return true;
        }
    }

    public List<Building> getBuildingList() {
        return this.buildingList;
    }

    /**
     * Adds a building to the list of buildings associated with the school.
     *
     * @param building The building to be added.
     * @return True if the building is successfully added; otherwise, false.
     * @throws IllegalArgumentException If the provided building is null.
     */
    public boolean addBuilding(Building building) {
        if(building == null) {
            logger.error("Building cannot be null", new IllegalArgumentException());
            return false;
        } else {
            buildingList.add(building);
            logger.info("Building added to the list.");
            return true;
        }
    }

    // Equals, hashCode, and toString methods are overridden
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof School)) {
            return false;
        }
        School school = (School) o;
        return Objects.equals(schoolName, school.schoolName) && Objects.equals(schoolType, school.schoolType) && Objects.equals(departmentList, school.departmentList) && Objects.equals(buildingList, school.buildingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName, schoolType, departmentList, buildingList);
    }

    @Override
    public String toString() {
        return "{" +
            " schoolName='" + getSchoolName() + "'" +
            ", schoolType='" + getSchoolType() + "'" +
            ", departmentList='" + getDepartmentList() + "'" +
            ", buildingList='" + getBuildingList() + "'" +
            "}";
    }

}
