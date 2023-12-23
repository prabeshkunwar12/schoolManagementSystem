/**
 * The DayOfAWeek entity represents the schedule for a course section on a weekly basis.
 * It defines the day of the week and start time for a particular course section's schedule.
 * 
 * Functionalities:
 * - Manages and maintains scheduling details for a specific course section on a weekly basis.
 * - Provides accessors and mutators to retrieve and modify schedule attributes.
 * - Establishes a relationship with the CourseSectionSchedule entity to associate weekly schedules.
 * 
 * Usage:
 * This class is utilized to define and manage the weekly schedule for a course section, allowing
 * access to day-of-the-week details and start times for the associated course sections.
 * 
 * Attributes:
 * - schedule: Represents the associated CourseSectionSchedule to which the weekly schedule belongs.
 * - dayOfWeek: Represents the day of the week for the schedule.
 * - startTime: Represents the start time for the schedule on the specified day.
 * 
 * Note: This class is connected to CourseSectionSchedule to maintain a list of weekly schedules
 * for a specific course section.
 * 
 * Functions:
 * - getSchedule(): Retrieves the associated CourseSectionSchedule.
 * - setSchedule(schedule: CourseSectionSchedule): Sets or updates the associated CourseSectionSchedule.
 * - getDayOfWeek(): Retrieves the day of the week for the schedule.
 * - setDayOfWeek(dayOfWeek: DayOfWeek): Sets or updates the day of the week for the schedule.
 * - getStartTime(): Retrieves the start time for the schedule.
 * - setStartTime(startTime: LocalTime): Sets or updates the start time for the schedule.
 * 
 * See Also:
 * - CourseSectionSchedule: Contains a list of DayOFAWeekSchedule instances representing weekly schedules
 *   for a particular course section.
 */

package com.school_management.support_entities.schedule;

import java.time.DayOfWeek;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "Weekly_schedule_css")
public class DayOfAWeekSchedule {
    @ManyToOne
    @JoinColumn(name = "course_section_schedule_id")
    private CourseSectionSchedule schedule;

    @Column(name = "day_of_week")
    private DayOfWeek dayOfWeek;

    @Column(name = "start_time") 
    private LocalTime startTime;

    private Logger logger = LoggerFactory.getLogger(DayOfAWeekSchedule.class);

    // default constructor for JPA compliance
    public DayOfAWeekSchedule() {
    }

    public DayOfAWeekSchedule(CourseSectionSchedule schedule, DayOfWeek dayOfWeek, LocalTime startTime) {
        this.schedule = schedule;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
    }

    public CourseSectionSchedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(CourseSectionSchedule schedule) {
        if(schedule == null) {
            logger.error("schedule is null", new IllegalArgumentException());
            throw new IllegalArgumentException("schedule cannot be null");
        }
        this.schedule = schedule;
        logger.info("schedule been modified for {}", getDayOfWeek());
    }

    public DayOfWeek getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        if(dayOfWeek == null) {
            logger.error("dayOfWeek is null", new IllegalArgumentException());
            throw new IllegalArgumentException("dayOfWeek cannot be null");
        }
        this.dayOfWeek = dayOfWeek;
        logger.info("dayOfWeek for {} has been modified to {} at {}", getSchedule().getCourseSectionScheduleID(), getDayOfWeek(), getStartTime());
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        if(startTime == null) {
            logger.error("startTime is null", new IllegalArgumentException());
            throw new IllegalArgumentException("startTime cannot be null");
        }
        this.startTime = startTime;
        logger.info("startTime for {} on {} has been modified to {}", getSchedule().getCourseSectionScheduleID(), getDayOfWeek(), getStartTime());
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof DayOfAWeekSchedule)) {
            return false;
        }
        DayOfAWeekSchedule weeklySchedule = (DayOfAWeekSchedule) o;
        return Objects.equals(schedule, weeklySchedule.schedule) && Objects.equals(dayOfWeek, weeklySchedule.dayOfWeek) && Objects.equals(startTime, weeklySchedule.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(schedule, dayOfWeek, startTime);
    }

    @Override
    public String toString() {
        return "{" +
            " schedule='" + getSchedule() + "'" +
            ", dayOfWeek='" + getDayOfWeek() + "'" +
            ", startTime='" + getStartTime() + "'" +
            "}";
    }
    
}
