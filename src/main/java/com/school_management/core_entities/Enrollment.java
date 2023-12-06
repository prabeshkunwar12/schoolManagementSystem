package com.school_management.core_entities;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.AssessmentGrade;

public class Enrollment {
    Student student;
    CourseSection courseSection;
    List<AssessmentGrade> assessmentGrades;

    // Logger for logging messages related to the Enrollment class
    private static final Logger logger = LoggerFactory.getLogger(Enrollment.class);

    //Constructor for Enrollment
    public Enrollment(Student student, CourseSection courseSection) {
        this.student = student;
        this.courseSection = courseSection;
        assessmentGrades = new ArrayList<>();
    }

    //getters and setters for Enrollment

    public Student getStudent() {
        return this.student;
    }

    public CourseSection getCourseSection() {
        return this.courseSection;
    }

    /**
     * Calculates the total assigned weightage of assessment grades for the enrollment.
     *
     * @return The total assigned weightage.
     */
    public float getTotalAssignedWeightage() {
        float sum = 0;
        for(AssessmentGrade grade:assessmentGrades) {
            sum += grade.getWeightage();
        }
        return sum;
    }

    /**
     * Calculates the unassigned weightage available for adding new assessment grades.
     *
     * @return The unassigned weightage.
     */
    public float getUnassignedWeightage() {
        return 100 - getTotalAssignedWeightage();
    }

    /**
     * Adds an assessment grade to the enrollment.
     *
     * @param assessmentGrade The assessment grade to add.
     * @throws IllegalArgumentException if the assessmentGrade is null.
     */
    public void addAssessmentGrade(AssessmentGrade assessmentGrade) {
        if (assessmentGrade == null) {
            throw new IllegalArgumentException("assessmentGrade cannot be null");
        } else if (getTotalAssignedWeightage() + assessmentGrade.getWeightage() > 100) {
            double totalWeightage = getTotalAssignedWeightage();
            double newWeightage = assessmentGrade.getWeightage();
            double excessWeightage = totalWeightage + newWeightage - 100;
        
            String errorMessage = String.format(
                "Adding the assessment grade would exceed the total weightage limit (100). " +
                "Current total: %.2f, New weightage: %.2f, Excess weightage: %.2f",
                totalWeightage, newWeightage, excessWeightage);
        
            logger.warn(errorMessage);
        } else {
            this.assessmentGrades.add(assessmentGrade);
        }
    }

    //equals, hash and toString for Enrollment
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Enrollment)) {
            return false;
        }
        Enrollment enrollment = (Enrollment) o;
        return Objects.equals(student, enrollment.student) && Objects.equals(courseSection, enrollment.courseSection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, courseSection);
    }

    @Override
    public String toString() {
        return "{" +
            " student='" + getStudent() + "'" +
            ", courseSection='" + getCourseSection() + "'" +
            "}";
    }
    
}
