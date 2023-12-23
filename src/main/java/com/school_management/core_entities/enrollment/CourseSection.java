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
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.core_entities.Course;
import com.school_management.core_entities.Teacher;
import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.school.Room;
import com.school_management.support_entities.time_frame.Session;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Course_section")
public class CourseSection  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private int sectionID;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne 
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(name = "passing_grade")
    private float passingGrade;

    @ManyToOne
    @JoinColumn(name = "course_section_schedule_id")
    private CourseSectionSchedule schedule;

    private Logger logger = LoggerFactory.getLogger(CourseSection.class);

    // Default constructor for JPA entity compliance.
    public CourseSection() {
    }
    
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

        // Validate and set room and teacher if the schedule allows it
        if(schedule.getStartDate().isBefore(session.getStartDate()) || schedule.getEndDate().isAfter(session.getEndDate())){
            logger.error("schedule's startDate must not be before and endDate must not be after session's startdate and endDate respectively", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.schedule = schedule;
        this.session = session;
        if(setRoomCheck(room) && setTeacherCheck(teacher)) {
            this.course = course;
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

    public Course getCourse() {
        return this.course;
    }

    //setter for Course for JPA compliance
    public void setCourse(Course course) {
        if(course == null) {
            logger.error("course is null", new IllegalArgumentException());
            throw new IllegalArgumentException("course cannot be null");
        }
        this.course = course; 
        logger.info("course for course {} has been changed to {}", getSectionID(), getCourse().getCourseName());
    }

    public Room getRoom() {
        return this.room;
    }

    //setter for Room for JPA compliance
    public void setRoom(Room room) {
        if(room == null) {
            logger.error("room is null", new IllegalArgumentException());
            throw new IllegalArgumentException("room cannot be null");
        }
        this.room = room; 
        logger.info("room for course {} has been changed to {}", getSectionID(), getRoom().getRoomName());
    
    }

    /**
     * Checks if the room can be assigned to the section based on the provided schedule.
     * 
     * @param room The room to be assigned.
     * @return True if the room can be assigned; otherwise, false.
     */
    public boolean setRoomCheck(Room room) {
        if(room == null) {
            logger.error("Room is null", new IllegalArgumentException());
            return false;
        }
        if(room.bookRoom(this.schedule)){
            this.room = room;
            logger.info("{} is booked with capacity of {}", room.getRoomType(), room.getStudentCapacity());
            return true;
        }
        logger.error("Failed to book the room.", new IllegalArgumentException());
        return false;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }

    //setter for Room for JPA compliance
    public void setTeacher(Teacher teacher) {
        if(teacher == null) {
            logger.error("teacher is null", new IllegalArgumentException());
            throw new IllegalArgumentException("teacher cannot be null");
        }
        this.teacher = teacher; 
        logger.info("teacher for course {} has been changed to {}", getSectionID(), getTeacher().getName());
    
    }

    /**
     * Sets the teacher for this course section if the provided teacher is not null and
     * can be assigned to the schedule.
     *
     * @param teacher The teacher to be assigned.
     * @return True if the teacher is successfully assigned; otherwise, false.
     */
    public boolean setTeacherCheck(Teacher teacher) {
        if(teacher != null && teacher.addCourseSectionSchedule(this.schedule)) { 
            this.teacher = teacher;
            logger.info("New teacher {} set for the course section", this.teacher.getName());
            return true;  
        }
        logger.error("Failed to set new teacher", new IllegalArgumentException());
        return false;
    } 

    public Session getSession() {
        return this.session;
    }

    //setter for Room for JPA compliance
    public void setSession(Session session) {
        if(session == null) {
            logger.error("session is null", new IllegalArgumentException());
            throw new IllegalArgumentException("session cannot be null");
        }
        this.session = session; 
        logger.info("session for course {} has been changed to {}", getSectionID(), getSession().getSessionID());
    
    }

    public float getPassingGrade() {
        return this.passingGrade;
    }

    public void setPassingGrade(float passingGrade) {
        if(passingGrade<0 || passingGrade>100) {
            logger.error("passing grade should be in between 0 and 100. Failed to set the passing grade.", new IllegalArgumentException());
            throw new IllegalArgumentException("passing grade should be in between 0 and 100. Failed to set the passing grade.");
        }
        this.passingGrade = passingGrade;
        logger.info("Passing grade for section {} changed to {}.", getSectionID(), getPassingGrade());
    }

    public CourseSectionSchedule getSchedule() {
        return this.schedule;
    }
    
    public void setSchedule(CourseSectionSchedule schedule) {
        if(schedule == null) {
            logger.error("schedule is null", new IllegalArgumentException());
            throw new IllegalArgumentException("schedule cannot be null");
        }
        this.schedule = schedule;
        logger.info("schedule for {} has been modified to {}", getSectionID(), getSchedule().getCourseSectionScheduleID());
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
        return sectionID == courseSection.sectionID && Objects.equals(course, courseSection.course) && Objects.equals(room, courseSection.room) && Objects.equals(teacher, courseSection.teacher) && Objects.equals(session, courseSection.session) && passingGrade == courseSection.passingGrade && Objects.equals(schedule, courseSection.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectionID, course, room, teacher, session, passingGrade, schedule);
    }


    @Override
    public String toString() {
        return "{" +
            " sectionID='" + getSectionID() + "'" +
            ", course='" + getCourse() + "'" +
            ", room='" + getRoom() + "'" +
            ", teacher='" + getTeacher() + "'" +
            ", session='" + getSession() + "'" +
            ", passingGrade='" + getPassingGrade() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
    
}
