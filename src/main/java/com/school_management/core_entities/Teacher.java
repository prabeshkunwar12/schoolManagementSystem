package com.school_management.core_entities;
import java.util.ArrayList;
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
    
    public Teacher(String name, long phoneNumber, String email, Department department) {
        setTeacherID();
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.department = department;
        coursesTaught = new ArrayList<>();
        sectionsCurrentlyTeaching = new ArrayList<>();
    }

    public int getTeacherID() {
        return this.teacherID;
    }

    public void setTeacherID() {
        this.teacherID = teacherIDGenerator();
    }

    private int teacherIDGenerator() {
        if(!teacherIDGenerated) {
            return 111111;
            /*
             * generate the unique id for students
            */
        }
        return getTeacherID();
    }

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

    public List<Course> getCoursesTaught() {
        return this.coursesTaught;
    }

    public void setCourses(List<Course> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public void addCourse(Course courseTaught){
        this.coursesTaught.add(courseTaught);
    }

    public void removeCourse(Course courseToRemove){
        if(coursesTaught.contains(courseToRemove))
            this.coursesTaught.remove(courseToRemove);
    }

    public List<CourseSection> getsectionsCurrentlyTeaching() {
        return this.sectionsCurrentlyTeaching;
    }

    public void setClasses(List<CourseSection> sectionsCurrentlyTeaching) {
        this.sectionsCurrentlyTeaching = sectionsCurrentlyTeaching;
    }

    public void addSection(CourseSection section){
        this.sectionsCurrentlyTeaching.add(section);
    } 
    public void removeSection(CourseSection section){
        if(sectionsCurrentlyTeaching.contains(section))
            sectionsCurrentlyTeaching.remove(section);
    }   

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
