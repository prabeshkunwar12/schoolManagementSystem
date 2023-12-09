package com.school_management.support_entities.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StudentSchedule {
    private List<CourseSectionSchedule> weeklySchedules;

    
    public StudentSchedule() {
        this.weeklySchedules = new ArrayList<>();
    }

    public List<CourseSectionSchedule> getWeeklySchedules() {
        return Collections.unmodifiableList(weeklySchedules);
    }

    public void addCourseSectionSchedule(CourseSectionSchedule schedule) {
        for(CourseSectionSchedule cs: weeklySchedules) {
            if(cs.hasConflict(schedule)) {
                throw new IllegalArgumentException("Schedule conflicts with " + cs.toString());
            } 
        }
        weeklySchedules.add(schedule);
    }

    public boolean removeCourseSectionSchedule(CourseSectionSchedule schedule) {
        return weeklySchedules.remove(schedule);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StudentSchedule)) {
            return false;
        }
        StudentSchedule studentSchedule = (StudentSchedule) o;
        return Objects.equals(weeklySchedules, studentSchedule.weeklySchedules);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weeklySchedules);
    }

    @Override
    public String toString() {
        return "{" +
            " weeklySchedules='" + getWeeklySchedules() + "'" +
            "}";
    }


}
