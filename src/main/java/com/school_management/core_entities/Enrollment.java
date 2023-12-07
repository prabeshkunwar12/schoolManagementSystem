package com.school_management.core_entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.AssessmentGrade;
import com.school_management.support_entities.AssessmentType;
import com.school_management.support_entities.Attendance;
import com.school_management.support_entities.EnrollmentStatus;
import com.school_management.support_entities.FinalCourseGrade;
import com.school_management.support_entities.Grades;

public class Enrollment {
    Student student;
    CourseSection courseSection;
    EnrollmentStatus enrollmentStatus;
    List<AssessmentGrade> assessmentGrades;
    Attendance attendance;
    Grades finalGrade;

    // Logger for logging messages related to the Enrollment class
    private static final Logger logger = LoggerFactory.getLogger(Enrollment.class);

    //Constructor for Enrollment
    public Enrollment(Student student, CourseSection courseSection, Attendance attendance) {
        this.student = student;
        this.courseSection = courseSection;
        this.enrollmentStatus = EnrollmentStatus.PLANNED;
        this.attendance = attendance;
        this.assessmentGrades = new ArrayList<>();
        this.finalGrade = new FinalCourseGrade();
        this.finalGrade.setPassingGrade(getCourseSection().getPassingGrade());
    }

    //getters and setters for Enrollment

    public Student getStudent() {
        return this.student;
    }

    public CourseSection getCourseSection() {
        return this.courseSection;
    }

    public Attendance getAttendance() {
        return this.attendance;
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
        } else if(assessmentGrade.getAssessment().getAssessmentType() == AssessmentType.BONOUS) {
            this.assessmentGrades.add(assessmentGrade);
        } else if(getTotalAssignedWeightage() + assessmentGrade.getWeightage() > 100) {
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

    public List<AssessmentGrade> getAssessmentGrades() {
        return Collections.unmodifiableList(assessmentGrades);
    }

    /**
     * Calculates the final grade based on the contributions of assessment grades.
     *
     * @return The calculated final grade. If the sum exceeds 100, returns 100.
     */
    public float calculateFinalGrade() {
        float sum = 0;

        // Loop through assessmentGrades to calculate the sum of final grade contributions
        for (AssessmentGrade assessmentGrade : assessmentGrades) {
            sum += assessmentGrade.getFinalGradeContibution();
        }

        // If the sum exceeds 100, return 100, otherwise return the sum
        if (sum > 100) {
            return 100;
        }
        return sum;
    }

    public Grades getFinalGrade() {
        return this.finalGrade;
    }

    private void setFinalGrade(float finalScoredGrade) {
        this.finalGrade.setScoredGrade(finalScoredGrade);
    }

    public float getFinalCourseGrade() {
        return this.finalGrade.getScoredGrade();
    }

    public void setFinalCourseGrade() {
        setFinalGrade(calculateFinalGrade());
    } 

    public boolean isPassed() {
        for(AssessmentGrade assessmentGrade: assessmentGrades) {
            if(!assessmentGrade.isPassed()) {
                return false;
            }
        }
        return calculateFinalGrade() >= getCourseSection().getPassingGrade();
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
        return Objects.equals(student, enrollment.student) && Objects.equals(courseSection, enrollment.courseSection) && Objects.equals(assessmentGrades, enrollment.assessmentGrades) && Objects.equals(finalGrade, enrollment.finalGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, courseSection, assessmentGrades, finalGrade);
    }
    

    @Override
    public String toString() {
        return "{" +
            " student='" + getStudent() + "'" +
            ", courseSection='" + getCourseSection() + "'" +
            ", assessmentGrades='" + getAssessmentGrades() + "'" +
            ", finalGrade='" + getFinalGrade() + "'" +
            "}";
    }
    
}
