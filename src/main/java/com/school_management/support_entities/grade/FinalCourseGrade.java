/**
 * The FinalCourseGrade class represents the final grade for a course, inheriting from the Grades
 * abstract class with a fixed total grade of 100, reflecting the cumulative evaluation of the entire course.
 * 
 * Functionalities:
 * - Inherits attributes and methods from the Grades abstract class, focusing on the final course grade,
 *   which has a pre-defined total grade of 100.
 * - Overrides the setTotalGrade method to prevent changes to the total grade, as it's fixed at 100 for
 *   a final course grade.
 * 
 * Usage:
 * This class serves to define and manage the final grade for a course, specifically maintaining a fixed
 * total grade of 100. It's employed to represent the overall assessment of a course's performance.
 * 
 * Note: The class extends the Grades abstract class but limits the alteration of the total grade to 100,
 * emphasizing the finality and consistency of the course's total evaluation.
 */
package com.school_management.support_entities.grade;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinalCourseGrade extends Grades {
    private Logger logger = LoggerFactory.getLogger(FinalCourseGrade.class);
    /**
     * Constructor for FinalCourseGrade.
     *
     * @param scoredGrade The scored grade for the final course grade.
     */
    public FinalCourseGrade() {
        super(100); // Calls the constructor of Grades with a fixed total grade of 100
        logger.info("Final course grade created");
    }  
    
    /**
     * Overrides the method to set the total grade.
     * Throws an exception as the total grade of a final course grade cannot be changed.
     *
     * @param totalGrade The total grade value.
     * @throws IllegalArgumentException Throws an exception when trying to change the total grade.
     */
    @Override
    public boolean setTotalGrade(float totalGrade) {
        logger.error("The total grade of final course grade is always 100.", new IllegalArgumentException());
        return false;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override 
    public String toString() {
        return super.toString();
    }
}
