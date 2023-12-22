/**
 * Represents an individual attendance record for a specific date and status.
 * Manages the data of an individual attendance record, including its associated date, status, and the reference to the attendance it belongs to.
 *
 * Usage:
 * Create an instance of AttendanceRecord using the constructor by providing Attendance, LocalDate, and AttendanceStatus.
 * The class handles individual records of attendance for each student on different dates.
 *
 * @see Attendance
 * @see LocalDate
 * @see AttendanceStatus
 */
    
package com.school_management.support_entities.attendance;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Attendance_record")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_record_id")
    private int attendanceRecordID;

    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "attendance_status")
    private AttendanceStatus status;

    private static Logger logger= LoggerFactory.getLogger(AttendanceRecord.class);

    // default constructor for JPA compliance
    public AttendanceRecord() {
    }

    /**
     * Constructs an AttendanceRecord with the provided parameters.
     * @param attendance The associated Attendance.
     * @param date The date of the attendance record.
     * @param status The status of the attendance for the specified date.
     * @throws IllegalArgumentException if any parameter is null.
     */
    public AttendanceRecord(Attendance attendance, LocalDate date, AttendanceStatus status) {
        if(attendance == null || date == null || status == null) {
            logger.error("parameters are null", new IllegalArgumentException());
            throw new IllegalArgumentException("parameters cannot be null cannot be null");
        }
        this.attendance = attendance;
        this.date = date;
        this.status = status;
        logger.info("Attendance Record {} initialized", getAttendanceRecordID());
    }

    public int getAttendanceRecordID() {
        return this.attendanceRecordID;
    }

    public void setAttendanceRecordID(int attendanceRecordID) {
        this.attendanceRecordID = attendanceRecordID;
    }

    public Attendance getAttendance() {
        return this.attendance;
    }

    public void setAttendance(Attendance attendance) {
        if(attendance == null) {
            logger.error("attendance is null", new IllegalArgumentException());
            throw new IllegalArgumentException("attendance cannot be null");
        }
        this.attendance = attendance;
        logger.info("attendance for {} has been modified to {}", getAttendanceRecordID(), getAttendance().getAttendanceID());
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        if(date == null) {
            logger.error("date is null", new IllegalArgumentException());
            throw new IllegalArgumentException("date cannot be null");
        }
        this.date = date;
        logger.info("date for {} has been modified to {}", getAttendanceRecordID(), getDate());
    }

    public AttendanceStatus getStatus() {
        return this.status;
    }

    public void setStatus(AttendanceStatus status) {
        if(status == null) {
            logger.error("status is null", new IllegalArgumentException());
            throw new IllegalArgumentException("status cannot be null");
        }
        this.status = status;
        logger.info("status for {} has been modified to {}", getAttendanceRecordID(), getStatus());
    }

    // Override methods for proper functionality

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AttendanceRecord)) {
            return false;
        }
        AttendanceRecord attendanceRecord = (AttendanceRecord) o;
        return attendanceRecordID == attendanceRecord.attendanceRecordID && Objects.equals(attendance, attendanceRecord.attendance) && Objects.equals(date, attendanceRecord.date) && Objects.equals(status, attendanceRecord.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attendanceRecordID, attendance, date, status);
    }

    @Override
    public String toString() {
        return "{" +
            " attendanceRecordID='" + getAttendanceRecordID() + "'" +
            ", attendance='" + getAttendance() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
    
}
