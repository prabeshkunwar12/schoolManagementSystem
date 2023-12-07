package com.school_management.support_entities;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Schedule {
    private EnumMap<DayOfWeek, LocalTime> weeklySchedule;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private List<ZonedDateTime> dateList;

    /**
     * Constructor to initialize with a provided weekly schedule and date range.
     *
     * @param weeklySchedule A map containing days of the week and their corresponding times.
     * @param startDate      The start date of the schedule.
     * @param endDate        The end date of the schedule.
     * @throws IllegalArgumentException if parameters are null.
     */
    public Schedule(Map<DayOfWeek, LocalTime> weeklySchedule, ZonedDateTime startDate, ZonedDateTime endDate) {
        if(weeklySchedule == null || startDate ==null || endDate== null) {
            throw new IllegalArgumentException("paramenteres cannot be null!");
        }
        this.weeklySchedule = new EnumMap<>(weeklySchedule);
        this.startDate = startDate;
        this.endDate = endDate;
        dateList = new ArrayList<>();
        dateGenerator();
    }

    /**
     * Constructor to initialize with an empty weekly schedule and date range.
     *
     * @param startDate The start date of the schedule.
     * @param endDate   The end date of the schedule.
     * @throws IllegalArgumentException if parameters are null.
     */
    public Schedule(ZonedDateTime startDate, ZonedDateTime endDate) {
        if(startDate ==null || endDate== null) {
            throw new IllegalArgumentException("paramenteres cannot be null.");
        }
        this.weeklySchedule = new EnumMap<>(DayOfWeek.class);
        this.startDate = startDate;
        this.endDate = endDate;
        dateList = new ArrayList<>();
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

    /**
     * Adds a day with the specified day of the week and time to the schedule.
     *
     * @param dayOfWeek The day of the week to add.
     * @param time      The time for the specified day.
     * @throws IllegalArgumentException if parameters are null.
     */
    public void addDay(DayOfWeek dayOfWeek, LocalTime time) {
        if(dayOfWeek==null || time==null) {
            throw new IllegalArgumentException("paramenteres cannot be null");
        }
        weeklySchedule.put(dayOfWeek, time);
        dateGenerator();
    }

    /**
     * Removes a day with the specified day of the week and time from the schedule.
     *
     * @param dayOfWeek The day of the week to remove.
     * @param time      The time for the specified day.
     * @throws IllegalArgumentException if the specified day and time are not found in the schedule.
     */
    public void removeDay(DayOfWeek dayOfWeek, LocalTime time) {
        boolean removed = weeklySchedule.remove(dayOfWeek, time);
        if(!removed) {
            throw new IllegalArgumentException("Cannot find dayOfWeek and time in map");
        }
        dateGenerator();
    }

    /**
     * Generates a list of dates based on the schedule within the specified date range.
     * Dates are included if they match the days of the week defined in the schedule.
     */
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
        if (!(o instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(weeklySchedule, schedule.weeklySchedule) && Objects.equals(startDate, schedule.startDate) && Objects.equals(endDate, schedule.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weeklySchedule, startDate, endDate);
    }

    @Override
    public String toString() {
        return "{" +
            " weeklySchedule='" + getWeeklySchedule() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }
    
}
