package com.school_management.support_entities;

import java.time.ZonedDateTime;
import java.util.Objects;

public class Assessment {
    private AssessmentType assessmentType;
    private String description;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int durationMinutes;
    private float weightage;

    /**
     * Constructs an Assessment object.
     *
     * @param assessmentType  The type of assessment.
     * @param description     The description of the assessment.
     * @param startDate       The start date of the assessment.
     * @param endDate         The end date of the assessment.
     * @param durationMinutes The duration of the assessment in minutes.
     * @param weightage       The weightage of the assessment.
     */
    public Assessment(AssessmentType assessmentType, String description, ZonedDateTime startDate, ZonedDateTime endDate, int durationMinutes, float weightage) {
        this.assessmentType = assessmentType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = durationMinutes;
        this.weightage = weightage;
    }

    // Getters and setters for Assessment


    public AssessmentType getAssessmentType() {
        return this.assessmentType;
    }

    public void setAssessmentType(AssessmentType assessmentType) {
        this.assessmentType = assessmentType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public float getWeightage() {
        return this.weightage;
    }

    public void setWeightage(float weightage) {
        this.weightage = weightage;
    }

    @Override
    public String toString() {
        return "{" +
            " assessmentType='" + getAssessmentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", durationMinutes='" + getDurationMinutes() + "'" +
            ", weightage='" + getWeightage() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Assessment)) {
            return false;
        }
        Assessment assessment = (Assessment) o;
        return Objects.equals(assessmentType, assessment.assessmentType) && Objects.equals(description, assessment.description) && Objects.equals(startDate, assessment.startDate) && Objects.equals(endDate, assessment.endDate) && durationMinutes == assessment.durationMinutes && weightage == assessment.weightage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assessmentType, description, startDate, endDate, durationMinutes, weightage);
    }


}
