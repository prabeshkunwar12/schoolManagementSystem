/**
 * Represents a schedule containing CourseSectionSchedules.
 * This abstract class provides methods to manage the weekly schedules, such as retrieving, adding, and removing CourseSectionSchedules.
 * It contains an unmodifiable list of weekly schedules and methods to add and remove CourseSectionSchedules.
 * 
 * Usage:
 * - To retrieve the list of weekly schedules, use the 'getWeeklySchedules' method.
 * - To add a new CourseSectionSchedule, use the 'addCourseSectionSchedule' method, providing the schedule to be added.
 *    - This method throws an IllegalArgumentException if the provided schedule conflicts with an existing one.
 * - To remove a CourseSectionSchedule from the weekly schedules, use the 'removeCourseSectionSchedule' method, specifying the schedule to be removed.
 *    - This method throws an IllegalArgumentException if the provided schedule is not found in the list.
 * 
 * Note: This class is abstract and is intended to be extended to define specific types of schedules.
 * 
 * @see CourseSectionSchedule
 */
package com.school_management.support_entities.schedule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Schedule {
    private int scheduleID;
    private List<CourseSectionSchedule> weeklySchedules;
    // Logger for logging messages related to the Schedule class
    private static final Logger logger = LoggerFactory.getLogger(Schedule.class);

     /**
     * Constructs a Schedule object initializing the list of weekly schedules.
     */
    protected Schedule() {
        this.weeklySchedules = new ArrayList<>();
    }

    public int getScheduleID() {
        return scheduleID;
    }

    /**
     * Retrieves an unmodifiable list of the weekly schedules.
     *
     * @return An unmodifiable list of CourseSectionSchedules.
     */
    public List<CourseSectionSchedule> getWeeklySchedules() {
        return Collections.unmodifiableList(weeklySchedules);
    }

    /**
     * Adds a CourseSectionSchedule to the list of weekly schedules.
     *
     * @param schedule The CourseSectionSchedule to be added.
     * @return True if the CourseSectionSchedule is successfully added; otherwise, false.
     * @throws IllegalArgumentException If the schedule conflicts with an existing schedule in the list.
     */
    public boolean addCourseSectionSchedule(CourseSectionSchedule schedule) {
        for(CourseSectionSchedule cs: weeklySchedules) {
            if(cs.hasConflict(schedule)) {
                String message = "Schedule conflicts with " + cs.toString();
                logger.error(message, new IllegalArgumentException());
                return false;
            } 
        }
        weeklySchedules.add(schedule);
        logger.info("CourseSectionSchedule added to the list");
        return true;
    }

    /**
     * Removes a CourseSectionSchedule from the list of weekly schedules.
     *
     * @param schedule The CourseSectionSchedule to be removed.
     * @return True if the CourseSectionSchedule is successfully removed; otherwise, false.
     * @throws IllegalArgumentException If the CourseSectionSchedule is not found in the list.
     */
    public boolean removeCourseSectionSchedule(CourseSectionSchedule schedule) {
        boolean removed =  weeklySchedules.remove(schedule);
        if(removed) {
            logger.info("CourseSectionSchedule removed from the list");
        } else {
            logger.error("CourseSectionSchedule not found in the list", new IllegalArgumentException());
        }
        return removed;
    }

    // overridden methods: equals, hashCode, and toString

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Schedule)) {
            return false;
        }
        Schedule schedule = (Schedule) o;
        return Objects.equals(weeklySchedules, schedule.weeklySchedules);
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
