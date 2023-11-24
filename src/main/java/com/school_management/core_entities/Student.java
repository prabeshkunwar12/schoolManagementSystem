package com.school_management.core_entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import com.school_management.support_entities.YearStanding;

public class Student {
    private int studentID;
    private boolean studentIDGenerated = false;
    private String name;
    private String address;
    private Date dateOfBirth;
    private String email;
    private long phoneNumber;
    private YearStanding yearStanding;
    private String guardianName;
    private long gurdianContactNumber;
    private String gurdianEmail;
    private List<Course> enrolledCourses;
    private List<Course> completedCourses;



    public Student() {
    }

    public Student(String name, String address, Date dateOfBirth, String email) {
        setStudentID();
        this.name = name;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phoneNumber = 0;
        this.yearStanding = YearStanding.FIRST_YEAR;
        this.guardianName = "not set";
        this.gurdianContactNumber = 0;
        this.gurdianEmail = "none";
        this.enrolledCourses = new ArrayList<>();
        this.completedCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return this.studentID;
    }

    public void setStudentID() {
        this.studentID = studentIDGenerator();
    }

    private int studentIDGenerator() {
        if(!studentIDGenerated) {
            return 111111;
            /*
             * generate the unique id for students
            */
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

    public long getGurdianContactNumber() {
        return this.gurdianContactNumber;
    }

    public void setGurdianContactNumber(long gurdianContactNumber) {
        this.gurdianContactNumber = gurdianContactNumber;
    }

    public String getGurdianEmail() {
        return this.gurdianEmail;
    }

    public void setGurdianEmail(String gurdianEmail) {
        this.gurdianEmail = gurdianEmail;
    }

    public List<Course> getEnrolledCourses() {
        return this.enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public List<Course> getCompletedCourses() {
        return this.completedCourses;
    }

    public void setCompletedCourses(List<Course> completedCourses) {
        this.completedCourses = completedCourses;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Student)) {
            return false;
        }
        Student student = (Student) o;
        return studentID == student.studentID && Objects.equals(name, student.name) && Objects.equals(address, student.address) && Objects.equals(dateOfBirth, student.dateOfBirth) && Objects.equals(email, student.email) && phoneNumber == student.phoneNumber && Objects.equals(yearStanding, student.yearStanding) && Objects.equals(guardianName, student.guardianName) && gurdianContactNumber == student.gurdianContactNumber && Objects.equals(gurdianEmail, student.gurdianEmail) && Objects.equals(enrolledCourses, student.enrolledCourses) && Objects.equals(completedCourses, student.completedCourses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentID, name, address, dateOfBirth, email, phoneNumber, yearStanding, guardianName, gurdianContactNumber, gurdianEmail, enrolledCourses, completedCourses);
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
            ", gurdianContactNumber='" + getGurdianContactNumber() + "'" +
            ", gurdianEmail='" + getGurdianEmail() + "'" +
            ", enrolledCourses='" + getEnrolledCourses() + "'" +
            ", completedCourses='" + getCompletedCourses() + "'" +
            "}";
    }
    
}
