package com.schoolManagement.model.core_entities.enrollment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.model.core_entities.Course;
import com.school_management.model.core_entities.Teacher;
import com.school_management.model.core_entities.enrollment.CourseSection;
import com.school_management.model.support_entities.schedule.CourseSectionSchedule;
import com.school_management.model.support_entities.school.Room;
import com.school_management.model.support_entities.school.RoomType;
import com.school_management.model.support_entities.time_frame.Session;

class CourseSectionTest {
    @Mock
    Course courseMock;

    @Mock
    Room roomMock;

    @Mock
    Teacher teacherMock;

    @Mock
    Session sessionMock;

    @Mock
    CourseSectionSchedule scheduleMock;

    CourseSection courseSection;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(scheduleMock.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        when(scheduleMock.getEndDate()).thenReturn(LocalDate.of(2020, 4, 28));
        
        when(sessionMock.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        when(sessionMock.getEndDate()).thenReturn(LocalDate.of(2020, 4, 29));
        
        when(roomMock.bookRoom(scheduleMock)).thenReturn(true);
        when(roomMock.getRoomType()).thenReturn(RoomType.HALL);
        when(roomMock.getStudentCapacity()).thenReturn(80);

        when(teacherMock.addCourseSectionSchedule(scheduleMock)).thenReturn(true);
        when(teacherMock.getName()).thenReturn("Teacher A");
        
        courseSection = new CourseSection(courseMock, roomMock, teacherMock, sessionMock, scheduleMock);
    }

    @Test
    void  testDefaultConstructor() {
        courseSection = new CourseSection();
        assertNotNull(courseSection);
    }

    @Test 
    void testValidConstructor() {
        assertNotNull(courseSection);
    }

    @Test 
    void testInvalidConstructor() {
        //course is null
        assertThrows(NullPointerException.class,()-> new CourseSection(null, roomMock, teacherMock, sessionMock, scheduleMock));

        //room is null
        assertThrows(NullPointerException.class, ()->new CourseSection(courseMock, null, teacherMock, sessionMock, scheduleMock));
        //room is not available
        when(roomMock.bookRoom(scheduleMock)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, ()->new CourseSection(courseMock, roomMock, teacherMock, sessionMock, scheduleMock));
        when(roomMock.bookRoom(scheduleMock)).thenReturn(true);

        //teacher is null
        assertThrows(NullPointerException.class, ()->new CourseSection(courseMock, roomMock, null, sessionMock, scheduleMock));
        //teacher is not available
        when(teacherMock.addCourseSectionSchedule(scheduleMock)).thenReturn(false);
        assertThrows(IllegalArgumentException.class, ()->new CourseSection(courseMock, roomMock, teacherMock, sessionMock, scheduleMock));
        when(teacherMock.addCourseSectionSchedule(scheduleMock)).thenReturn(true);

        //session is null
        assertThrows(NullPointerException.class, ()->new CourseSection(courseMock, roomMock, teacherMock, null, scheduleMock));
    
        //schedule is null
        assertThrows(NullPointerException.class, ()->new CourseSection(courseMock, roomMock, teacherMock, sessionMock, null));
        // schedule starts before session
        when(scheduleMock.getStartDate()).thenReturn(LocalDate.of(2019, 1, 4));
        assertThrows(IllegalArgumentException.class, ()->new CourseSection(courseMock, roomMock, teacherMock, sessionMock, scheduleMock));
        when(scheduleMock.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        // schedule ends after session
        when(scheduleMock.getEndDate()).thenReturn(LocalDate.of(2021, 4, 28));
        assertThrows(IllegalArgumentException.class, ()->new CourseSection(courseMock, roomMock, teacherMock, sessionMock, scheduleMock));
    }

    @Test
    void testValidGetterForSectionID() {
        assertEquals(0, courseSection.getSectionID());
    }

    @Test
    void testValidSetterAndGetterForCourse() {
        assertEquals(courseMock, courseSection.getCourse());
        Course course1 = mock(Course.class);
        courseSection.setCourse(course1); 
        assertEquals(courseSection.getCourse(), course1);
    }

    @Test
    void testInvalidSetterAndGetterForCourse() {
        // course is null
        assertThrows(NullPointerException.class, ()->courseSection.setCourse(null));
    }

    @Test
    void testValidSetterAndGetterForRoom() {
        assertEquals(roomMock, courseSection.getRoom());
        //setroom
        Room room1 = mock(Room.class);
        when(room1.bookRoom(scheduleMock)).thenReturn(true);
        courseSection.setRoom(room1);
        assertEquals(room1, courseSection.getRoom());
        //setRoomCheck        
        Room room2 = mock(Room.class);
        when(room2.bookRoom(scheduleMock)).thenReturn(true);
        courseSection.setRoomCheck(room2);
        assertEquals(room2, courseSection.getRoom());
    }

    @Test
    void testInvalidSetterAndGetterForRoom() {
        //room is null 
        assertThrows(NullPointerException.class, ()->courseSection.setRoom(null));
        assertThrows(NullPointerException.class, ()->courseSection.setRoomCheck(null));
        //room is not available
        when(roomMock.bookRoom(scheduleMock)).thenReturn(false);
        assertFalse( courseSection.setRoomCheck(roomMock));
    }

    @Test
    void testValidSetterAndGetterForTeacher() {
        assertEquals(teacherMock, courseSection.getTeacher());
        //setTeacher
        Teacher teacher1 = mock(Teacher.class);
        when(teacher1.addCourseSectionSchedule(scheduleMock)).thenReturn(true);
        courseSection.setTeacher(teacher1);
        assertEquals(teacher1, courseSection.getTeacher());
        //setTeacherCheck
        Teacher teacher2 = mock(Teacher.class);
        when(teacher2.addCourseSectionSchedule(scheduleMock)).thenReturn(true);
        courseSection.setTeacherCheck(teacher2);
        assertEquals(teacher2, courseSection.getTeacher());
    }

    @Test
    void testInvalidSetterAndGetterForTeacher() {
        //Teacher is null 
        assertThrows(NullPointerException.class, ()->courseSection.setTeacher(null));
        assertThrows(NullPointerException.class, ()->courseSection.setTeacherCheck(null));
        
        //Teacher is not available
        when(teacherMock.addCourseSectionSchedule(scheduleMock)).thenReturn(false);
        assertFalse( courseSection.setTeacherCheck(teacherMock));
    }

    @Test
    void testValidSetterAndGetterForSession() {
        assertEquals(sessionMock, courseSection.getSession());

        Session session1 = mock(Session.class);
        courseSection.setSession(session1);
        assertEquals(session1, courseSection.getSession());
    }

    @Test
    void testInvalidSetterAndGetterForSession() {
        // session is null
        assertThrows(NullPointerException.class, ()->courseSection.setSession(null));
    }

    @Test
    void testValidSetterAndGetterForSchedule() {
        assertEquals(scheduleMock, courseSection.getSchedule());

        CourseSectionSchedule schedule1 = mock(CourseSectionSchedule.class);
        courseSection.setSchedule(schedule1);
        assertEquals(schedule1, courseSection.getSchedule());
    }

    @Test
    void testInvalidSetterAndGetterForSchedule() {
        // Schedule is null
        assertThrows(NullPointerException.class, ()->courseSection.setSchedule(null));
    }

    @Test
    void testValidSetterAndGetterForSessionAndSchedule() {
        Session session1 = mock(Session.class);
        CourseSectionSchedule schedule1 = mock(CourseSectionSchedule.class);

        when(schedule1.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        when(schedule1.getEndDate()).thenReturn(LocalDate.of(2020, 4, 28));
        
        when(session1.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        when(session1.getEndDate()).thenReturn(LocalDate.of(2020, 4, 29));
        
        courseSection.setSessionAndSchedule(session1, schedule1);
        assertEquals(session1, courseSection.getSession());
        assertEquals(schedule1, courseSection.getSchedule());
    }

    @Test
    void testInvalidSetterAndGetterForSessionAndSchedule() {
        // both session and schedule are null
        assertThrows(NullPointerException.class, ()->courseSection.setSessionAndSchedule(null, null));
        
        Session session1 = mock(Session.class);
        CourseSectionSchedule schedule1 = mock(CourseSectionSchedule.class);

        when(schedule1.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        when(schedule1.getEndDate()).thenReturn(LocalDate.of(2020, 4, 28));
        
        when(session1.getStartDate()).thenReturn(LocalDate.of(2020, 1, 4));
        when(session1.getEndDate()).thenReturn(LocalDate.of(2020, 4, 29));
        
        // session is null
        assertThrows(NullPointerException.class, ()->courseSection.setSessionAndSchedule(null, schedule1));
        // schedule is null
        assertThrows(NullPointerException.class, ()->courseSection.setSessionAndSchedule(session1, null));

        //schedule start date is before session start date
        when(schedule1.getStartDate()).thenReturn(LocalDate.of(2019, 1, 4));
        assertFalse(courseSection.setSessionAndSchedule(session1, schedule1));

        //schedule end date is after session end date
        when(schedule1.getEndDate()).thenReturn(LocalDate.of(2022, 1, 1));
        assertFalse(courseSection.setSessionAndSchedule(session1, schedule1));
    }

    @Test
    void testValidSetterAndGetterForPassingGrade() {
        assertEquals(40, courseSection.getPassingGrade());

        courseSection.setPassingGrade(60);
        assertEquals(60, courseSection.getPassingGrade());
    }

    @Test
    void testInvalidSetterAndGetterForPassingGrade() {
        // passing grade less than 0
        assertThrows(IllegalArgumentException.class, ()->courseSection.setPassingGrade(-1));
        // passing grade more than 100
        assertThrows(IllegalArgumentException.class, ()->courseSection.setPassingGrade(101));
    }
}
