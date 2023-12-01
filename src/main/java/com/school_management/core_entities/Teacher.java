package com.school_management.core_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Teacher {
    private int teacherID;
    private boolean teacherIDGenerated;
    private String name;
    private long phoneNumber;
    private String email;
    private Department department;
    private List<Course> coursesTaught;
    private List<CourseSection> sectionsCurrentlyTeaching;
    
    // Constructor for Teacher
    public Teacher(String name, long phoneNumber, String email, Department department) {
        setTeacherID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        coursesTaught = new ArrayList<>();
        sectionsCurrentlyTeaching = new ArrayList<>();
    }

    // Getter for teacher ID
    public int getTeacherID() {
        return this.teacherID;
    }

    // Setter for teacher ID
    public void setTeacherID() {
        this.teacherID = teacherIDGenerator();
    }

    // Private method to generate teacher ID
    private int teacherIDGenerator() {
        if (!teacherIDGenerated) {
            return 111111; // Temporary ID generation logic
            /*
             * generate the unique ID for teachers
             * (Actual logic for generating unique ID can be added here)
             */
        }
        return getTeacherID();
    }

    // Getters and setters for teacher details

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public long getPhoneNumber() {
        return this.phoneNumber;
    }
    
    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public Department getDepartment() {
        return this.department;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }    

    /*
    * Get courses taught by the teacher.
    * 
    * @return List<Course> - Unmodifiable view of the courses taught by the teacher.
    * Returns a new ArrayList to prevent the modification of the original List.
    */
    public List<Course> getCoursesTaught() {

        return Collections.unmodifiableList(coursesTaught);
    }
    
    /*
    * Set courses taught by the teacher.
    * 
    * @param coursesTaught - List of courses to set for the teacher.
    * Throws an IllegalArgumentException if coursesTaught is null.
    */
    public void setCourses(List<Course> coursesTaught) {

        if (coursesTaught != null) {
            this.coursesTaught = new ArrayList<>(coursesTaught);
            // Assigns a new ArrayList to prevent the modification of the original List.
        } else {
            throw new IllegalArgumentException("Courses taught cannot be null");
        }
    }
    
    /*
    * Add a course taught by the teacher.
    * 
    * @param courseTaught - Course to be added.
    * Throws an IllegalArgumentException if courseTaught is null.
    */
    public void addCourse(Course courseTaught) {
        
    
        if (courseTaught != null) {
            this.coursesTaught.add(courseTaught);
        } else {
            throw new IllegalArgumentException("Course taught cannot be null");
        }
    }
    
    /*
    * Remove a course taught by the teacher.
    * 
    * @param courseToRemove - Course to be removed.
    * Throws an IllegalArgumentException if the course is not found in the List.
    */
    public void removeCourse(Course courseToRemove) {
        boolean removed = coursesTaught.remove(courseToRemove);
        if (!removed) {
            throw new IllegalArgumentException("Course not found in the List");
        }
    }
    
    /*
    * Get sections currently taught by the teacher.
    * 
    * @return List<CourseSection> - Unmodifiable view of the sections currently taught by the teacher.
    * Returns a new ArrayList to prevent the modification of the original List.
    */
    public List<CourseSection> getsectionsCurrentlyTeaching() {
        return Collections.unmodifiableList(sectionsCurrentlyTeaching);
    }
    
    /*
    * Set sections currently taught by the teacher.
    * 
    * @param sectionsCurrentlyTeaching - List of sections to set for the teacher.
    * Throws an IllegalArgumentException if sectionsCurrentlyTeaching is null.
    */
    public void setClasses(List<CourseSection> sectionsCurrentlyTeaching) {
        if (sectionsCurrentlyTeaching != null) {
            this.sectionsCurrentlyTeaching = new ArrayList<>(sectionsCurrentlyTeaching);
        } else {
            throw new IllegalArgumentException("List of sections cannot be null");
        }
    }
    
    /*
    * Add a section currently taught by the teacher.
    * 
    * @param section - Section to be added.
    * Throws an IllegalArgumentException if section is null.
    */
    public void addSection(CourseSection section) {
        if (section != null) {
            sectionsCurrentlyTeaching.add(section);
        } else {
            throw new IllegalArgumentException("Section cannot be null");
        }
    }
    
    /*
    * Remove a section currently taught by the teacher.
    * 
    * @param section - Section to be removed.
    * Throws an IllegalArgumentException if the section is not found in the List.
    */
    public void removeSection(CourseSection section) {
        boolean removed = this.sectionsCurrentlyTeaching.remove(section);
        if (!removed) {
            throw new IllegalArgumentException("Cannot find the section in the list");
        }
    }
      

    // Overridden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Teacher)) {
            return false;
        }
        Teacher teacher = (Teacher) o;
        return teacherID == teacher.teacherID && Objects.equals(name, teacher.name) && phoneNumber == teacher.phoneNumber && Objects.equals(email, teacher.email) && Objects.equals(department, teacher.department) && Objects.equals(coursesTaught, teacher.coursesTaught) && Objects.equals(sectionsCurrentlyTeaching, teacher.sectionsCurrentlyTeaching);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID, name, phoneNumber, email, department, coursesTaught, sectionsCurrentlyTeaching);
    }

    @Override
    public String toString() {
        return "{" +
            " teacherID='" + getTeacherID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", department='" + getDepartment() + "'" +
            ", coursesTaught='" + getCoursesTaught() + "'" +
            ", sectionsCurrentlyTeaching='" + getsectionsCurrentlyTeaching() + "'" +
            "}";
    }
}
