package com.schoolManagement.support_entities.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.school_management.support_entities.school.School;
import com.school_management.support_entities.school.SchoolType;

class SchoolTest {
    @Test
    void schoolDefaultConstructorTestValid() {
        School school = new School();
        assertNotNull(school);
    }

    @Test
    void schoolConstructorTestValid() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        assertNotNull(school);
    }

    @Test
    void schoolConstructorTestSchoolNameNull() {
        String school_name = null;
        SchoolType schoolType = SchoolType.HIGH;
        
        assertThrows(IllegalArgumentException.class, ()->{School school = new School(school_name, schoolType);});
    }

    @Test
    void schoolConstructorTestSchoolTypeNull() {
        String school_name = "Diamond";
        SchoolType schoolType = null;
        
        assertThrows(IllegalArgumentException.class, ()->{School school = new School(school_name, schoolType);});
    }

    @Test
    void schoolIDGetterTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        assertEquals(0, school.getSchoolID());
    }

    @Test
    void schoolIDSetterTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        school.setSchoolID(100);
        assertEquals(100, school.getSchoolID());
    }

    @Test
    void schoolNameGetterTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        assertEquals("Diamond HS", school.getSchoolName());
    }

    @Test
    void schoolNameSetterTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        school.setSchoolName("Gold HS");
        assertEquals("Gold HS", school.getSchoolName());
    }

    @Test
    void schoolNameSetterNullTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        assertThrows(IllegalArgumentException.class, ()->{school.setSchoolName(null);});
    }

    @Test
    void schoolTypeGetterTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        assertEquals(SchoolType.HIGH, school.getSchoolType());
    }

    @Test
    void schoolTypeSetterTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        school.setSchoolType(SchoolType.COLLEGE);
        assertEquals(SchoolType.COLLEGE, school.getSchoolType());
    }

    @Test
    void schoolTypeSetterNullTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(school_name, schoolType);
        assertThrows(IllegalArgumentException.class, ()->{school.setSchoolType(null);});
    }

    @Test
    void equalsPositiveTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school1 = new School(school_name, schoolType);
        School school2 = new School(school_name, schoolType);
        assertEquals(school1, school2);
        assertEquals(school1.hashCode(), school2.hashCode());
    }

    @Test
    void idNotEqualEqualsTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school1 = new School(school_name, schoolType);
        School school2 = new School(school_name, schoolType);
        school2.setSchoolID(1);
        assertNotEquals(school1, school2);
        assertNotEquals(school1.hashCode(), school2.hashCode());
    }

    @Test
    void nameNotEqualEqualsTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school1 = new School(school_name, schoolType);
        School school2 = new School(school_name, schoolType);
        school2.setSchoolName("Gold HS");
        assertNotEquals(school1, school2);
        assertNotEquals(school1.hashCode(), school2.hashCode());
    }

    @Test
    void typeNotEqualEqualsTest() {
        String school_name = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school1 = new School(school_name, schoolType);
        School school2 = new School(school_name, schoolType);
        school2.setSchoolType(SchoolType.KINDER_GARDEN);
        assertNotEquals(school1, school2);
        assertNotEquals(school1.hashCode(), school2.hashCode());
    }
}

