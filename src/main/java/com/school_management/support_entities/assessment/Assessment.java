/**
 * The Assessment class represents an assessment or evaluation activity within the educational system.
 * It encapsulates details such as assessment type, description, schedule, duration, and weightage.
 * Assessments are used to evaluate a student's understanding and performance in a course or subject.
 * 
 * Usage:
 * The Assessment class is used to create assessment instances with various parameters like type,
 * description, start and end dates, duration in minutes, and weightage. These instances are utilized
 * in educational systems to track, manage, and evaluate student performance.
 * 
 * Functionalities:
 * - Construct an Assessment object with essential details: type, description, schedule, duration, and weightage.
 * - Getters and setters for all attributes to retrieve and modify assessment details.
 * - Override methods like toString(), equals(), and hashCode() for proper representation and comparison
 *   of Assessment objects.
 * 
 * This class employs ZonedDateTime for handling time-related attributes, ensuring the flexibility of time zones
 * and accurate time representations for assessments conducted across various regions.
 * 
 * Note: It's important to ensure the consistency and accuracy of assessment details, especially start and end dates,
 * to effectively manage evaluations and student performance tracking.
 */
package com.school_management.support_entities.assessment;

import java.time.ZonedDateTime;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Assessment {
    private int assessmentID;
    private final AssessmentType assessmentType;
    private String description;
    private ZonedDateTime startDate;
    private ZonedDateTime endDate;
    private int durationMinutes;

    private Logger logger = LoggerFactory.getLogger(Assessment.class);

    /**
     * Constructs an Assessment object.
     *
     * @param assessmentType  The type of assessment.
     * @param description     The description of the assessment.
     * @param startDate       The start date of the assessment.
     * @param endDate         The end date of the assessment.
     * @param durationMinutes The duration of the assessment in minutes.
     */
    public Assessment(AssessmentType assessmentType, String description, ZonedDateTime startDate, ZonedDateTime endDate, int durationMinutes) {
        this.assessmentType = assessmentType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.durationMinutes = durationMinutes;
        logger.info("New Assessment initialized");
    }

    // Getters and setters for Assessment

    public int getAssessmentID() {
        return this.assessmentID;
    }

    public AssessmentType getAssessmentType() {
        return this.assessmentType;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
        logger.info("Description modified for the assessment");
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
        logger.info("StartDate modified for the assessment");
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
        logger.info("EndDate modified for the assessment");
    }

    public int getDurationMinutes() {
        return this.durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
        logger.info("Duration modified for the assessment");
    }


    @Override
    public String toString() {
        return "{" +
            " assessmentType='" + getAssessmentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", durationMinutes='" + getDurationMinutes() + "'" +
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
        return Objects.equals(assessmentType, assessment.assessmentType) && Objects.equals(description, assessment.description) && Objects.equals(startDate, assessment.startDate) && Objects.equals(endDate, assessment.endDate) && durationMinutes == assessment.durationMinutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(assessmentType, description, startDate, endDate, durationMinutes);
    }


}
