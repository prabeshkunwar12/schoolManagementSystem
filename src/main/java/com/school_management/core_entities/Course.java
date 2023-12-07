package com.school_management.core_entities;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.school_management.support_entities.SchoolYear;
import java.util.Objects;

public class Course {
    private int courseID;
    private String courseName;
    private String description;
    private int credits;
    private List<SchoolYear> schoolYears;
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
        schoolYears = new ArrayList<>();
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCredits() {
        return this.credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<SchoolYear> getSchoolYearList() {
        return Collections.unmodifiableList(schoolYears);
    }

    public void addSchoolYear(SchoolYear schoolYear) {
        if(schoolYear == null) {
            throw new IllegalArgumentException("school year cannot be null");
        } else {
            if(containsYear(schoolYear.getYear())) {
                throw new IllegalArgumentException("This course already contains this school year.");
            } else {
                schoolYears.add(schoolYear);
            }
        }
    }

    public int getIndexOfSchoolYear(Year year) {
        for(SchoolYear schoolYear: schoolYears) {
            if(schoolYear.getYear() == year) {
                return schoolYears.indexOf(schoolYear);
            }
        }
        return -1;
    }

    public boolean containsYear(Year year) {
        for(SchoolYear schoolYear: schoolYears) {
            if(schoolYear.getYear() == year) {
                return true;
            }
        }
        return false;
    }

    public SchoolYear getSchoolYear(int index) {
        return schoolYears.get(index);
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Course)) {
            return false;
        }
        Course course = (Course) o;
        return courseID == course.courseID && Objects.equals(courseName, course.courseName) && Objects.equals(description, course.description) && credits == course.credits && Objects.equals(schoolYears, course.schoolYears);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, courseName, description, credits, schoolYears);
    }


    @Override
    public String toString() {
        return "{" +
            " courseID='" + getCourseID() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            ", credits='" + getCredits() + "'" +
            ", schoolYears='" + getSchoolYearList() + "'" +
            "}";
    }
   
}
