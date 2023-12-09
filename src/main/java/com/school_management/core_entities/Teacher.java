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
    private List<CourseSection> sectionsTaughtList;
    private List<CourseSection> sectionsCurrentlyTeachingList;
    
    // Constructor for Teacher
    public Teacher(String name, long phoneNumber, String email) {
        setTeacherID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        sectionsTaughtList = new ArrayList<>();
        sectionsCurrentlyTeachingList = new ArrayList<>();
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
        if(department == null) {
            throw new IllegalArgumentException("department cannot be null");
        }
        this.department.removeTeacher(this);
        department.addTeacher(this);
        this.department = department;
    }


    /*
    * Get courses taught by the teacher.
    * 
    * @return List<CourseSection> - Unmodifiable view of the courses taught by the teacher.
    * Returns a new ArrayList to prevent the modification of the original List.
    */
    public List<CourseSection> getCourseSectionTaught() {

        return Collections.unmodifiableList(sectionsTaughtList);
    }
    
    
    /*
    * Get sections currently taught by the teacher.
    * 
    * @return List<CourseSection> - Unmodifiable view of the sections currently taught by the teacher.
    * Returns a new ArrayList to prevent the modification of the original List.
    */
    public List<CourseSection> getsectionsCurrentlyTeachingList() {
        return Collections.unmodifiableList(sectionsCurrentlyTeachingList);
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
        return teacherID == teacher.teacherID && Objects.equals(name, teacher.name) && phoneNumber == teacher.phoneNumber && Objects.equals(email, teacher.email) && Objects.equals(department, teacher.department) && Objects.equals(sectionsTaughtList, teacher.sectionsTaughtList) && Objects.equals(sectionsCurrentlyTeachingList, teacher.sectionsCurrentlyTeachingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teacherID, name, phoneNumber, email, department, sectionsTaughtList, sectionsCurrentlyTeachingList);
    }

    @Override
    public String toString() {
        return "{" +
            " teacherID='" + getTeacherID() + "'" +
            ", name='" + getName() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", department='" + getDepartment() + "'" +
            ", sectionsTaughtList='" + getCourseSectionTaught() + "'" +
            ", sectionsCurrentlyTeachingList='" + getsectionsCurrentlyTeachingList() + "'" +
            "}";
    }
}
