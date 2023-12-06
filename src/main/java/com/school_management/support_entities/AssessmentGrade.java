package com.school_management.support_entities;

import java.util.Objects;

public class AssessmentGrade extends Grades{
    Assessment assessment;

    /**
     * Constructor for AssessmentGrade.
     *
     * @param assessment The assessment for the grade.
     * @param totalGrade The total grade for assessment.
     */
    public AssessmentGrade(Assessment assessment, int totalGrade) {
        super(totalGrade);
        this.assessment = assessment;
    }

    // Getters and setters for AssessmentGrade

    public Assessment getAssessment() {
        return this.assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    /**
     * Retrieves the weightage of the assessment.
     *
     * @return The weightage.
     */
    public float getWeightage() {
        return this.getAssessment().getWeightage();
    }

    /**
     * Calculates the final grade contribution.
     *
     * @return The final grade contribution.
     */
    public float finalGradeContibution() {
        return (super.getScoredGrade()/super.getTotalGrade())*getWeightage();
    }

    // Overridden equals, hashCode, and toString methods for AssessmentGrade

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AssessmentGrade)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        AssessmentGrade assessmentGrade = (AssessmentGrade) o;
        return Objects.equals(assessment, assessmentGrade.assessment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), assessment);
    }

    @Override
    public String toString() {
        return "{" +
            super.toString() +
            " assessment='" + getAssessment() + "'" +
            "}";
    }
    
}
