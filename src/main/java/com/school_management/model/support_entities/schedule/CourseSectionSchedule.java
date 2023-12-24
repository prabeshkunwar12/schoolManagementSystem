/**
 * Represents the schedule for a course section, including weekly schedules, start and end dates, and durations.
 * Allows management and retrieval of information related to the schedule, such as setting the weekly schedule,
 * adding or removing specific days, checking for schedule conflicts, and generating a list of dates within the schedule period.
 *
 * Usage:
 * CourseSectionSchedule schedule = new CourseSectionSchedule(weeklySchedule, duration, startDate, endDate);
 * schedule.setWeeklySchedule(newWeeklySchedule); // Set a new weekly schedule
 * schedule.addDay(DayOfWeek.MONDAY, LocalTime.of(9, 0)); // Add a specific day to the schedule
 *
 * @see DayOfWeek
 */
package com.school_management.model.support_entities.schedule;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course_section_schedule")
public class CourseSectionSchedule{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_section_schedule_id")
    private int courseSectionScheduleID;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_section_schedule_id")
    private List<DayOfAWeekSchedule> weeklySchedule;
    
    @Column(name = "duration")
    private Duration duration;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(name = "end_date")
    private LocalDate endDate;
    
    private List<LocalDate> dateList;

    private Logger logger = LoggerFactory.getLogger(CourseSectionSchedule.class);
    
    public CourseSectionSchedule(List<DayOfAWeekSchedule> weeklySchedule, Duration duration, LocalDate startDate, LocalDate endDate) {
        if(weeklySchedule == null || duration==null || startDate ==null || endDate == null) {
            logger.error("paramenteres cannot be null!", new IllegalArgumentException());  
            throw new IllegalArgumentException(); 
        } 
        if(startDate.isAfter(endDate)) {
            logger.error("startDate must be before endDate", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.weeklySchedule = new ArrayList<>(weeklySchedule);
        this.startDate = startDate;
        this.endDate = endDate;
        dateGenerator(); 
        logger.info("CourseSectionSchedule initialized.");  
    }

    public int getCourseSectionScheduleID() {
        return this.courseSectionScheduleID;
    }
    /**
     * Retrieves an unmodifiable view of the weekly schedule for this course section.
     *
     * @return An unmodifiable map containing the weekly schedule.
     */
    public List<DayOfAWeekSchedule> getWeeklySchedule() {
        return Collections.unmodifiableList(weeklySchedule);
    }

    /**
     * Sets the weekly schedule for this course section.
     *
     * @param weeklySchedule The new weekly schedule to be set (must not be null).
     * Sets the weekly schedule and generates the date list accordingly.
     * @throws IllegalArgumentException If the provided schedule is null.
     */
    public void setWeeklySchedule(List<DayOfAWeekSchedule> weeklySchedule) {
        if(weeklySchedule == null) {
            logger.error("Schedule cannot be null!", new IllegalArgumentException());
            throw new IllegalArgumentException("Schedule cannot be null");
        }
        this.weeklySchedule = new ArrayList<>(weeklySchedule);
        logger.info("new Weekly schedule set.");
        dateGenerator();
    }

    /**
     * Retrieves the start date of this course section's schedule.
     *
     * @return The start date of the schedule.
     */
    public LocalDate getStartDate() {
        return this.startDate;
    }

    /**
     * Sets the start date of this course section's schedule.
     *
     * @param startDate The new start date to be set (must not be null).
     * @throws IllegalArgumentException If the provided start date is null.
     */
    public void setStartDate(LocalDate startDate) {
        if(startDate ==null) {
            logger.error("start date cannot be null", new IllegalArgumentException());
        }
        this.startDate = startDate;
        dateGenerator();
        logger.info("new StartDate set.");
    }

    /**
     * Retrieves the end date of this course section's schedule.
     *
     * @return The end date of the schedule.
     */
    public LocalDate getEndDate() {
        return this.endDate;
    }

    /**
     * Sets the end date of this course section's schedule.
     *
     * @param endDate The new end date to be set (must not be null).
     * @throws IllegalArgumentException If the provided end date is null.
     */
    public void setEndDate(LocalDate endDate) {
        if(endDate== null) {
            logger.error("endDate cannot be null", new IllegalArgumentException());
        }
        this.endDate = endDate;
        dateGenerator();
        logger.info("new endDate set.");
    }
  
    /**
     * Adds a day to the weekly schedule for this course section.
     *
     * @param dayOfWeek The day of the week to be added (must not be null).
     * @param time      The time for the specified day (must not be null).
     * @throws IllegalArgumentException If either dayOfWeek or time is null.
     */
    public boolean addDay(DayOfWeek dayOfWeek, LocalTime time) {
        if(dayOfWeek==null || time==null) {
            logger.error("paramenteres cannot be null!", new IllegalArgumentException());
            return false;
        }
        for(DayOfAWeekSchedule s: weeklySchedule) {
            if(dayOfWeek.equals(s.getDayOfWeek())) {
                s.setStartTime(time);
                logger.info("Start time for {} changed to {}", dayOfWeek, time);
                return true;
            }
        }
        DayOfAWeekSchedule s = new DayOfAWeekSchedule(this, dayOfWeek, time);
        weeklySchedule.add(s);
        dateGenerator();
        logger.info("Day of the week and its respective time added.");
        return true;
    }

    /**
     * Removes a day from the weekly schedule for this course section.
     *
     * @param dayOfWeek The day of the week to be removed (must not be null).
     * @throws IllegalArgumentException If the specified day and time do not exist in the schedule.
     */
    public boolean removeDay(DayOfWeek dayOfWeek) {
        for(DayOfAWeekSchedule s: weeklySchedule) {
            if(s.getDayOfWeek().equals(dayOfWeek)) {
                weeklySchedule.remove(s);
                dateGenerator();
                logger.info("{} removed form the schedule", dayOfWeek);
                return true;
            }
        }
        logger.error("{} not found on the list", dayOfWeek, new IllegalArgumentException());
        return false;
    }

    /**
     * Retrieves the duration of this course section's schedule.
     *
     * @return The duration of the schedule.
     */
    public Duration getDuration() {
        return duration;
    }

    /**
     * Sets the duration of this course section's schedule.
     *
     * @param duration The new duration to be set.
     */
    public void setDuration(Duration duration) {
        if(duration == null) {
            logger.error("Duration cannot be null", new IllegalArgumentException());
        }
        this.duration = duration;
        logger.info("New duration set for the schedule");
    }

    /**
     * Retrieves an unmodifiable list of dates within this course section's schedule period.
     *
     * @return An unmodifiable list containing the dates within the schedule period.
     */
    public List<LocalDate> getDateList() {
        return Collections.unmodifiableList(this.dateList);
    }

    /**
     * Checks for schedule conflicts between two course section schedules.
     *
     * @param schedule The schedule to check for conflicts with this course section's schedule (must not be null).
     * @return True if there is a conflict; otherwise, false.
     */
    public boolean hasConflict(CourseSectionSchedule schedule) {
        if(this.endDate.isBefore(schedule.getStartDate()) || schedule.getEndDate().isBefore(this.startDate)) {
            return false;
        }

        List<DayOfAWeekSchedule> ws1 = schedule.getWeeklySchedule();
        List<DayOfAWeekSchedule> ws2 = getWeeklySchedule();
        Duration d1 = schedule.getDuration();
        Duration d2 = getDuration();
    
        for(DayOfAWeekSchedule dayOfAWeekSchedule1: ws1) {
            for(DayOfAWeekSchedule dayOfAWeekSchedule2: ws2) {
                if(dayOfAWeekSchedule1.getDayOfWeek().equals(dayOfAWeekSchedule2.getDayOfWeek()) && hasConflict(dayOfAWeekSchedule1.getStartTime(), d1, dayOfAWeekSchedule2.getStartTime(), d2)) {
                    return true;
                }
            }
        } 
    
        return false; // No conflict found
    }

    /**
     * Checks for schedule conflicts between two sets of start times and durations.
     *
     * @param startTime1 The start time of the first schedule.
     * @param d1         The duration of the first schedule.
     * @param startTime2 The start time of the second schedule.
     * @param d2         The duration of the second schedule.
     * @return True if there is a conflict; otherwise, false.
     */
    private boolean hasConflict(LocalTime startTime1, Duration d1, LocalTime startTime2, Duration d2) {
        return (!startTime1.plus(d1).isBefore(startTime2) && !startTime2.plus(d2).isBefore(startTime1));
    }

    private void dateGenerator() {
        dateList = new ArrayList<>();
        LocalDate date = startDate;

        while (!date.isAfter(endDate)) {
            final LocalDate currenDate = date;
            if(weeklySchedule.stream().anyMatch(daySchedule -> daySchedule.getDayOfWeek().equals(currenDate.getDayOfWeek()))) {
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

