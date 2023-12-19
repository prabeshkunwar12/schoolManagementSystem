package com.school_management.support_entities.school;

import java.util.Objects;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class School {
    private String schoolName;
    private SchoolType schoolType;

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

    // Equals, hashCode, and toString methods are overridden
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof School)) {
            return false;
        }
        School school = (School) o;
        return Objects.equals(schoolName, school.schoolName) && Objects.equals(schoolType, school.schoolType) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolName, schoolType);
    }

    @Override
    public String toString() {
        return "{" +
            " schoolName='" + getSchoolName() + "'" +
            ", schoolType='" + getSchoolType() + "'" +
            "}";
    }

}
