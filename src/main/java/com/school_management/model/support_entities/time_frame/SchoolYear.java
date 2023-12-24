/**
 * Represents a School Year within the educational system. It encapsulates the year information
 * along with a list of sessions associated with that academic year.
 *
 * Key Attributes:
 * - Year: Represents the academic year.
 * - Sessions: A collection of sessions within that academic year.
 *
 * Functionality:
 * - Constructors: Initialize the school year with a given year or with a list of sessions.
 * - Getters and Setters: Access and modify the year and session list.
 * - Session Management: Add sessions, verify session type existence within the year.
 * - Overrides: Equals, hashCode, and toString methods for comparison and representation.
 *
 * Usage:
 * Create a SchoolYear instance by specifying the academic year. Optionally, include sessions
 * associated with that year. Manage sessions by adding or checking session types within the year.
 * Access year and session details using getters. Ensure valid data manipulation by verifying inputs.
 */
package com.school_management.model.support_entities.time_frame;

import java.time.LocalDate;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.model.support_entities.school.School;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "School_year")
public class SchoolYear {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "school_year_id")
    private int schoolYearID;
    
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;

    private Logger logger = LoggerFactory.getLogger(SchoolYear.class);

    // default constructor for JPA compliance
    public SchoolYear() {}

    /**
     * Constructor to initialize a SchoolYear with the specified year and an empty session list.
     *
     * @param year The academic year.
     */
    public SchoolYear(School school, LocalDate startDate, LocalDate endDate) {
        if(school == null || startDate == null || endDate == null) {
            logger.error("Parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        if(startDate.getYear()!=endDate.getYear()){
            logger.error("start date and end date must have same year", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        if(startDate.isAfter(endDate)) {
            logger.error("Start date should be before end date", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.startDate = startDate;
        this.endDate = endDate;
        this.school = school;
        logger.info("New School Year initialized");
    }

    public int getSchoolYearID() {
        return this.schoolYearID;
    }

    public School getSchool() {
        return school;
    }

    //JPA compliance
    public void setSchool(School school) {
        if(school == null) {
            logger.error("school is null", new IllegalArgumentException());
            throw new IllegalArgumentException("school cannot be null");
        }
        this.school = school;
        logger.info("school for {} has been modified to {}", getSchoolYearID(), getSchool().getSchoolName());
    }

    /**
     * Get the academic year.
     *
     * @return The academic year.
     */
    public int getYear() {
        return this.startDate.getYear();
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate == null) {
            logger.error("startDate is null", new IllegalArgumentException());
            throw new IllegalArgumentException("startDate cannot be null");
        }
        this.startDate = startDate;
        logger.info("startDate for {} has been modified to {}", getSchoolYearID(), getStartDate());
    } 

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate == null) {
            logger.error("endDate is null", new IllegalArgumentException());
            throw new IllegalArgumentException("endDate cannot be null");
        }
        this.endDate = endDate;
        logger.info("endDate for {} has been modified to {}", getSchoolYearID(), getEndDate());
    } 

    // Overridden equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SchoolYear)) {
            return false;
        }
        SchoolYear schoolYear = (SchoolYear) o;
        return Objects.equals(school, schoolYear.school) && Objects.equals(startDate, schoolYear.startDate) && Objects.equals(endDate, schoolYear.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(school, startDate, endDate);
    }

    @Override
    public String toString() {
        return "{" +
            " school='" + getSchool() + "'" +
            " startDate='" + getStartDate() + "'" +
            " endDate='" + getEndDate() + "'" +
            "}";
    }
    
}
