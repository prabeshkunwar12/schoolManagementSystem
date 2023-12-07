package com.school_management.support_entities;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class CourseSectionSchedule{
    private EnumMap<DayOfWeek, LocalTime> weeklySchedule;
    private Duration duration;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private List<ZonedDateTime> dateList;
    
    public CourseSectionSchedule(Map<DayOfWeek, LocalTime> weeklySchedule, Duration duration, ZonedDateTime startDate, ZonedDateTime endDate) {
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

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        if(startDate ==null) {
            throw new IllegalArgumentException("start date cannot be null");
        }
        this.startDate = startDate;
        dateGenerator();
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
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

    public List<ZonedDateTime> getDateList() {
        return Collections.unmodifiableList(this.dateList);
    }

    public void setDateList(List<ZonedDateTime> dateList) {
        if(dateList != null) {
            this.dateList = new ArrayList<>(dateList);
        }
        throw new IllegalArgumentException("dateList cannot be null");
    }

    private void dateGenerator() {
        dateList = new ArrayList<>();
        ZonedDateTime date = startDate;
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

