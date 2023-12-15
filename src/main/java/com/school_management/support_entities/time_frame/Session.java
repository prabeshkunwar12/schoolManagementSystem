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
package com.school_management.support_entities.time_frame;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.core_entities.enrollment.CourseSection;


public class Session {
    private int sessionID;
    private final SessionType sessionType;
    private List<CourseSection> courseSectionList;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private Logger logger = LoggerFactory.getLogger(Session.class);

    /**
     * Initializes a new Session with provided parameters.
     *
     * @param sessionID   The unique identifier for the session.
     * @param sessionType The type of session.
     * @param startDate   The start date of the session.
     * @param endDate     The end date of the session.
     */
    public Session(int sessionID, SessionType sessionType, LocalDate startDate, LocalDate endDate) {
        this.sessionID = sessionID;
        this.sessionType = sessionType;
        this.startDate = startDate;
        this.endDate = endDate;
        courseSectionList = new ArrayList<>();
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

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public List<CourseSection> getCourseSectionList() {
        return this.courseSectionList;
    }

    /**
     * Adds a course section to the session's course section list.
     *
     * @param courseSection The course section to add.
     * @return True if the course section is added successfully, false otherwise.
     * @throws IllegalArgumentException if the course section is null or its schedule doesn't fit within the session dates.
     */
    public boolean addCourseSection(CourseSection courseSection) {
        if(courseSection == null) {
            logger.error("course section cannot be null",  new IllegalArgumentException());
            return false;
        }
        
        if(courseSection.getSchedule().getStartDate().isBefore(this.startDate) || courseSection.getSchedule().getEndDate().isAfter(this.endDate)) {
            logger.error("The course should be available between {} and {}", startDate, endDate,  new IllegalArgumentException());
            return false;
        }

        courseSectionList.add(courseSection);
        logger.info("CourseSection added");
        return true;
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
        return sessionID == session.sessionID && Objects.equals(sessionType, session.sessionType) && Objects.equals(courseSectionList, session.courseSectionList) && Objects.equals(startDate, session.startDate) && Objects.equals(endDate, session.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionID, sessionType, courseSectionList, startDate, endDate);
    }


    @Override
    public String toString() {
        return "{" +
            " sessionID='" + getSessionID() + "'" +
            ", sessionType='" + getSessionType() + "'" +
            ", courseSectionList='" + getCourseSectionList() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
    
    
}
