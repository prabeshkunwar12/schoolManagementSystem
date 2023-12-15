package com.school_management.support_entities.time_frame;

import java.util.Date;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.core_entities.CourseSection;

import java.util.List;


public class Session {
    private int sessionID;
    private final SessionType sessionType;
    private List<CourseSection> courseSections;
    private final Date starDate;
    private final Date endDate;

    private Logger logger = LoggerFactory.getLogger(Session.class);

    public Session(int sessionID, SessionType sessionType, Date starDate, Date endDate) {
        this.sessionID = sessionID;
        this.sessionType = sessionType;
        this.starDate = starDate;
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

    public Date getStarDate() {
        return this.starDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public List<CourseSection> getCourseSections() {
        return this.courseSections;
    }

    public boolean addCourseSection(CourseSection courseSection) {
        if(courseSection == null) {
            logger.error("course section cannot be null",  new IllegalArgumentException());
            return false;
        } else {
            courseSections.add(courseSection);
            logger.info("CourseSection added");
            return true;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Session)) {
            return false;
        }
        Session session = (Session) o;
        return sessionID == session.sessionID && Objects.equals(sessionType, session.sessionType) && Objects.equals(courseSections, session.courseSections) && Objects.equals(starDate, session.starDate) && Objects.equals(endDate, session.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionID, sessionType, courseSections, starDate, endDate);
    }


    @Override
    public String toString() {
        return "{" +
            " sessionID='" + getSessionID() + "'" +
            ", sessionType='" + getSessionType() + "'" +
            ", courseSections='" + getCourseSections() + "'" +
            ", starDate='" + getStarDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
    
    
}
