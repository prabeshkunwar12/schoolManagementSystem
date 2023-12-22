/**
 * The `Enrollment` class manages the enrollment of a student in a course section.
 * It handles the addition of assessment grades, calculation of final grades, and tracking the attendance.
 * This class provides functionalities to manage assessment grades, calculate final grades,
 * track attendance, and determine enrollment status.
 *
 * Usage:
 * Create an instance of `Enrollment` by providing a `Student` and a `CourseSection`.
 * Use methods like `addAssessmentGrade` to add assessment grades,
 * `calculateFinalGrade` to compute the final grade, and `isPassed` to check the pass status.
 *
 * Functionalities:
 * - Initialize enrollment for a student in a course section.
 * - Add assessment grades to the enrollment.
 * - Calculate final grades based on assessment contributions.
 * - Track and calculate attendance.
 * - Determine the enrollment status and pass status.
 *
 * @see Student
 * @see CourseSection
 * @see AssessmentGrade
 * @see FinalCourseGrade
 * @see Attendance
 * @see Grades
 */
package com.school_management.core_entities.enrollment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.assessment.AssessmentType;
import com.school_management.support_entities.attendance.Attendance;
import com.school_management.support_entities.grade.AssessmentGrade;
import com.school_management.support_entities.grade.FinalCourseGrade;
import com.school_management.support_entities.grade.Grades;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private int  enrollmentID;

    
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    
    @ManyToOne
    @JoinColumn(name = "course_section_id")
    private CourseSection courseSection;
    
    @Column(name = "enrollment_status")
    private EnrollmentStatus enrollmentStatus;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enrollment_id")
    private List<AssessmentGrade> assessmentGrades;
    
    @ManyToOne
    @JoinColumn(name = "attendance_id")
    private Attendance attendance;
    
    @ManyToOne
    @JoinColumn(name = "final_course_grade_id")
    private Grades finalGrade;
    
    @Column(name = "pass_status")
    private PassStatus passStatus;

    // Logger for logging messages related to the Enrollment class
    private static Logger logger = LoggerFactory.getLogger(Enrollment.class);

    // Default constructor for JPA compliance
    public Enrollment() {}

    //Constructor for Enrollment
    public Enrollment(Student student, CourseSection courseSection) {
        if (student == null || courseSection == null) {
            logger.error("Student and CourseSection cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException("Student and CourseSection cannot be null");
        }

        if (!areSchedulesCompatible(student, courseSection)) {
            logger.error("Student's schedule and CourseSection's schedule conflict with each other", new IllegalArgumentException());
            throw new IllegalArgumentException("Scheduling conflict between student and course section");
        }

        this.student = student;
        this.courseSection = courseSection;
        this.passStatus = PassStatus.NOT_DECIDED;
        initializeEnrollment();
        logger.info("New Enrollment created");
    }

    public int getEnrollmentID() {
        return this.enrollmentID;
    }

    private boolean areSchedulesCompatible(Student student, CourseSection courseSection) {
        return student.getSchedule().addCourseSectionSchedule(courseSection.getSchedule());
    }

    private void initializeEnrollment() {
        this.enrollmentStatus = EnrollmentStatus.PLANNED;
        this.attendance = new Attendance(courseSection.getSchedule());
        this.assessmentGrades = new ArrayList<>();
        this.finalGrade = new FinalCourseGrade();
        this.finalGrade.setPassingGrade(courseSection.getPassingGrade());
    }


    //getters and setters for Enrollment

    public Student getStudent() {
        return this.student;
    }

    //jpa compliance
    public void setStudent(Student student) {
        if(student == null) {
            logger.error("student is null", new IllegalArgumentException());
            throw new IllegalArgumentException("student cannot be null");
        }
        this.student = student;
        logger.info("student for {} has been modified to {}", getEnrollmentID(), getStudent().getStudentID());
    }

    public CourseSection getCourseSection() {
        return this.courseSection;
    }

    //jpa compliance
    public void setCourseSection(CourseSection courseSection) {
        if(courseSection == null) {
            logger.error("courseSection is null", new IllegalArgumentException());
            throw new IllegalArgumentException("courseSection cannot be null");
        }
        this.courseSection = courseSection;
        logger.info("courseSection for {} has been modified to {}", getEnrollmentID(), getCourseSection().getSectionID());
    }

    public Attendance getAttendance() {
        return this.attendance;
    }

    //jpa compliance
    public void setAttendance(Attendance attendance) {
        if(attendance == null) {
            logger.error("attendance is null", new IllegalArgumentException());
            throw new IllegalArgumentException("attendance cannot be null");
        }
        this.attendance = attendance;
        logger.info("attendance for {} has been modified to {}", getEnrollmentID(), getAttendance().getAttendanceID());
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
            logger.error("assessmentGrade cannot be null", new IllegalArgumentException());
        } else if(assessmentGrade.getAssessment().getAssessmentType() == AssessmentType.BONOUS) {
            this.assessmentGrades.add(assessmentGrade);
            logger.info("Bonous grade added");
        } else if(getTotalAssignedWeightage() + assessmentGrade.getWeightage() > 100) {
            double totalWeightage = getTotalAssignedWeightage();
            double newWeightage = assessmentGrade.getWeightage();
            double excessWeightage = totalWeightage + newWeightage - 100;
        
            String errorMessage = String.format(
                "Adding the assessment grade would exceed the total weightage limit (100). " +
                "Current total: %.2f, New weightage: %.2f, Excess weightage: %.2f",
                totalWeightage, newWeightage, excessWeightage);
        
            logger.error(errorMessage, new IllegalArgumentException());
        } else {
            this.assessmentGrades.add(assessmentGrade);
            logger.info("Assessment added with the weightage of {}.", assessmentGrade.getWeightage());
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

        logger.info("calculating final grade...");
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

    public void setFinalGrade(float finalScoredGrade) {
        this.finalGrade.setScoredGrade(finalScoredGrade);
        logger.info("final grade set.");
    }

    // JPA complaince
    public void setFinalGrade(Grades finalGrade) {
        if(finalGrade == null) {
            logger.error("finalGrade is null", new IllegalArgumentException());
            throw new IllegalArgumentException("finalGrade cannot be null");
        }
        this.finalGrade = finalGrade;
        logger.info("finalGrade for {} has been modified to {}", getEnrollmentID(), getFinalGrade().getScoredGrade());
    }

    public float getFinalCourseGrade() {
        return this.finalGrade.getScoredGrade();
    }

    public void setFinalCourseGrade() {
        setFinalGrade(calculateFinalGrade());
    } 

    public void decidePassed() {
        
        if(enrollmentStatus.equals(EnrollmentStatus.REGISTERED) && calculateFinalGrade() >= getCourseSection().getPassingGrade()) {
            boolean passed = true;
            for(AssessmentGrade assessmentGrade: assessmentGrades) {
                if(!assessmentGrade.isPassed()) {
                    passed = false;
                }
            }
            if(passed) {
                passStatus = PassStatus.PASSED;
            } else {
                passStatus = PassStatus.FAILED;
            }
        } else {
            passStatus = PassStatus.FAILED;
        }
    }

    public boolean isPassed() {
        decidePassed();
        return passStatus.equals(PassStatus.PASSED);
    }


    public EnrollmentStatus getEnrollmentStatus() {
        return this.enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    public PassStatus getPassStatus() {
        return this.passStatus;
    }

    // JPA complaiance
    public void setPassStatus(PassStatus passStatus) {
        if(passStatus == null) {
            logger.error("passStatus is null", new IllegalArgumentException());
            throw new IllegalArgumentException("passStatus cannot be null");
        }
        this.passStatus = passStatus;
        logger.info("passStatus for {} has been modified to {}", getEnrollmentID(), getPassStatus());
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
        return Objects.equals(student, enrollment.student) && Objects.equals(courseSection, enrollment.courseSection) && Objects.equals(enrollmentStatus, enrollment.enrollmentStatus) && Objects.equals(assessmentGrades, enrollment.assessmentGrades) && Objects.equals(attendance, enrollment.attendance) && Objects.equals(finalGrade, enrollment.finalGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, courseSection, enrollmentStatus, assessmentGrades, attendance, finalGrade);
    }


    @Override
    public String toString() {
        return "{" +
            " student='" + getStudent() + "'" +
            ", courseSection='" + getCourseSection() + "'" +
            ", enrollmentStatus='" + getEnrollmentStatus() + "'" +
            ", assessmentGrades='" + getAssessmentGrades() + "'" +
            ", attendance='" + getAttendance() + "'" +
            ", finalGrade='" + getFinalGrade() + "'" +
            "}";
    }
    
    
}
