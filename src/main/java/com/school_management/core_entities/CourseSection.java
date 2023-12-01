package com.school_management.core_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class CourseSection  {
    private int sectionID;
    private Course course;
    private String room;
    private List<Enrollment> enrollments;
    private Teacher teacher;
    
    /**
     * Constructor to initialize a CourseSection with provided parameters.
     *
     * @param sectionID     The unique identifier for the section.
     * @param course        The associated Course.
     * @param room          The room where the section is conducted.
     * @param enrollments   List of enrollments in the section.
     * @param teacher       The teacher assigned to the section.
     */
    public CourseSection(int sectionID, Course course, String room, List<Enrollment> enrollments, Teacher teacher) {
        this.sectionID = sectionID;
        this.course = course;
        this.room = room;
        this.enrollments = enrollments;
        this.teacher = teacher;
    }

    /**
     * Constructor to initialize a CourseSection with given sectionID and course.
     *
     * @param sectionID     The unique identifier for the section.
     * @param course        The associated Course.
     */
    public CourseSection(int sectionID, Course course) {
        this.sectionID = sectionID;
        this.course = course;
    }

    // Getters and setters

    public int getSectionID() {
        return this.sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
    }

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getRoom() {
        return this.room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    /**
     * Retrieves the list of enrollments for this course section.
     *
     * @return An unmodifiable list of enrollments.
     */
    public List<Enrollment> getEnrollments() {
        return Collections.unmodifiableList(this.enrollments);
    }

    /**
     * Sets the enrollments for this course section.
     *
     * @param enrollments The list of enrollments to set.
     * @throws IllegalArgumentException if the provided list is null.
     */
    public void setEnrollments(List<Enrollment> enrollments) {
        if(enrollments != null) {
            this.enrollments = new ArrayList<>(enrollments);
        } else {
            throw new IllegalArgumentException("list of enrollments cannot be null");
        }
    }

    /**
     * Adds an enrollment to the list of enrollments for this section.
     *
     * @param enrollment The enrollment to add.
     * @throws IllegalArgumentException if enrollment is null.
     */
    public void addEnrollment(Enrollment enrollment) {
        if(enrollment != null) {
            this.enrollments.add(enrollment);
        }
        else {
            throw new IllegalArgumentException("enrollment cannot be null");
        } 
    }

    /**
     * Removes an enrollment from the list of enrollments for this section.
     *
     * @param enrollment The enrollment to remove.
     * @throws IllegalArgumentException if enrollment is not found in the list.
     */
    public void removeEnrollment(Enrollment enrollment) {
        boolean removed = this.enrollments.remove(enrollment);
        if(!removed) {
            throw new IllegalArgumentException("enrollment not found in the list");
        }
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    /**
     * Sets the teacher for this course section.
     *
     * @param teacher The teacher to be assigned.
     * @throws IllegalArgumentException if the provided teacher is null.
     */
    public void setTeacher(Teacher teacher) {
        if(teacher != null) {
            this.teacher = teacher;
        } else {
            throw new IllegalArgumentException("teacher cannot be null");
        }
    }

    /**
     * Checks if the provided enrollment exists in the list.
     *
     * @param enrollment The enrollment to check.
     * @return True if the enrollment is passed, false otherwise.
     */
    public boolean isPassed(Enrollment enrollment) {
        if(this.enrollments.contains(enrollment)) {
            return true;
        } else {
            throw new IllegalArgumentException("enrollment not found in the list");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CourseSection)) {
            return false;
        }
        CourseSection courseSection = (CourseSection) o;
        return sectionID == courseSection.sectionID && Objects.equals(course, courseSection.course) && Objects.equals(room, courseSection.room) && Objects.equals(enrollments, courseSection.enrollments) && Objects.equals(teacher, courseSection.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionID, course, room, enrollments, teacher);
    }

    @Override
    public String toString() {
        return "{" +
            " sectionID='" + getSectionID() + "'" +
            ", course='" + getCourse() + "'" +
            ", room='" + getRoom() + "'" +
            ", enrollments='" + getEnrollments() + "'" +
            ", teacher='" + getTeacher() + "'" +
            "}";
    }
    
}
