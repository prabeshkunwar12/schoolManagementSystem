package com.schoolManagement.core_entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.core_entities.Department;
import com.school_management.core_entities.Teacher;
import com.school_management.support_entities.school.School;

class DepartmentTest {

    @Mock
    School mockSchool;

    @Mock
    Teacher mockHeadOfDepartment;

    Department department;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        department = new Department("department 1",  "this is department 1", mockSchool);
    }

    @Test
    void testDefaultConstructor() {
        department = new Department();
        assertNotNull(department);
    }

    @Test
    void testValidConstructor() {
        assertNotNull(department);
    }

    @Test
    void testInvalidConstructor() {
        // department name is null
        assertThrows(IllegalArgumentException.class, () -> new Department(null,  "this is department 1", mockSchool));
        // department name is empty string
        assertThrows(IllegalArgumentException.class, () -> new Department("",  "this is department 1", mockSchool));
        // description is null
        assertThrows(IllegalArgumentException.class, () -> new Department("department 1",  null, mockSchool));
        // school is null
        assertThrows(IllegalArgumentException.class, () -> new Department("department 1",  "this is department 1", null));
    }

    @Test
    void testValidSetterAndGetterForDepartmentID() {
        assertEquals(0, department.getDepartmentID());
        department.setDepartmentID(100);
        assertEquals(100, department.getDepartmentID());
    }

    @Test
    void testValidSetterAndGetterForDepartmentName() {
        assertEquals("department 1", department.getDepartmentName());
        department.setDepartmentName("department 2");
        assertEquals("department 2", department.getDepartmentName());
    }

    @Test
    void testInvalidSetterAndGetterForDepartmentName() {
        // department name is null
        assertThrows(IllegalArgumentException.class, () -> department.setDepartmentName(null));
        // department name is empty string
        assertThrows(IllegalArgumentException.class, () -> department.setDepartmentName(""));
        // department name is too long >100
        assertThrows(IllegalArgumentException.class, () -> department.setDepartmentName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void testValidSetterAndGetterForDescription() {
        assertEquals("this is department 1", department.getDescription());
        department.setDescription("this is department 2");
        assertEquals("this is department 2", department.getDescription());
    }

    @Test
    void testInvalidSetterAndGetterForDescription() {
        // department name is null
        assertThrows(IllegalArgumentException.class, () -> department.setDescription(null));
       // department name is too long >100
        assertThrows(IllegalArgumentException.class, () -> department.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));
    }

    @Test
    void testValidSetterAndGetterForSchool() {
        assertEquals(mockSchool, department.getSchool());
        School mockSchool2 = mock(School.class);
        department.setSchool(mockSchool2);
        assertEquals(mockSchool2, department.getSchool());
    }

    @Test
    void testInvalidSetterAndGetterForSchool() {
        // school is null
        assertThrows(IllegalArgumentException.class, () -> department.setSchool(null));
    }

    @Test
    void testValidSetterAndGetterForHeadOfDepartment() {
        // Stubbing the behavior for getDepartment() 
        when(mockHeadOfDepartment.getDepartment()).thenReturn(department);

        department.setHeadOfDepartment(mockHeadOfDepartment);

        assertEquals(mockHeadOfDepartment, department.getHeadOfDepartment());
    }

    @Test
    void testInvalidSetterAndGetterForHeadOfDepartment() {
        // headOfDepartment is null
        assertThrows(IllegalArgumentException.class,() ->  department.setHeadOfDepartment(null));

        //headOfDepartment is of different department
        Department department2 = mock(Department.class);
        when(mockHeadOfDepartment.getDepartment()).thenReturn(department2);
        assertThrows(IllegalArgumentException.class, () -> department.setHeadOfDepartment(mockHeadOfDepartment));

    }
}
