/**
 * The Attendance class manages and tracks the attendance of students for a specific CourseSectionSchedule.
 * It maintains a record of attendance status for each scheduled date within the course section's schedule.
 *
 * Functionalities:
 * - Stores and manages attendance status for scheduled dates within the CourseSectionSchedule.
 * - Allows retrieval of the CourseSectionSchedule and the attendance status list for each scheduled date.
 * - Provides a method to assign/update attendance status for specific dates.
 *
 * Usage:
 * This class is utilized to monitor and manage the attendance of students within a particular course section.
 * It uses the CourseSectionSchedule associated with the course section to initialize the attendance list
 * and allows the modification of attendance status for each scheduled date as needed.
 *
 * Note: The attendance status follows an enum type (AttendanceStatus) and is linked to specific dates in
 * the course section schedule. It's intended to maintain an accurate record of student attendance.
 */
package com.school_management.support_entities.attendance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.schedule.CourseSectionSchedule;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id")
    private int attendanceID;
    
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private CourseSectionSchedule schedule;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "attendance_id")
    private List<AttendanceRecord> attendanceRecordList;


    private static Logger logger = LoggerFactory.getLogger(Attendance.class);
    
    // default constructor for JPA compliance
    public Attendance() {}

    public Attendance(CourseSectionSchedule schedule) {
        if(schedule == null) {
            logger.error("CourseSectionSchedule is null", new IllegalArgumentException());
            throw new IllegalArgumentException("schedule cannot be null");
        }
        this.schedule = schedule;
        initializeAttendanceRecordList();
        logger.info("New attendance created");
    }

    public int getAttendanceID() {
        return attendanceID;
    }

    public CourseSectionSchedule getSchedule() {
        return this.schedule;
    }

    //JPA compliance
    public void setSchedule(CourseSectionSchedule schedule) {
        if(schedule == null) {
            logger.error("schedule is null", new IllegalArgumentException());
            throw new IllegalArgumentException("schedule cannot be null");
        }
        this.schedule = schedule;
        logger.info("schedule for {} has been modified to {}", getAttendanceID(), getSchedule().getCourseSectionScheduleID());
    }

    public List<AttendanceRecord> getAttendanceRecordList() {
        return Collections.unmodifiableList(attendanceRecordList);
    }

    //jpa compliance
    public void setAttendanceRecordList(List<AttendanceRecord> attendanceRecordList) {
        if(attendanceRecordList == null) {
            logger.error("attendanceRecordList is null", new IllegalArgumentException());
            throw new IllegalArgumentException("attendanceRecordList cannot be null");
        }
        this.attendanceRecordList = new ArrayList<>(attendanceRecordList);
        logger.info("attendanceRecordList for {} has been modified", getAttendanceID());
    }

    private void initializeAttendanceRecordList() {
        attendanceRecordList = new ArrayList<>();
        List<LocalDate> dates = getSchedule().getDateList();

        for(LocalDate date:dates) {
            attendanceRecordList.add(new AttendanceRecord(this, date, AttendanceStatus.NA));
        }
    }

    
    public boolean assignStatus(LocalDate date, AttendanceStatus attendanceStatus) {
        boolean dateFound = false;

        for (AttendanceRecord attendanceRecord : attendanceRecordList) {
            if (attendanceRecord.getDate().equals(date)) {
                attendanceRecord.setStatus(attendanceStatus);
                dateFound = true;
                break;
            }
        }

        if (!dateFound) {
            logger.error("Date not found in the list", new IllegalArgumentException());
            return false;
        }

        logger.info("Attendance status for date {} changed to {}", date, attendanceStatus);
        return true;
    }

    // Override methods for equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Attendance)) {
            return false;
        }
        Attendance attendance = (Attendance) o;
        return Objects.equals(schedule, attendance.schedule) && Objects.equals(attendanceRecordList, attendance.attendanceRecordList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule, attendanceRecordList);
    }

    @Override
    public String toString() {
        return "{" +
            " schedule='" + getSchedule() + "'" +
            ", attendanceRecordList='" + getAttendanceRecordList() + "'" +
            "}";
    }
}