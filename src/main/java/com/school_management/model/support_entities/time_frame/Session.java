/**
 * The `Session` class represents a time frame for specific courses within the school management system.
 * It manages details about a session, including its unique ID, type, start date, end date, and associated course sections.
 *
 * Functionalities:
 * - Initializes and manages session details, including ID, type, start date, and end date.
 * - Allows modification of the session ID.
 * - Manages a list of associated course sections.
 * - Provides methods to add course sections to the session.
 * - Implements equals, hashCode, and toString methods for session comparison and string representation.
 *
 * @see SessionType
 * @see CourseSection
 */
package com.school_management.model.support_entities.time_frame;

import java.time.LocalDate;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Session")
public class Session {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private int sessionID;
    
    @Column(name = "session_type")
    private SessionType sessionType;
    
    @ManyToOne
    @JoinColumn(name = "school_year_id")
    private SchoolYear schoolYear;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;

    private Logger logger = LoggerFactory.getLogger(Session.class);

    //default constructor for JPA compliance
    public Session(){}
    /**
     * Initializes a new Session with provided parameters.
     *
     * @param sessionID   The unique identifier for the session.
     * @param sessionType The type of session.
     * @param startDate   The start date of the session.
     * @param endDate     The end date of the session.
     */
    public Session(SessionType sessionType, SchoolYear schoolYear, LocalDate startDate, LocalDate endDate) {
        if(sessionType==null || schoolYear==null || startDate==null || endDate==null) {
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        if(startDate.getYear()!=endDate.getYear() || startDate.getYear()!=schoolYear.getYear() || endDate.getYear()!=schoolYear.getYear()){
            logger.error("start date and end date must be in the same year as schoolYear", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        if(startDate.isAfter(endDate)) {
            logger.error("Start date should be before end date", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.sessionType = sessionType;
        this.schoolYear = schoolYear;
        this.startDate = startDate;
        this.endDate = endDate;

        logger.info("New Session Initialized");
    }

    public int getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
        logger.info("Session id modified");
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        if(sessionType == null) {
            logger.error("sessionType is null", new IllegalArgumentException());
            throw new IllegalArgumentException("sessionType cannot be null");
        }
        this.sessionType = sessionType;
        logger.info("sessionType for {} has been modified to {}", getSessionID(), getSessionType());
    }

    public SchoolYear getSchoolYear() {
        return this.schoolYear;
    }

    public void setSchoolYear(SchoolYear schoolYear) {
        if(schoolYear == null) {
            logger.error("schoolYear is null", new IllegalArgumentException());
            throw new IllegalArgumentException("schoolYear cannot be null");
        }
        this.schoolYear = schoolYear;
        logger.info("schoolYear for {} has been modified to {}", getSessionID(), getSchoolYear());
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
        logger.info("startDate for {} has been modified to {}", getSessionID(), getStartDate());
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
        logger.info("endDate for {} has been modified to {}", getSessionID(), getEndDate());
    }

    // Overridden equals, hashCode, and toString methods

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Session)) {
            return false;
        }
        Session session = (Session) o;
        return sessionID == session.sessionID && Objects.equals(sessionType, session.sessionType) && Objects.equals(schoolYear, session.schoolYear) && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionID, sessionType, schoolYear, startDate, endDate);
    }


    @Override
    public String toString() {
        return "{" +
            " sessionID='" + getSessionID() + "'" +
            ", sessionType='" + getSessionType() + "'" +
            ", schoolYear='" + getSchoolYear() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
    
    
}
