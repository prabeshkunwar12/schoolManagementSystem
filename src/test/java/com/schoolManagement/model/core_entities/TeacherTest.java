package com.schoolManagement.model.core_entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.model.core_entities.Department;
import com.school_management.model.core_entities.Teacher;
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
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9999999999", null, departmentMock));
        // email is empty
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9999999999", "", departmentMock));
        // email is  too long > 100 characters
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9999999999", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaa.com", departmentMock));
        // department is null
        assertThrows(IllegalArgumentException.class, () -> new Teacher("Teacher A", "CA", "9999999999", "aaa@aaa.com", null));
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
        assertEquals("aaa@aaa.aaa", teacher.getEmail());
        teacher.setEmail("bbb@bbb.bbb");
    }
}
