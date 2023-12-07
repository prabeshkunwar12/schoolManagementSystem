/**
 * Represents the attendance for a specific enrollment within a course.
 *
 * @param enrollment The enrollment for which the attendance is being recorded.
 * @throws IllegalArgumentException if the enrollment provided is null.
 * @return An instance of Attendance.
 */
package com.school_management.support_entities;

import com.school_management.core_entities.Enrollment;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Attendance {
    private Enrollment enrollment;
    private Map<ZonedDateTime, AttendanceStatus> attendanceList;

    /**
     * Constructs an Attendance object for a specific enrollment.
     *
     * @param enrollment The enrollment for which the attendance is being recorded.
     * @throws IllegalArgumentException if the enrollment provided is null.
     */
    public Attendance(Enrollment enrollment) {
        if(enrollment!=null) {
            this.enrollment = enrollment;
            initializeAttendanceList();
        }
        throw new IllegalArgumentException("enrollment cannot be null");
    }

    public Enrollment getEnrollment() {
        return this.enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    /**
     * Initializes the attendance list based on the schedule of the associated enrollment.
     */
    private void initializeAttendanceList() {
        attendanceList = new HashMap<>();
        List<ZonedDateTime> dates = enrollment.getCourseSection().getSchedule().getListOfDates();

        for(ZonedDateTime date:dates) {
            attendanceList.put(date, AttendanceStatus.NA);
        }
    }

    /**
     * Assigns an attendance status for a specific date.
     *
     * @param date             The date for which the attendance status needs to be assigned.
     * @param attendanceStatus The status to be assigned (e.g., present, absent).
     * @throws IllegalArgumentException if the date provided is not found in the attendance list.
     */
    public void assignStatus(ZonedDateTime date, AttendanceStatus attendanceStatus) {
        if(attendanceList.containsKey(date)) {
            attendanceList.put(date, attendanceStatus);
        }
        throw new IllegalArgumentException("Date not found in the list");
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
        return Objects.equals(enrollment, attendance.enrollment) && Objects.equals(attendanceList, attendance.attendanceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrollment, attendanceList);
    }

    @Override
    public String toString() {
        return "{" +
            " enrollment='" + getEnrollment() + "'" +
            " attendanceList='" + attendanceList + "'" +
            "}";
    }
    
    
}
