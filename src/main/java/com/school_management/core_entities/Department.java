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

    /**
     * Constructor to initialize department details.
     *
     * @param departmentID The unique identifier for the department.
     * @param departmentName The name of the department (must not be null).
     * @param description The description of the department (must not be null).
     * @throws IllegalArgumentException If departmentName or description is null.
     */
    public Department(int departmentID, String departmentName, String description) {
        if (departmentName == null || description == null) {
            throw new IllegalArgumentException("Department name or description cannot be null");
        }
        this.departmentID = departmentID;
        this.departmentName = departmentName;
        this.headOfDepartment = null; // Initially no head of department assigned
        this.description = description;
        this.teachersList = new ArrayList<>(); // Initialize an empty list of teachers
        this.coursesOffered = new ArrayList<>(); // Initialize an empty list of courses
    }

    // Getters and setters for department details

    public int getDepartmentID() {
        return this.departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return this.departmentName;
    }

    /**
     * Set department name, ensuring it's not null.
     *
     * @param departmentName The name of the department (must not be null).
     * @throws IllegalArgumentException If departmentName is null.
     */
    public void setDepartmentName(String departmentName) {
        if (departmentName == null) {
            throw new IllegalArgumentException("Department name cannot be null");
        }
        this.departmentName = departmentName;
    }

    public Teacher getHeadOfDepartment() {
        return this.headOfDepartment;
    }

    /**
     * Set Head of Department, ensuring it's not null.
     *
     * @param headOfDepartment The name of the department (must not be null).
     * @throws IllegalArgumentException If headOfDepartment is null.
     */
    public void setHeadOfDepartment(Teacher headOfDepartment) {
        if (headOfDepartment == null) {
            throw new IllegalArgumentException("Head of Department cannot be null");
        }
        this.headOfDepartment = headOfDepartment;
    }

    // Get department description
    public String getDescription() {
        return this.description;
    }

    /**
     * Set department description, ensuring it's not null.
     *
     * @param description The description of the department (must not be null).
     * @throws IllegalArgumentException If description is null.
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        this.description = description;
    }

    // Accessor methods for teachers list

    /**
     * Get an unmodifiable view of the teachers list.
     *
     * @return Unmodifiable list of teachers.
     */
    public List<Teacher> getTeachersList() {
        return Collections.unmodifiableList(new ArrayList<>(teachersList));
    }

    /**
     * Set the teachers list, ensuring it's not null.
     *
     * @param teachersList The list of teachers (must not be null).
     * @throws IllegalArgumentException If teachersList is null.
     */
    public void setTeachersList(List<Teacher> teachersList) {
        if (teachersList == null) {
            throw new IllegalArgumentException("Teachers list cannot be null");
        }
        this.teachersList = new ArrayList<>(teachersList);
    }

    /**
     * Add a teacher to the teachers list, ensuring it's not null.
     *
     * @param teacher The teacher to be added (must not be null).
     * @throws IllegalArgumentException If teacher is null.
     */
    public void addTeacher(Teacher teacher) {
        if (teacher != null) {
            teachersList.add(teacher);
        } else {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
    }

    /**
     * Remove a teacher from the teachers list.
     *
     * @param teacher The teacher to be removed.
     * @throws IllegalArgumentException If teacher not found in the list.
     */
    public void removeTeacher(Teacher teacher) {
        boolean removed = teachersList.remove(teacher);
        if (!removed) {
            throw new IllegalArgumentException("Teacher not found in the list");
        }
    }

    // Accessor methods for coursesOffered list

    /**
     * Get an unmodifiable view of the coursesOffered list.
     *
     * @return Unmodifiable list of offered courses.
     */
    public List<Course> getCoursesOffered() {
        return Collections.unmodifiableList(new ArrayList<>(coursesOffered));
    }

    /**
     * Set the coursesOffered list, ensuring it's not null.
     *
     * @param coursesOffered The list of offered courses (must not be null).
     * @throws IllegalArgumentException If coursesOffered is null.
     */
    public void setCoursesOffered(List<Course> coursesOffered) {
        if(coursesOffered == null){
            throw new IllegalArgumentException("Courses offered cannot be null");
        }
        this.coursesOffered = new ArrayList<>(coursesOffered);
    }

    /**
     * Add a course to the coursesOffered list, ensuring it's not null.
     *
     * @param course The course to be added (must not be null).
     * @throws IllegalArgumentException If course is null.
     */
    public void addCourse(Course course) {
        if (course != null) {
            coursesOffered.add(course);
        } else {
            throw new IllegalArgumentException("Course cannot be null");
        }
    }

    /**
     * Remove a course from the coursesOffered list.
     *
     * @param course The course to be removed.
     * @throws IllegalArgumentException If course not found in the list.
     */
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
