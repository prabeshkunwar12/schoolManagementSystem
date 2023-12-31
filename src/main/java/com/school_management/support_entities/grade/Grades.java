/**
 * The Grades class represents the evaluation metrics and standards for assessing student performance.
 * It serves as an abstract blueprint for various grading systems within the educational domain,
 * encapsulating attributes such as scored grade, total grade, and passing grade.
 * 
 * Functionalities:
 * - Constructors to initialize the grades with the total grade.
 * - Getters and setters to manage scored grade, total grade, and passing grade attributes,
 *   ensuring proper validation checks for their values.
 * - Abstract methods for specific grading systems or subclasses to implement their unique evaluation rules.
 * 
 * Usage:
 * This abstract class is extended by various concrete grading system classes, each representing a specific
 * evaluation method or system such as letter grades, percentage-based grades, etc. It provides a foundation
 * for defining the behavior of different grading systems while enforcing validation of grade values.
 * 
 * Attributes:
 * - scoredGrade: Represents the actual grade or score achieved by a student.
 * - totalGrade: Represents the maximum achievable grade or total score for an assessment.
 * - passingGrade: Represents the minimum grade required to pass an assessment.
 * 
 * Note: Subclasses or specific grading system implementations should adhere to the constraints
 * and rules defined within this abstract class for consistent and reliable evaluation metrics.
 */
package com.school_management.support_entities.grade;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Grades")
public abstract class Grades {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grades_id")
    private int gradesID;
    
    @Column(name = "scored_grade")
    private float scoredGrade;
    
    @Column(name = "total_grade")
    private float totalGrade;
    
    @Column(name = "passing_grade")
    private float passingGrade;

    private Logger logger =  LoggerFactory.getLogger(Grades.class);

    // default construtor for JPA compliance
    protected Grades() {}

    /**
     * Constructor for Grades.
     *
     * @param totalGrade The total grade for the assessment.
     * @throws IllegalArgumentException if the totalGrade is less than 0.
     */
    protected Grades(float totalGrade) {
        if (totalGrade < 0) {
            logger.error("total grade is less than zero", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.totalGrade = totalGrade;
        this.scoredGrade = 0;
        this.passingGrade = 0;
        logger.info("new Grades initialized.");
    }

    public int getGradesID() {
        return this.gradesID;
    }

    // Getters and setters for scoredGrade and totalGrade

    public float getScoredGrade() {
        return this.scoredGrade;
    }

    /**
     * Sets the scored grade.
     *
     * @param scoredGrade The scored grade to set.
     * @throws IllegalArgumentException if scoredGrade is less than 0 or exceeds totalGrade.
     */
    public void setScoredGrade(float scoredGrade) {
        if(scoredGrade > getTotalGrade() && scoredGrade < 0) {
            logger.error("scored grade should be in between 0 and {}", getTotalGrade(), new IllegalArgumentException());
            throw new IllegalArgumentException("scored grade should be in between 0 and {}");
        } 
        this.scoredGrade = scoredGrade;
        logger.info("Scored grade for {} changed to {}", getGradesID(), this.scoredGrade);
    }

    public float getTotalGrade() {
        return this.totalGrade;
    }

    /**
     * Sets the total grade.
     *
     * @param totalGrade The total grade to set.
     * @throws IllegalArgumentException if totalGrade is less than 0.
     */
    public void setTotalGrade(float totalGrade) {
        if (totalGrade<0) {
            logger.error("total grade should be greater than 0", new IllegalArgumentException());
            throw new IllegalArgumentException("Total grade must ne greater than 0");
        }
        this.totalGrade = totalGrade;
        logger.info("Total grade changed to {}", this.totalGrade);
    }

    public float getPassingGrade() {
        return this.passingGrade;
    }

    /**
     * Sets the passing grade.
     *
     * @param passingGrade The passing grade to set.
     * @throws IllegalArgumentException if passingGrade is less than 0 or exceeds totalGrade.
     */
    public void setPassingGrade(float passingGrade) {
        if(passingGrade>getTotalGrade() && passingGrade<0) {
            logger.error("passing grade should be in between 0 and {} ", getTotalGrade(), new IllegalArgumentException());
            throw new IllegalArgumentException("passing grade should be in between 0 and " + getTotalGrade());
        } 
        this.passingGrade = passingGrade;
        logger.info("Passing grade changed to {}", this.passingGrade);
    }

    // Overridden equals, hashCode, and toString methods for Grades


    @Override
    public String toString() {
        return "{" +
            " scoredGrade='" + getScoredGrade() + "'" +
            ", totalGrade='" + getTotalGrade() + "'" +
            ", passingGrade='" + getPassingGrade() + "'" +
            "}";
    }  
    

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Grades)) {
            return false;
        }
        Grades grades = (Grades) o;
        return scoredGrade == grades.scoredGrade && totalGrade == grades.totalGrade && passingGrade == grades.passingGrade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoredGrade, totalGrade, passingGrade);
    }
    
}
