package com.school_management.core_entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.school_management.support_entities.YearStanding;

public class Student {
    // Logger for logging messages related to the Student class
    private static final Logger logger = LoggerFactory.getLogger(Student.class);

    // Unique identifier for the student
    private int studentID;

    // Flag to track whether the student ID has been generated
    private boolean studentIDGenerated = false;

    // Student's personal details
    private String name;
    private String address;
    private Date dateOfBirth;
    private String email;
    private long phoneNumber;

    // Year standing of the student (e.g., First year, Second year, etc.)
    private YearStanding yearStanding;

    // Guardian details
    private String guardianName;
    private long guardianContactNumber;
    private String guardianEmail;

    // List of courses in which the student is currently enrolled
    private List<Enrollment> enrolledCourses;

    // List of courses completed by the student
    private List<Enrollment> completedCourses;

    // Default constructor
    public Student() {
    }

    // Constructor to initialize mandatory personal details of the student
    public Student(String name, String address, Date dateOfBirth, String email) {
        // Generates a unique student ID
        setStudentID();

        // Set basic personal details
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;

        // Default values for other attributes
        this.phoneNumber = 0;
        this.yearStanding = YearStanding.FIRST_YEAR;
        this.guardianName = "not set";
        this.guardianContactNumber = 0;
        this.guardianEmail = "none";

        // Initialize lists for enrolled and completed courses
        this.enrolledCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
    }

    // Getters and setters 

    public int getStudentID() {
        return this.studentID;
    }

    public void setStudentID() {
        this.studentID = studentIDGenerator();
    }

    // Private method to generate a unique student ID
    private int studentIDGenerator() {
        if (!studentIDGenerated) {
            return 111111; // Replace with actual logic for generating a unique ID
        }
        return getStudentID();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public YearStanding getYearStanding() {
        return this.yearStanding;
    }

    public void setYearStanding(YearStanding yearStanding) {
        this.yearStanding = yearStanding;
    }

    public String getGuardianName() {
        return this.guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public long getGuardianContactNumber() {
        return this.guardianContactNumber;
    }

    public void setGuardianContactNumber(long guardianContactNumber) {
        this.guardianContactNumber = guardianContactNumber;
    }

    public String getGuardianEmail() {
        return this.guardianEmail;
    }

    public void setGuardianEmail(String guardianEmail) {
        this.guardianEmail = guardianEmail;
    }

    /**
     * Returns a read-only view of the list of courses in which the student is enrolled.
     *
     * @return Unmodifiable list of enrolled courses
     */
    public List<Enrollment> getEnrolledCourses() {
        return Collections.unmodifiableList(enrolledCourses);
    }

    /**
     * Sets the list of courses in which the student is enrolled.
     *
     * @param enrolledCourses List of enrolled courses
     * @throws IllegalArgumentException if enrolledCourses is null
     */
    public void setEnrolledCourses(List<Enrollment> enrolledCourses) {
        if(enrolledCourses != null) {
            this.enrolledCourses = new ArrayList<>(enrolledCourses);
        } else {
            throw new IllegalArgumentException("Enrolled courses cannot be null");
        }
    }

    /**
     * Adds an enrollment to the list of courses in which the student is enrolled.
     *
     * @param enrollment The enrollment to be added
     * @throws IllegalArgumentException if enrollment is null
     */
    public void addCourse(Enrollment enrollment) {
        if(enrollment != null) {
            this.enrolledCourses.add(enrollment);
        } else {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }
    }

    /**
     * Adds a course section to the list of courses in which the student is enrolled.
     *
     * @param courseSection The course section to be added
     * @throws IllegalArgumentException if courseSection is null
     */
    public void addCourse(CourseSection courseSection) {
        if(courseSection != null) {
            Enrollment enrollment = new Enrollment(this, courseSection);
            addCourse(enrollment);
        } else {
            throw new IllegalArgumentException("Course Section cannot be null");
        }
    }

    /**
     * Removes an enrollment from the list of courses in which the student is enrolled.
     *
     * @param enrollment The enrollment to be removed
     * @throws IllegalArgumentException if enrollment is not found in the list
     */
    public void removeCourse(Enrollment enrollment) {
        boolean removed = this.enrolledCourses.remove(enrollment);
        if(!removed) {
            throw new IllegalArgumentException("Course Section not found in the list");
        }
    }

    /**
     * Removes a course section from the list of courses in which the student is enrolled.
     *
     * @param courseSection The course section to be removed
     */
    public void removeCourse(CourseSection courseSection) {
        Enrollment enrollment = new Enrollment(this, courseSection);
        removeCourse(enrollment);
    }

    /**
     * Returns a read-only view of the list of courses completed by the student.
     *
     * @return Unmodifiable list of completed courses
     */
    public List<Enrollment> getCompletedCourses() {
        return Collections.unmodifiableList(completedCourses);
    }

    /**
     * Marks an enrollment as complete and moves it to the completed courses list.
     *
     * @param enrollment The enrollment to be marked as complete
     * @return True if the course is marked as complete, false otherwise
     * @throws IllegalArgumentException if enrollment is null
     */
    public boolean setCourseAsComplete(Enrollment enrollment) {
        if(enrollment != null) {
            if(enrollment.getCourseSection().isPassed(enrollment)) {
                this.completedCourses.add(enrollment);
                this.removeCourse(enrollment);
                return true;
            } else {
                logger.warn("Student didn't meet the criteria to pass the course");
                return false;

            }
        } else {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }
    }

    // Equals, hashCode, and toString methods
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return studentID == student.studentID && Objects.equals(name, student.name) && Objects.equals(address, student.address) && Objects.equals(dateOfBirth, student.dateOfBirth) && Objects.equals(email, student.email) && phoneNumber == student.phoneNumber && Objects.equals(yearStanding, student.yearStanding) && Objects.equals(guardianName, student.guardianName) && guardianContactNumber == student.guardianContactNumber && Objects.equals(guardianEmail, student.guardianEmail) && Objects.equals(enrolledCourses, student.enrolledCourses) && Objects.equals(completedCourses, student.completedCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, address, dateOfBirth, email, phoneNumber, yearStanding, guardianName, guardianContactNumber, guardianEmail, enrolledCourses, completedCourses);
    }

    @Override
    public String toString() {
        return "{" +
            " studentID='" + getStudentID() + "'" +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", yearStanding='" + getYearStanding() + "'" +
            ", guardianName='" + getGuardianName() + "'" +
            ", guardianContactNumber='" + getGuardianContactNumber() + "'" +
            ", guardianEmail='" + getGuardianEmail() + "'" +
            ", enrolledCourses='" + getEnrolledCourses() + "'" +
            ", completedCourses='" + getCompletedCourses() + "'" +
            "}";
    }
    
}
