package com.school_management.core_entities;

import java.time.Year;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

import com.school_management.support_entities.Schedule;
import com.school_management.support_entities.Session;

public class Course {
    private int courseID;
    private String courseName;
    private String description;
    private int credits;
    private Map<Year, Map<Session, Stack<CourseSection>>> sections; //each year can have multiple sessions and each sessions can have multiple sections available
    
    /**
     * Constructor to initialize a Course with given parameters.
     *
     * @param courseID       The unique identifier for the course.
     * @param courseName     The name of the course.
     * @param description    The description of the course.
     * @param credits        The number of credits for the course.
     * @throws IllegalArgumentException if the number of sections is 0.
     */
    public Course(int courseID, String courseName, String description, int credits) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        sections = new HashMap<>();
    }

    // getters and setters
    
    public int getCourseID() {
        return this.courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getdescription() {
        return this.description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Retrieves the sections mapped by Year and Session.
     *
     * @return An unmodifiable map of sections mapped by Year and Session.
     */
    public Map<Year, Map<Session, Stack<CourseSection>>> getSections() {
        return Collections.unmodifiableMap(this.sections);
    }

    /**
     * Adds a new Year to the sections map.
     *
     * @param year The Year to be added.
     */
    private void addYear(Year year) {
        EnumMap<Session, Stack<CourseSection>> sessionMap = new EnumMap<>(Session.class);
        sections.put(year, sessionMap);
    }

    /**
     * Adds a new Session to the sections map for the provided Year.
     *
     * @param year    The Year to which the Session is added.
     * @param session The Session to be added.
     */
    private void addSession(Year year, Session session) {
        sections.get(year).put(session, new Stack<>());
    }

    /**
     * Adds a new section to the course with the provided parameters.
     *
     * @param room             The room for the new section.
     * @param enrollemntsList  The list of enrollments for the section.
     * @param teacher          The teacher for the section.
     * @param year             The Year when this course is/was available.
     * @param session          The Session when this course is/was available.
     * @param schedule         The weeklySchedule of the section
     * @throws IllegalArgumentException if any parameter is null.
     */
    public void addSection(String room, List<Enrollment> enrollemntsList, Teacher teacher, Year year, Session session, Schedule schedule) {
        if (room != null && enrollemntsList != null && teacher != null && year != null && session != null) {
            if (!sections.containsKey(year)) {
                addYear(year);
            }
            if (!sections.get(year).containsKey(session)) {
                addSession(year, session);
            }

            int sectionID = sectionIdGenerator(year, session);
            CourseSection section = new CourseSection(sectionID, this, room, enrollemntsList, teacher, year, session);
            section.setSchedule(schedule);
            sections.get(year).get(session).push(section);

        } else {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
    }

    /**
     * Adds a new section to the course with the provided Year and Session.
     *
     * @param year          The Year when this course is/was available.
     * @param session       The Session when this course is/was available.
     * @param schedule      The weeklySchedule of the section
     * @throws IllegalArgumentException if any parameter is null.
     */
    public void addSection(Year year, Session session, Schedule schedule) {
        if (year != null && session != null) {
            if (!sections.containsKey(year)) {
                addYear(year);
            }
            if (!sections.get(year).containsKey(session)) {
                addSession(year, session);
            }

            int sectionID = sectionIdGenerator(year, session);
            CourseSection section = new CourseSection(sectionID, this, year, session);
            section.setSchedule(schedule);
            sections.get(year).get(session).push(section);

        } else {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
    }

    /**
     * Generates a unique section ID for the provided Year and Session combination.
     *
     * @param year    The Year for the section.
     * @param session The Session for the section.
     * @return A unique section ID.
     */
    public int sectionIdGenerator(Year year, Session session) {
        CourseSection courseSection = sections.get(year).get(session).peek();
        if (courseSection == null) {
            return 1;
        } else {
            return courseSection.getSectionID() + 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return courseID == course.courseID && Objects.equals(courseName, course.courseName) && Objects.equals(description, course.description) && credits == course.credits && Objects.equals(sections, course.sections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseName, description, credits, sections);
    }

    @Override
    public String toString() {
        return "{" +
            " courseID='" + getCourseID() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getdescription() + "'" +
            ", credits='" + getCredits() + "'" +
            ", sections='" + getSections() + "'" +
            "}";
    }
    
}
