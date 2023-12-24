package com.schoolManagement.model.core_entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.model.core_entities.Course;
import com.school_management.model.core_entities.Department;

class CourseTest {
    @Mock 
    Department departmentMock;

    Course course;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        course = new Course("Course A", "This is Course A", departmentMock, 3);
    }

    @Test
    void testDefaultConstructor() {
        course = new Course();
        assertNotNull(course);
    }

    @Test 
    void testValidConstructor() {
        assertNotNull(course);
    }

    @Test
    void testInvalidConstructor() {
        // Course name is null
        assertThrows(NullPointerException.class, () -> new Course(null, "This is Course A", departmentMock, 3));
        // Course name is empty
        assertThrows(IllegalArgumentException.class, () -> new Course("", "This is Course A", departmentMock, 3));
        // Course name is too long >100 characters
        assertThrows(IllegalArgumentException.class, () -> new Course("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "This is Course A", departmentMock, 3));
        // Description is null
        assertThrows(NullPointerException.class, () -> new Course("Course A", null, departmentMock, 3));
        // Description is empty
        assertThrows(IllegalArgumentException.class, () -> new Course("Course A", "", departmentMock, 3));  
        // Description is too long >300 characters
        assertThrows(IllegalArgumentException.class, () -> new Course("Course A", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", departmentMock, 3));
    }

    @Test
    void testSetterAndGetterForCourseID() {
        assertEquals(0, course.getCourseID());
        course.setCourseID(100);
        assertEquals(100, course.getCourseID());
    }

    @Test
    void testValidSetterAndGetterForCourseName() {
        assertEquals("Course A", course.getCourseName());
        course.setCourseName("Course B");
        assertEquals("Course B", course.getCourseName());
    }

    @Test
    void testInvalidSetterAndGetterForCourseName() {
        // CourseName is null
        assertThrows(NullPointerException.class, () -> course.setCourseName(null));
        // CourseName is empty
        assertThrows(IllegalArgumentException.class, ()->course.setCourseName(""));
        // CourseName is too long >100 characters
        assertThrows(IllegalArgumentException.class, ()->course.setCourseName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void testValidSetterAndGetterForDescription() {
        assertEquals("This is Course A", course.getDescription());
        course.setDescription("This is Course B");
        assertEquals("This is Course B", course.getDescription());
    }

    @Test
    void testInvalidSetterAndGetterForDescription() {
        // Description is null
        assertThrows(NullPointerException.class, () -> course.setDescription(null));
        // Description is empty
        assertThrows(IllegalArgumentException.class, ()->course.setDescription(""));
        // Description is too long >300 characters
        assertThrows(IllegalArgumentException.class, ()->course.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void testValidSetterAndGetterForDepartment() {
        assertEquals(departmentMock, course.getDepartment());
        Department departmentMock2 = mock(Department.class);
        course.setDepartment(departmentMock2);
        assertEquals(departmentMock2, course.getDepartment());
    }

    @Test
    void testInvalidSetterAndGetterForDepartment() {
        // Department is null
        assertThrows(NullPointerException.class, () -> course.setDepartment(null));
    }

    @Test
    void testValidSetterAndGetterForCredits() {
        assertEquals(3, course.getCredits());
        course.setCredits(15);
        assertEquals(15, course.getCredits());
    }

    @Test
    void testInvalidSetterAndGetterForCredits() { 
        // credit less than 0
        assertThrows(IllegalArgumentException.class, () -> course.setCredits(-1));
    }
}