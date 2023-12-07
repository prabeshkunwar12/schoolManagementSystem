package com.school_management.support_entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SchoolYear {
    private Year year;
    private List<Session> sessions;


    public SchoolYear(Year year) {
        this.year = year;
        sessions = new ArrayList<>();
    }

    public SchoolYear(Year year, List<Session> sessions) {
        this.year = year;
        setSessions(sessions);
    }

    public Year getYear() {
        return this.year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public List<Session> getSessions() {
        return this.sessions;
    }

    public void setSessions(List<Session> sessions) {
        if(sessions == null) {
            throw new IllegalArgumentException("sessions cannot be null");
        }
        this.sessions = new ArrayList<>();
        for(Session session: sessions) {
            addSession(session);
        }
    }

    public void addSession(Session session) {
        if(session == null) {
            throw new IllegalArgumentException("session cannot be null");
        }
        if(containsSessionType(session.getSessionType())) {
            throw new IllegalArgumentException("this year already contains " + session.getSessionType() + " session.");
        }
        this.sessions.add(session);
    }

    public boolean containsSessionType(SessionType sessionType) {
        for(Session session: sessions) {
            if(session.getSessionType() == sessionType) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SchoolYear)) {
            return false;
        }
        SchoolYear schoolYear = (SchoolYear) o;
        return Objects.equals(year, schoolYear.year) && Objects.equals(sessions, schoolYear.sessions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, sessions);
    }

    @Override
    public String toString() {
        return "{" +
            " year='" + getYear() + "'" +
            ", sessions='" + getSessions() + "'" +
            "}";
    }
    
}
