/**
 * The AssessmentGrade class represents the graded evaluation of a specific assessment
 * within a grading system, detailing the assessment, its associated weightage, and final
 * grade contribution towards the overall score.
 * 
 * Functionalities:
 * - Inherits attributes and methods from the Grades abstract class for managing scored grade,
 *   total grade, and passing grade, and incorporates additional attributes for assessment details.
 * - Provides functionalities to calculate the final grade contribution based on the assessment's
 *   scored grade, total grade, and assigned weightage.
 * - Validates the assessment's passing criteria based on the assessment type and scored grade.
 * 
 * Usage:
 * This class is used to define the grading information and evaluation metrics specifically for
 * an individual assessment within a grading system. It computes the weightage and final grade
 * contribution for the assessment, adhering to the criteria set by the grading system.
 * 
 * Attributes:
 * - assessment: Represents the assessment entity associated with the grade.
 * - weightage: Represents the proportional weight assigned to the assessment within the grading system.
 * 
 * Note: The class extends the Grades abstract class, leveraging its grading attributes and functionalities,
 * while introducing assessment-specific attributes to calculate and manage the assessment's grade.
 */
package com.school_management.support_entities.grade;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.assessment.Assessment;
import com.school_management.support_entities.assessment.AssessmentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Assessment_grade")
public class AssessmentGrade extends Grades {
    
    @ManyToOne
    @JoinColumn(name = "assessment_id")
    private Assessment assessment;

    @Column(name = "weightage")
    private float weightage ;

    private Logger logger = LoggerFactory.getLogger(AssessmentGrade.class);

    //default constructor for JPA compliance
    public AssessmentGrade() {super();}

    /**
     * Constructor for AssessmentGrade.
     *
     * @param assessment The assessment for the grade.
     * @param totalGrade The total grade for assessment.
     */
    public AssessmentGrade(Assessment assessment, int totalGrade, float weightage) {
        super(totalGrade);
        this.assessment = assessment;
        setWeightage(weightage);
        logger.info("New {} Assessment grade  created", assessment.getAssessmentType());
    }

    // Getters and setters for AssessmentGrade

    public Assessment getAssessment() {
        return this.assessment;
    }

    public void setAssessment(Assessment assessment) {
        if(assessment == null) {
            logger.error("assessment is null", new IllegalArgumentException());
            throw new IllegalArgumentException("assessment cannot be null");
        }
        this.assessment = assessment;
        logger.info("assessment for {} has been modified to {}", super.getGradesID(), getAssessment().getAssessmentID());
    }

    /**
     * Retrieves the weightage of the assessment.
     *
     * @return The weightage.
     */
    public float getWeightage() {
        return this.weightage;
    }

    public void setWeightage(float weightage) {
        if(weightage<0 && weightage>100) {
            logger.error("Weightage should be between 0 and 100", new IllegalArgumentException());
            throw new IllegalArgumentException("Weightage should be between 0 and 100");
        } 
        this.weightage = weightage;
        logger.info("Weightage modified to {} for the assessment {}", getWeightage(), getAssessment().getAssessmentID());
    }

    /**
     * Calculates the final grade contribution.
     *
     * @return The final grade contribution.
     */
    public float getFinalGradeContibution() {
        return (super.getScoredGrade() / super.getTotalGrade()) * getWeightage();
    }

    /**
     * Checks if the assessment grade meets the passing criteria.
     *
     * @return True if passed, false otherwise.
     */
    public boolean isPassed() {
        return !(getAssessment().getAssessmentType() == AssessmentType.MANDATORY_PASS && super.getScoredGrade()<super.getPassingGrade());
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
