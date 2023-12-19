/**
 * The `CourseSection` class represents a section of a course within the educational system.
 * It manages the scheduling, room allocation, and enrollment of students.
 * This class handles the association between a course, room, teacher, enrollment list, and grading criteria.
 *
 * Usage:
 * Create a `CourseSection` instance by providing essential parameters such as section ID, course, room, teacher, and schedule.
 * Utilize methods like `addEnrollment`, `removeEnrollment`, and `setPassingGrade` to manage enrollments and grading criteria.
 *
 * Functionalities:
 * - Initializes a new course section with associated parameters.
 * - Manages room allocation for the section based on scheduling.
 * - Adds and removes student enrollments from the section.
 * - Sets and retrieves the teacher for the course section.
 * - Manages and retrieves the passing grade required for the section.
 * - Handles checking whether an enrollment in the section has passed or failed.
 *
 * @see Course
 * @see Room
 * @see Teacher
 * @see Enrollment
 * @see CourseSectionSchedule
 */
package com.school_management.core_entities.enrollment;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.core_entities.Course;
import com.school_management.core_entities.Teacher;
import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.school.Room;
import com.school_management.support_entities.time_frame.Session;

public class CourseSection  {
    private int sectionID;
    private final Course course;
    private Room room;
    private final List<Enrollment> enrollmentList;
    private Teacher teacher;
    private final Session session;
    private float passingGrade;
    private final CourseSectionSchedule schedule;

    private Logger logger = LoggerFactory.getLogger(CourseSection.class);

    
    /**
     * Constructor to initialize a CourseSection with provided parameters.
     *
     * @param course        The associated Course.
     * @param room          The room where the section is conducted.
     * @param enrollmentList   List of enrollments in the section.
     * @param teacher       The teacher assigned to the section.
     * @param schedule      The schedule of the course
     */
    public CourseSection(Course course, Room room, Teacher teacher, Session session, CourseSectionSchedule schedule) {
        if(course==null || room==null || teacher==null || session==null || schedule==null) {
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        if(schedule.getStartDate().isBefore(session.getStartDate()) || schedule.getEndDate().isAfter(session.getEndDate())){
            logger.error("schedule's startDate must not be before and endDate must not be after session's startdate and endDate respectively", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.schedule = schedule;
        this.session = session;
        if(setRoom(room) && setTeacher(teacher)) {
            this.course = course;
            this.enrollmentList = new ArrayList<>();
            this.passingGrade = 0;
            logger.info("New Course section for {} Initialized", this.course);
        } else {
            logger.error("failed to initialize course Section", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
    }

    // Getters and setters

    public int getSectionID() {
        return this.sectionID;
    }

    public void setSectionID(int sectionID) {
        this.sectionID = sectionID;
        logger.info("CourseSection ID modified to {}", sectionID);
    }

    public Course getCourse() {
        return this.course;
    }

    public Room getRoom() {
        return this.room;
    }
    public Session getSession() {
        return this.session;
    }

    public boolean setRoom(Room room) {
        if(room.bookRoom(this.schedule)){
            this.room = room;
            logger.info("{} is booked with capacity of {}", room.getRoomType(), room.getStudentCapacity());
            return true;
        }
        logger.error("Failed to book the room.", new IllegalArgumentException());
        return false;
    }

    /**
     * Retrieves the list of enrollments for this course section.
     *
     * @return An unmodifiable list of enrollmentList.
     */
    public List<Enrollment> getEnrollmentList() {
        return Collections.unmodifiableList(this.enrollmentList);
    }

    /**
     * Adds an enrollment to the list of enrollments for this section.
     *
     * @param enrollment The enrollment to add.
     * @throws IllegalArgumentException if enrollment is null.
     */
    public boolean addEnrollment(Enrollment enrollment) {
        if(enrollment != null) {
            this.enrollmentList.add(enrollment);
            logger.info("New enrollment for {} has been added.", enrollment.getStudent().getName());
            return true;
        }
        logger.error("failed to add new enrollment", new IllegalArgumentException());
        return false;
    }

    /**
     * Removes an enrollment from the list of enrollments for this section and also for the respective student.
     *
     * @param enrollment The enrollment to remove.
     * @throws IllegalArgumentException if enrollment is not found in the list.
     */
    public boolean removeEnrollment(Enrollment enrollment) {
        boolean removedEnrollment = enrollment.getStudent().removeCourse(enrollment) && this.enrollmentList.remove(enrollment);
        if(!(removedEnrollment)) {
            logger.error("Failed to remove the enrollment", new IllegalArgumentException());
            return false;
        }
        logger.info("Enrollment for {} has been removed.", enrollment.getStudent().getName());
        return true;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    /**
     * Sets the teacher for this course section.
     *
     * @param teacher The teacher to be assigned.
     * @throws IllegalArgumentException if the provided teacher is null.
     */
    public boolean setTeacher(Teacher teacher) {
        if(teacher != null && teacher.addCourseSectionSchedule(this.schedule)) { 
            this.teacher = teacher;
            logger.info("New teacher {} set for the course section", this.teacher.getName());
            return true;  
        }
        logger.error("Failed to set new teacher", new IllegalArgumentException());
        return false;
    }


    public float getPassingGrade() {
        return this.passingGrade;
    }

    public boolean setPassingGrade(float passingGrade) {
        if(passingGrade<0 || passingGrade>100) {
            logger.error("passing grade should not be in between 0 and 100. Failed to set the passing grade.", new IllegalArgumentException());
            return false;
        }
        this.passingGrade = passingGrade;
        logger.info("Passing grade changed to {}", passingGrade);
        return true;
    }


    /**
     * Checks if the provided enrollment exists in the list.
     *
     * @param enrollment The enrollment to check.
     * @return True if the enrollment is passed, false otherwise.
     */
    public boolean isPassed(Enrollment enrollment) {
        if(this.enrollmentList.contains(enrollment)) {
            return enrollment.isPassed();
        } else {
            throw new IllegalArgumentException("enrollment not found in the list");
        }
    }


    public CourseSectionSchedule getSchedule() {
        return this.schedule;
    }

    //hash, equals and toString


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CourseSection)) {
            return false;
        }
        CourseSection courseSection = (CourseSection) o;
        return sectionID == courseSection.sectionID && Objects.equals(course, courseSection.course) && Objects.equals(room, courseSection.room) && Objects.equals(session, courseSection.session) && Objects.equals(enrollmentList, courseSection.enrollmentList) && Objects.equals(teacher, courseSection.teacher) && passingGrade == courseSection.passingGrade && Objects.equals(schedule, courseSection.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionID, course, room, session, enrollmentList, teacher, passingGrade, schedule);
    }


    @Override
    public String toString() {
        return "{" +
            " sectionID='" + getSectionID() + "'" +
            ", course='" + getCourse() + "'" +
            ", room='" + getRoom() + "'" +
            ", session='" + getSession() + "'" +
            ", enrollmentList='" + getEnrollmentList() + "'" +
            ", teacher='" + getTeacher() + "'" +
            ", passingGrade='" + getPassingGrade() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
    
    
}
