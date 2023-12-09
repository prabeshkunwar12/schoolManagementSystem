package com.school_management.support_entities.grade;

public class FinalCourseGrade extends Grades {
    /**
     * Constructor for FinalCourseGrade.
     *
     * @param scoredGrade The scored grade for the final course grade.
     */
    public FinalCourseGrade() {
        super(100); // Calls the constructor of Grades with a fixed total grade of 100
    }  
    
    /**
     * Overrides the method to set the total grade.
     * Throws an exception as the total grade of a final course grade cannot be changed.
     *
     * @param totalGrade The total grade value.
     * @throws IllegalArgumentException Throws an exception when trying to change the total grade.
     */
    @Override
    public void setTotalGrade(float totalGrade) {
        throw new IllegalArgumentException("Cannot change the total grade of final course grade.");
    }
}
