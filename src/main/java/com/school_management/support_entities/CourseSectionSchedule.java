package com.school_management.support_entities;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CourseSectionSchedule{
    private EnumMap<DayOfWeek, LocalTime> weeklySchedule;
    private Duration duration;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<LocalDate> dateList;
    
    public CourseSectionSchedule(Map<DayOfWeek, LocalTime> weeklySchedule, Duration duration, LocalDate startDate, LocalDate endDate) {
        super();
        if(weeklySchedule == null || duration==null || startDate ==null || endDate == null) {
            throw new IllegalArgumentException("paramenteres cannot be null!");   
        } else {
            this.weeklySchedule = new EnumMap<>(weeklySchedule);
            this.startDate = startDate;
            this.endDate = endDate;
            dateGenerator(); 
        }   
    }

    public Map<DayOfWeek,LocalTime> getWeeklySchedule() {
        return Collections.unmodifiableMap(weeklySchedule);
    }

    public void setWeeklySchedule(Map<DayOfWeek,LocalTime> weeklySchedule) {
        if(weeklySchedule == null) {
            throw new IllegalArgumentException("schedule cannot be null");
        }
        this.weeklySchedule = new EnumMap<>(weeklySchedule);
        dateGenerator();
    }

    public LocalDate getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if(startDate ==null) {
            throw new IllegalArgumentException("start date cannot be null");
        }
        this.startDate = startDate;
        dateGenerator();
    }

    public LocalDate getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if(endDate== null) {
            throw new IllegalArgumentException("end date cannot be null");
        }
        this.endDate = endDate;
        dateGenerator();
    }
  
    public void addDay(DayOfWeek dayOfWeek, LocalTime time) {
        if(dayOfWeek==null || time==null) {
            throw new IllegalArgumentException("paramenteres cannot be null");
        }
        weeklySchedule.put(dayOfWeek, time);
        dateGenerator();
    }

    
    public void removeDay(DayOfWeek dayOfWeek, LocalTime time) {
        boolean removed = weeklySchedule.remove(dayOfWeek, time);
        if(!removed) {
            throw new IllegalArgumentException("Cannot find dayOfWeek and time in map");
        }
        dateGenerator();
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<LocalDate> getDateList() {
        return Collections.unmodifiableList(this.dateList);
    }

    public void setDateList(List<LocalDate> dateList) {
        if(dateList != null) {
            this.dateList = new ArrayList<>(dateList);
        }
        throw new IllegalArgumentException("dateList cannot be null");
    }

    public boolean hasConflict(CourseSectionSchedule schedule) {
        if(this.endDate.isBefore(schedule.getStartDate()) || schedule.getEndDate().isBefore(this.startDate)) {
            return false;
        }

        Map<DayOfWeek, LocalTime> ws1 = schedule.getWeeklySchedule();
        Map<DayOfWeek, LocalTime> ws2 = getWeeklySchedule();
        Duration d1 = schedule.getDuration();
        Duration d2 = getDuration();
    
        for (Map.Entry<DayOfWeek, LocalTime> entry : ws1.entrySet()) {
            DayOfWeek key = entry.getKey();
            LocalTime value = entry.getValue();
    
            if (ws2.containsKey(key) && hasConflict(value, d1, ws2.get(key), d2)) {
                return true;
            }
        }
    
        return false; // No conflict found
    }

    public boolean hasConflict(LocalTime startTime1, Duration d1, LocalTime startTime2, Duration d2) {
        return (!startTime1.plus(d1).isBefore(startTime2) && !startTime2.plus(d2).isBefore(startTime1));
    }

    private void dateGenerator() {
        dateList = new ArrayList<>();
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if(weeklySchedule.containsKey(date.getDayOfWeek())) {
                dateList.add(date);
            }
            date = date.plusDays(1); // Move to the next day
        }
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CourseSectionSchedule)) {
            return false;
        }
        CourseSectionSchedule courseSectionSchedule = (CourseSectionSchedule) o;
        return Objects.equals(weeklySchedule, courseSectionSchedule.weeklySchedule) && Objects.equals(duration, courseSectionSchedule.duration) && Objects.equals(startDate, courseSectionSchedule.startDate) && Objects.equals(endDate, courseSectionSchedule.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weeklySchedule, duration, startDate, endDate);
    }


    @Override
    public String toString() {
        return "{" +
            " weeklySchedule='" + getWeeklySchedule() + "'" +
            ", duration='" + getDuration() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
    
}

