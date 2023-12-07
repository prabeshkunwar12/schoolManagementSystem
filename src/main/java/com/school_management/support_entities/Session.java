package com.school_management.support_entities;

import java.util.Date;
import java.util.Objects;

import com.school_management.core_entities.CourseSection;

import java.util.List;


public class Session {
    private int sessionID;
    private SessionType sessionType;
    private List<CourseSection> courseSections;
    private Date starDate;
    private Date enDate;

    public Session(int sessionID, SessionType sessionType, Date starDate, Date enDate) {
        this.sessionID = sessionID;
        this.sessionType = sessionType;
        this.starDate = starDate;
        this.enDate = enDate;
    }

    public int getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public SessionType getSessionType() {
        return this.sessionType;
    }

    public void setSessionType(SessionType sessionType) {
        this.sessionType = sessionType;
    }

    public Date getStarDate() {
        return this.starDate;
    }

    public void setStarDate(Date starDate) {
        this.starDate = starDate;
    }

    public Date getEnDate() {
        return this.enDate;
    }

    public void setEnDate(Date enDate) {
        this.enDate = enDate;
    }

    public Session sessionID(int sessionID) {
        setSessionID(sessionID);
        return this;
    }

    public Session sessionType(SessionType sessionType) {
        setSessionType(sessionType);
        return this;
    }

    public Session starDate(Date starDate) {
        setStarDate(starDate);
        return this;
    }

    public Session enDate(Date enDate) {
        setEnDate(enDate);
        return this;
    }


    public List<CourseSection> getCourseSections() {
        return this.courseSections;
    }

    public void addCourseSection(CourseSection courseSection) {
        if(courseSection == null) {
            throw new IllegalArgumentException("course section cannot be null");
        } else {
            courseSections.add(courseSection);
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
        return sessionID == session.sessionID && Objects.equals(sessionType, session.sessionType) && Objects.equals(courseSections, session.courseSections) && Objects.equals(starDate, session.starDate) && Objects.equals(enDate, session.enDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sessionID, sessionType, courseSections, starDate, enDate);
    }


    @Override
    public String toString() {
        return "{" +
            " sessionID='" + getSessionID() + "'" +
            ", sessionType='" + getSessionType() + "'" +
            ", courseSections='" + getCourseSections() + "'" +
            ", starDate='" + getStarDate() + "'" +
            ", enDate='" + getEnDate() + "'" +
            "}";
    }
    
    
}
