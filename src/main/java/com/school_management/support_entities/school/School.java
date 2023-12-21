package com.school_management.support_entities.school;

import java.util.Objects;

import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.slf4j.Logger;

@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_id")
    private int schoolID;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_type")
    private SchoolType schoolType;

    private Logger logger = LoggerFactory.getLogger(School.class);

    // Default constructor for JPA entity compliance.
    public School() {

    }

    /**
     * Initializes a new School instance with a provided school name and school type.
     *
     * @param schoolName The name of the school.
     * @param schoolType The type of the school (e.g., elementary, high school, university).
     */
    public School(String schoolName, SchoolType schoolType) {
        if(schoolName==null || schoolType==null) {
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.schoolName = schoolName;
        this.schoolType = schoolType;
        logger.info("School is initialized");
    }
    
    public int getSchoolID() {
        return schoolID;
    }

    // setter for testing purpose
    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getSchoolName() {
        return this.schoolName;
    }

    public void setSchoolName(String schoolName) {
        if(schoolName==null) {
            logger.error("School Name is null", new IllegalArgumentException());
            throw new IllegalArgumentException("School Name is Null");
        }
        this.schoolName = schoolName;
        logger.info("New schoolName set.");
    }

    public SchoolType getSchoolType() {
        return this.schoolType;
    }

    public void setSchoolType(SchoolType schoolType) {
        if(schoolType==null) {
            logger.error("School type is null", new IllegalArgumentException());
            throw new IllegalArgumentException("School type is Null");
        }
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
        return schoolID == school.schoolID && Objects.equals(schoolName, school.schoolName) && Objects.equals(schoolType, school.schoolType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schoolID, schoolName, schoolType);
    }

    @Override
    public String toString() {
        return "{" +
            " schoolID='" + getSchoolID() + "'" +
            ", schoolName='" + getSchoolName() + "'" +
            ", schoolType='" + getSchoolType() + "'" +
            "}";
    }    
}
