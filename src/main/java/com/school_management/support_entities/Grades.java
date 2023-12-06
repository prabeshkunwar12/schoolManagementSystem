package com.school_management.support_entities;

import java.util.Objects;

public abstract class Grades {
    private float scoredGrade = 0;
    private float totalGrade;

    // Constructor for Grades
    protected Grades(float totalGrade) {
        this.totalGrade = totalGrade;
    }

    // Getters and setters for scoredGrade and totalGrade

    /**
     * Retrieves the scored grade.
     *
     * @return The scored grade.
     */
    public float getScoredGrade() {
        return this.scoredGrade;
    }

    /**
     * Sets the scored grade.
     *
     * @param scoredGrade The scored grade to set.
     */
    public void setScoredGrade(float scoredGrade) {
        this.scoredGrade = scoredGrade;
    }

    /**
     * Retrieves the total grade.
     *
     * @return The total grade.
     */
    public float getTotalGrade() {
        return this.totalGrade;
    }

    /**
     * Sets the total grade.
     *
     * @param totalGrade The total grade to set.
     */
    public void setTotalGrade(float totalGrade) {
        this.totalGrade = totalGrade;
    }

    // Overridden equals, hashCode, and toString methods for Grades

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Grades)) {
            return false;
        }
        Grades grades = (Grades) o;
        return scoredGrade == grades.scoredGrade && totalGrade == grades.totalGrade;
    }

    @Override
    public int hashCode() {
        return Objects.hash(scoredGrade, totalGrade);
    }

    @Override
    public String toString() {
        return "{" +
            " scoredGrade='" + getScoredGrade() + "'" +
            ", totalGrade='" + getTotalGrade() + "'" +
            "}";
    }
}
