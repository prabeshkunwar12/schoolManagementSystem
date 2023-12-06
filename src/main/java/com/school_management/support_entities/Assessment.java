package com.school_management.support_entities;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assessment {
    private AssessmentType assessmentType;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int durationMinutes;
    private float weightage;


    public Assessment() {
    }

    public Assessment(AssessmentType assessmentType, String description, LocalDateTime startDate, LocalDateTime endDate, int durationMinutes, float weightage) {
        this.assessmentType = assessmentType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = durationMinutes;
        this.weightage = weightage;
    }

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

    public LocalDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
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
    
}
