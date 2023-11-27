package com.school_management.core_entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Department {
    // Member variables for department details
    private int departmentID;
    private String departmentName;
    private Teacher headOfDepartment;
    private String description;
    private List<Teacher> teachersList;
    private List<Course> coursesOffered;

    // Constructor to initialize department details
    public Department(int departmentID, String departmentName, String description) {
        // Ensure the department name and description are not null
        if (departmentName == null || description == null) {
            throw new IllegalArgumentException("Department name or description cannot be null");
        }
        // Assign values to department details
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.headOfDepartment = null; // Initially no head of department assigned
        this.description = description;
        this.teachersList = new ArrayList<>(); // Initialize an empty list of teachers
        this.coursesOffered = new ArrayList<>(); // Initialize an empty list of courses
    }

    // Getters and setters for department details

    // Get department ID
    public int getDepartmentID() {
        return this.departmentID;
    }

    // Set department ID
    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    // Get department name
    public String getDepartmentName() {
        return this.departmentName;
    }

    // Set department name, ensuring it's not null
    public void setDepartmentName(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("Department name cannot be null");
        }
        this.departmentName = departmentName;
    }

    // Get head of department
    public Teacher getHeadOfDepartment() {
        return this.headOfDepartment;
    }

    // Set head of department
    public void setHeadOfDepartment(Teacher headOfDepartment) {
        this.headOfDepartment = headOfDepartment;
    }

    // Get department description
    public String getDescription() {
        return this.description;
    }

    // Set department description, ensuring it's not null
    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    // Accessor methods for teachers list

    // Get an unmodifiable view of the teachers list
    public List<Teacher> getTeachersList() {
        return Collections.unmodifiableList(new ArrayList<>(teachersList));
        // Returns an unmodifiable view to prevent direct modifications
    }

    // Set the teachers list, ensuring it's not null
    public void setTeachersList(List<Teacher> teachersList) {
        if (teachersList == null) {
            throw new IllegalArgumentException("Teachers list cannot be null");
        }
        this.teachersList = new ArrayList<>(teachersList);
        // Creates a new ArrayList to avoid direct modifications to the original list
    }

    // Add a teacher to the teachers list, ensuring it's not null
    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            teachersList.add(teacher);
        } else {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
    }

    // Remove a teacher from the teachers list
    public void removeTeacher(Teacher teacher) {
        boolean removed = teachersList.remove(teacher);
        if (!removed) {
            throw new IllegalArgumentException("Teacher not found in the list");
        }
    }

    // Accessor methods for coursesOffered list

    // Get an unmodifiable view of the coursesOffered list
    public List<Course> getCoursesOffered() {
        return Collections.unmodifiableList(new ArrayList<>(coursesOffered));
        // Creates a new ArrayList to avoid direct modifications to the original list
    }

    // Set the coursesOffered list, ensuring it's not null
    public void setCoursesOffered(List<Course> coursesOffered) {
        if(coursesOffered == null){
            throw new IllegalArgumentException("Courses offered cannot be null");
        }
        this.coursesOffered = new ArrayList<>(coursesOffered);
        // Creates a new ArrayList to avoid direct modifications to the original list
    }

    // Add a course to the coursesOffered list, ensuring it's not null
    public void addCourse(Course course) {
        if (course != null) {
            coursesOffered.add(course);
        } else {
            throw new IllegalArgumentException("Course cannot be null");
        }
    }

    // Remove a course from the coursesOffered list
    public void removeCourse(Course course) {
        boolean removed = coursesOffered.remove(course);
        if(!removed) {
            throw new IllegalArgumentException("Course not found on the list");
        }
    }

    // Overridden equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Department)) {
            return false;
        }
        Department department = (Department) o;
        return departmentID == department.departmentID && Objects.equals(departmentName, department.departmentName) && Objects.equals(headOfDepartment, department.headOfDepartment) && Objects.equals(description, department.description) && Objects.equals(teachersList, department.teachersList) && Objects.equals(coursesOffered, department.coursesOffered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(departmentID, departmentName, headOfDepartment, description, teachersList, coursesOffered);
    }

    @Override
    public String toString() {
        return "{" +
            " departmentID='" + getDepartmentID() + "'" +
            ", departmentName='" + getDepartmentName() + "'" +
            ", headOfDepartment='" + getHeadOfDepartment() + "'" +
            ", description='" + getDescription() + "'" +
            ", teachersList='" + getTeachersList() + "'" +
            ", coursesOffered='" + getCoursesOffered() + "'" +
            "}";
    }
    
}
