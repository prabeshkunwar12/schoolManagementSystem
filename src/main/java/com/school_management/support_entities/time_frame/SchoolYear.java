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
package com.school_management.support_entities.time_frame;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchoolYear {
    private final Year year;
    private List<Session> sessions;

    private Logger logger = LoggerFactory.getLogger(SchoolYear.class);

    /**
     * Constructor to initialize a SchoolYear with the specified year and an empty session list.
     *
     * @param year The academic year.
     */
    public SchoolYear(Year year) {
        this.year = year;
        sessions = new ArrayList<>();
        logger.info("New School Year initialized");
    }

    /**
     * Constructor to initialize a SchoolYear with the specified year and sessions.
     *
     * @param year The academic year.
     * @param sessions The list of sessions associated with the year.
     */
    public SchoolYear(Year year, List<Session> sessions) {
        this.year = year;
        setSessions(sessions);
        logger.info("New School Year initialized");
    }

    /**
     * Get the academic year.
     *
     * @return The academic year.
     */
    public Year getYear() {
        return this.year;
    }

    /**
     * Get an unmodifiable view of the list of sessions associated with the year.
     *
     * @return Unmodifiable list of sessions.
     */
    public List<Session> getSessions() {
        return Collections.unmodifiableList(sessions);
    }

    /**
     * Set the list of sessions associated with the year.
     *
     * @param sessions The list of sessions to be set.
     * @throws IllegalArgumentException if the provided sessions list is null.
     */
    public boolean setSessions(List<Session> sessions) {
        if(sessions == null) {
            logger.error("sessions cannot be null", new IllegalArgumentException());
            return false;
        } else {
            this.sessions = new ArrayList<>();
            for(Session session: sessions) {
                if(!addSession(session)){
                    return false;
                }
            }
            logger.info("List of sessions modified");
            return true;
        }  
    }

    /**
     * Add a session to the list associated with the year.
     *
     * @param session The session to be added.
     * @throws IllegalArgumentException if the provided session is null or if the session type already exists.
     */
    public boolean addSession(Session session) {
        if(session == null) {
            logger.error("session cannot be null", new IllegalArgumentException());
            return false;
        }
        if(containsSessionType(session.getSessionType())) {
            logger.error("this year already contains {} session.", session.getSessionType(), new IllegalArgumentException());
            return false;
        }
        this.sessions.add(session);
        logger.info("Added {} to the sessionList", session.getSessionType());
        return true;
    }

    /**
     * Check if a specific session type exists within the year's session list.
     *
     * @param sessionType The session type to be checked.
     * @return True if the session type exists, otherwise false.
     */
    public boolean containsSessionType(SessionType sessionType) {
        for(Session session: sessions) {
            if(session.getSessionType().equals(sessionType)) {
                return true;
            }
        }
        return false;
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
