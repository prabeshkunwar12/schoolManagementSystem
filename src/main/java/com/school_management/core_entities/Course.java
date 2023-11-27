package com.school_management.core_entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private int courseID;
    private String courseName;
    private String description;
    private int credits;
    private List<CourseSection> sections;
    
    public Course(int courseID, String courseName, String description, int credits, int numberOfSections) {
        this.courseID = courseID;
        this.courseName = courseName;
        this.description = description;
        this.credits = credits;
        setSections(numberOfSections);
    }

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
        return this.sections;
    }

    public void setSections(int numberOfSections){
        this.sections = new ArrayList<>();
        for(int i=1; i<=numberOfSections;i++){
            sections.add(new CourseSection(i, this));
        }
    }

    public void addSection(String room, List<Student> studentsList, Teacher teacher) {
        int sectionID = sections.size()+1;
        sections.add(new CourseSection(sectionID, this, room, studentsList, teacher));
    }

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
