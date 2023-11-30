package com.school_management.core_entities;
import java.util.Objects;

public class Enrollment {
    Student student;
    CourseSection courseSection;

    public Enrollment(Student student, CourseSection courseSection) {
        this.student = student;
        this.courseSection = courseSection;
    }

    public Student getStudent() {
        return this.student;
    }

    public CourseSection getCourseSection() {
        return this.courseSection;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Enrollment)) {
            return false;
        }
        Enrollment enrollment = (Enrollment) o;
        return Objects.equals(student, enrollment.student) && Objects.equals(courseSection, enrollment.courseSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, courseSection);
    }

    @Override
    public String toString() {
        return "{" +
            " student='" + getStudent() + "'" +
            ", courseSection='" + getCourseSection() + "'" +
            "}";
    }
    
}
