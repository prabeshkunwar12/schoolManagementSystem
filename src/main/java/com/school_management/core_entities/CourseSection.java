package com.school_management.core_entities;
import java.util.List;
import java.util.Objects;

public class CourseSection  {
    private int sectionID;
    private Course course;
    private String room;
    private List<Student> studentsList;
    private Teacher teacher;
    

    public CourseSection() {
    }

    public CourseSection(int sectionID, Course course, String room, List<Student> studentsList, Teacher teacher) {
        this.sectionID = sectionID;
        this.course = course;
        this.room = room;
        this.studentsList = studentsList;
        this.teacher = teacher;
    }

    public CourseSection(int sectionID, Course course) {
        this.sectionID = sectionID;
        this.course = course;
    }

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

    public List<Student> getStudentsList() {
        return this.studentsList;
    }

    public void setStudentsList(List<Student> studentsList) {
        this.studentsList = studentsList;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public CourseSection sectionID(int sectionID) {
        setSectionID(sectionID);
        return this;
    }

    public CourseSection course(Course course) {
        setCourse(course);
        return this;
    }

    public CourseSection room(String room) {
        setRoom(room);
        return this;
    }

    public CourseSection studentsList(List<Student> studentsList) {
        setStudentsList(studentsList);
        return this;
    }

    public CourseSection teacher(Teacher teacher) {
        setTeacher(teacher);
        return this;
    }

    public boolean isPassed(Enrollment enrollment) {
        enrollment.getStudent();
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CourseSection)) {
            return false;
        }
        CourseSection courseSection = (CourseSection) o;
        return sectionID == courseSection.sectionID && Objects.equals(course, courseSection.course) && Objects.equals(room, courseSection.room) && Objects.equals(studentsList, courseSection.studentsList) && Objects.equals(teacher, courseSection.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionID, course, room, studentsList, teacher);
    }

    @Override
    public String toString() {
        return "{" +
            " sectionID='" + getSectionID() + "'" +
            ", course='" + getCourse() + "'" +
            ", room='" + getRoom() + "'" +
            ", studentsList='" + getStudentsList() + "'" +
            ", teacher='" + getTeacher() + "'" +
            "}";
    }
    
}
