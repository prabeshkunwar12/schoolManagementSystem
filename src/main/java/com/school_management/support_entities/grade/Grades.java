package com.school_management.support_entities.grade;

import java.util.Objects;

public abstract class Grades {
    private float scoredGrade;
    private float totalGrade;
    private float passingGrade;

    /**
     * Constructor for Grades.
     *
     * @param totalGrade The total grade for the assessment.
     * @throws IllegalArgumentException if the totalGrade is less than 0.
     */
    protected Grades(float totalGrade) {
        if (totalGrade < 0) {
            throw new IllegalArgumentException("total grade should be greater than 0");
        }
        this.totalGrade = totalGrade;
        this.scoredGrade = 0;
        this.passingGrade = 0;
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
            throw new IllegalArgumentException("scored grade should be in between 0 and total grade (" + getTotalGrade() + ")");
        } 
        this.scoredGrade = scoredGrade;
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
            throw new IllegalArgumentException("total grade should be greater than 0");
        }
        this.totalGrade = totalGrade;
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
            throw new IllegalArgumentException("passing grade should be in between 0 and total grade (" + getTotalGrade() + ")");
        } 
        this.passingGrade = passingGrade;
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
