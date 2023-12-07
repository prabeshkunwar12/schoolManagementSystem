package com.school_management.support_entities;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Attendance {
    private CourseSectionSchedule schedule;
    private Map<LocalDate, AttendanceStatus> attendanceList;

    
    public Attendance(CourseSectionSchedule schedule) {
        if(schedule == null) {
            throw new IllegalArgumentException("enrollment cannot be null");
        }
        this.schedule = schedule;
        initializeAttendanceList();
    }

    public CourseSectionSchedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(CourseSectionSchedule schedule) {
        if(schedule == null) {
            throw new IllegalArgumentException("enrollment cannot be null");
        }
        this.schedule = schedule;
        initializeAttendanceList();
    }

    public Map<LocalDate, AttendanceStatus> getAttendanceList() {
        return Collections.unmodifiableMap(attendanceList);
    }

    private void initializeAttendanceList() {
        attendanceList = new HashMap<>();
        List<LocalDate> dates = getSchedule().getDateList();

        for(LocalDate date:dates) {
            attendanceList.put(date, AttendanceStatus.NA);
        }
    }

    
    public void assignStatus(LocalDate date, AttendanceStatus attendanceStatus) {
        if(attendanceList.computeIfPresent(date, (key, oldValue)->attendanceStatus) == null) {
            throw new IllegalArgumentException("Date not found in the list");
        }
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
        return Objects.equals(schedule, attendance.schedule) && Objects.equals(attendanceList, attendance.attendanceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule, attendanceList);
    }

    @Override
    public String toString() {
        return "{" +
            " schedule='" + getSchedule() + "'" +
            ", attendanceList='" + getAttendanceList() + "'" +
            "}";
    }
}