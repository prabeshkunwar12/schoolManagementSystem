package com.schoolManagement.core_entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.core_entities.Department;
import com.school_management.core_entities.Teacher;
import com.school_management.support_entities.schedule.Schedule;

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

}
