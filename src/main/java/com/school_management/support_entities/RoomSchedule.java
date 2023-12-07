package com.school_management.support_entities;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RoomSchedule {
    private List<CourseSectionSchedule> weeklySchedules;

    
    public RoomSchedule() {
        this.weeklySchedules = new ArrayList<>();
    }

    public List<CourseSectionSchedule> getWeeklySchedule() {
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
}
