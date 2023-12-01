package com.school_management.core_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Course {
    private int courseID;
    private String courseName;
    private String description;
    private int credits;
    private List<CourseSection> sections;
    
    /**
     * Constructor to initialize a Course with given parameters.
     *
     * @param courseID       The unique identifier for the course.
     * @param courseName     The name of the course.
     * @param description    The description of the course.
     * @param credits        The number of credits for the course.
     * @param numberOfSections The number of sections for the course.
     * @throws IllegalArgumentException if the number of sections is 0.
     */
    public Course(int courseID, String courseName, String description, int credits, int numberOfSections) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        setSections(numberOfSections);
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

    public List<CourseSection> getSections() {
        return Collections.unmodifiableList(sections);
    }

    /**
     * Sets the number of sections for the course and initializes sections accordingly.
     *
     * @param numberOfSections The number of sections to set.
     * @throws IllegalArgumentException if the number of sections is 0.
     */
    public void setSections(int numberOfSections){
        if(numberOfSections != 0) {
            this.sections = new ArrayList<>();
            for(int i=1; i<=numberOfSections;i++){
                sections.add(new CourseSection(i, this));
            }
        } else {
            throw new IllegalArgumentException("number of sections cannot be 0");
        }
    }

     /**
     * Adds a new section to the course with provided parameters.
     *
     * @param room             The room for the new section.
     * @param enrollemntsList  The list of enrollments for the section.
     * @param teacher          The teacher for the section.
     * @throws IllegalArgumentException if any parameter is null.
     */
    public void addSection(String room, List<Enrollment> enrollemntsList, Teacher teacher) {
        if(room != null && enrollemntsList != null && teacher != null) {
            int sectionID = sections.size()+1;
            sections.add(new CourseSection(sectionID, this, room, enrollemntsList, teacher));
        } else {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
    }

    /**
     * Adds a new section to the course.
     * Automatically assigns the next section ID.
     */
    public void addSection(){
        int sectionID = sections.size()+1;
        sections.add(new CourseSection(sectionID, this));
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
