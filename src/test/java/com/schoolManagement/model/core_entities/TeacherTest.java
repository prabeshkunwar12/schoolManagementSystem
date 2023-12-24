package com.schoolManagement.model.core_entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.model.core_entities.Department;
import com.school_management.model.core_entities.Teacher;
import com.school_management.model.support_entities.schedule.CourseSectionSchedule;
import com.school_management.model.support_entities.schedule.Schedule;

class TeacherTest {
    @Mock
    Department departmentMock;

    @Mock
    Schedule scheduleMock;

    Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        teacher = new Teacher("Teacher A", "CA", "9999999999", "aaa@aaa.com", departmentMock);
    }

    @Test
    void testDefaultConstructor() {
        Teacher teacherDefault = new Teacher();
        assertNotNull(teacherDefault);
    }

    @Test
    void testValidConstructor() {
        assertNotNull(departmentMock);
    }

    @Test
    void testInvalidConstructor() {
        // name is null
        assertThrows(IllegalArgumentException.class, () -> new Teacher(null, "CA", "9999999999", "aaa@aaa.com", departmentMock));
        // name is empty
        assertThrows(IllegalArgumentException.class, () -> new Teacher("", "CA", "9999999999", "aaa@aaa.com", departmentMock));
        // Name is too long >100 characters
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "CA", "9999999999", "aaa@aaa.com", departmentMock));
        // Country Code is null
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", null, "9999999999", "aaa@aaa.com", departmentMock));
        // Country Code is invalid
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "AAAAAAAA", "9999999999", "aaa@aaa.com", departmentMock));
        // phone number is too long >14 digits
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "999999999999999", "aaa@aaa.com", departmentMock));
        // phone number is too short < 2 digits
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9", "aaa@aaa.com", departmentMock));
        // phone number is in wrong format
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "99absdff9", "aaa@aaa.com", departmentMock));
        // email is null
        assertThrows(NullPointerException.class, () -> new Teacher("Teacher A", "CA", "9999999999", null, departmentMock));
        // email is empty
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9999999999", "", departmentMock));
        // email is  too long > 100 characters
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9999999999", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaa.com", departmentMock));
        // department is null
        assertThrows(NullPointerException.class, () -> new Teacher("Teacher A", "CA", "9999999999", "aaa@aaa.com", null));
    }

    @Test
    void testSetterAndGetterForTeacherID() {
        assertEquals(0, teacher.getTeacherID());
        teacher.setTeacherID(100);
        assertEquals(100, teacher.getTeacherID());
    }

    @Test
    void testValidSetterAndGetterForTeacherName() {
        assertEquals("Teacher A", teacher.getName());
        teacher.setName("Teacher B");
        assertEquals("Teacher B", teacher.getName());
    }

    @Test
    void testInvalidsetterAndGetterForTeacherName() {
        // name is null
        assertThrows(IllegalArgumentException.class, () -> teacher.setName(null));
        // name is empty
        assertThrows(IllegalArgumentException.class, () -> teacher.setName(""));
        // Name is too long >100 characters
        assertThrows(IllegalArgumentException.class, () -> teacher.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void testValidSetterAndGetterForPhoneNumber() {
        assertEquals("+19999999999", teacher.getPhoneNumber());
        
        teacher.setPhoneNumber("US", "1919191919");
        assertEquals("+11919191919", teacher.getPhoneNumber());

        teacher.setPhoneNumber("+9779999999999");
        assertEquals("+9779999999999", teacher.getPhoneNumber());
    }

    @Test
    void testInvalidSetterAndGetterForPhoneNumber() {
        // country code is null
        assertThrows(IllegalArgumentException.class,() -> teacher.setPhoneNumber(null, "9999999999"));
        // country code is invalid
        assertThrows(IllegalArgumentException.class, () -> teacher.setPhoneNumber("sdevas", "9999999999"));
        // phone number is null
        assertThrows(IllegalArgumentException.class, () -> teacher.setPhoneNumber("CA", null));
        // phone number is too long >15 if country code matches with first digit 
        assertThrows(IllegalArgumentException.class, () -> teacher.setPhoneNumber("CA","1919191919191999"));
        // phone number is too long >14 if country code doesn't match with first digit
        assertThrows(IllegalArgumentException.class, () -> teacher.setPhoneNumber("CA","1919191919191999"));
        // phone number is too short <2
        assertThrows(IllegalArgumentException.class, () -> teacher.setPhoneNumber("CA","1"));
        // phone number is invalid
        assertThrows(IllegalArgumentException.class, () -> teacher.setPhoneNumber("CA","1has2had3"));
    }

    @Test
    void testValidSetterAndGetterForEmail() {
        assertEquals("aaa@aaa.com", teacher.getEmail());
        teacher.setEmail("bbb@bbb.com");
        assertEquals("bbb@bbb.com", teacher.getEmail());
    }

    @Test 
    void testInvalidSetterAndGetterForEmail() {
        // email is null
        assertThrows(NullPointerException.class, () -> teacher.setEmail(null));
        // email is empty
        assertThrows(IllegalArgumentException.class, () -> teacher.setEmail(""));
        // email contains invalid characters
        assertThrows(IllegalArgumentException.class, () -> teacher.setEmail("aa}{}@aaa.com"));
        // email doesn't have @
        assertThrows(IllegalArgumentException.class, () -> teacher.setEmail("aaa_aaa.com"));
        // email doesn't have top domain
        assertThrows(IllegalArgumentException.class, () -> teacher.setEmail("aa@com"));
        // email's top domain is empty
        assertThrows(IllegalArgumentException.class, () -> teacher.setEmail("aa@aaa."));
    }

    @Test 
    void testValidSetterAndGetterForDepartment() {
        assertEquals(departmentMock, teacher.getDepartment());

        Department departmentMock1 = mock(Department.class);
        teacher.setDepartment(departmentMock1);
        
        assertEquals(departmentMock1, teacher.getDepartment());
    }

    @Test 
    void testInvalidSetterAndGetterForDepartment() {
        // department is null
        assertThrows(NullPointerException.class, () -> teacher.setDepartment(null));
    }

    @Test 
    void testValidSetterAndGetterForSchedule() {
        teacher.setSchedule(scheduleMock);
        assertEquals(scheduleMock, teacher.getSchedule());
    }

    @Test 
    void testInvalidSetterAndGetterForSchedule() {
        // Schedule is null
        assertThrows(NullPointerException.class, () -> teacher.setSchedule(null));
    }

    @Test
    void testSuccessfulAddCourseSectionSchedule() {
        CourseSectionSchedule cSchedule = mock(CourseSectionSchedule.class);
        when(scheduleMock.addCourseSectionSchedule(cSchedule)).thenReturn(true);
        assertEquals(true, teacher.addCourseSectionSchedule(cSchedule));
    }

    @Test
    void testFailedAddCourseSectionSchedule() {
        CourseSectionSchedule cSchedule = mock(CourseSectionSchedule.class);
        teacher.setSchedule(scheduleMock);
        when(scheduleMock.addCourseSectionSchedule(cSchedule)).thenReturn(false);

        // Invoke the method on the teacher object
        boolean result1 = teacher.addCourseSectionSchedule(null); // Passing null should return false
        boolean result2 = teacher.addCourseSectionSchedule(cSchedule); // Mocked behavior returns false

        // Verify that the method behaves as expected
        assertFalse(result1);
        assertFalse(result2);
    }

}
