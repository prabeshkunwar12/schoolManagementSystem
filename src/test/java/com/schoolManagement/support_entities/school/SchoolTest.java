package com.schoolManagement.support_entities.school;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.school_management.support_entities.school.School;
import com.school_management.support_entities.school.SchoolType;

class SchoolTest {
    // Testing the default constructor of the School class
    @Test
    void testSchoolDefaultConstructor() {
        School school = new School();
        assertNotNull(school);
    }

    // Testing various valid scenarios for the School constructor
    @Test
    void validSchoolConstructorTest() {

        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.KINDER_GARDEN;
        School school = new School(schoolName, schoolType);
        assertNotNull(school);

        school = new School("D", SchoolType.PRIMARY);
        assertNotNull(school);

        school = new School("Sjdfnnnn HS", SchoolType.SECONDARY);
        assertNotNull(school);

        school = new School("e", SchoolType.HIGH);
        assertNotNull(school);

        school = new School("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh", SchoolType.COLLEGE);
        assertNotNull(school);

        school = new School("aaa", SchoolType.UNIVERSITY);
        assertNotNull(school);
    }

    // Testing invalid scenarios for the School constructor
    @Test
    void testInvalidSchoolConstructorTestSchool() {
        // school name is null
        assertThrows(IllegalArgumentException.class, ()->{School school = new School(null, SchoolType.PRIMARY);});
        // school type is null
        assertThrows(IllegalArgumentException.class, ()->{School school = new School("Diamond HS", null);});
        // school name is an empty string
        assertThrows(IllegalArgumentException.class, ()->{School school = new School("", SchoolType.HIGH);});
        // school name is too long >100
        assertThrows(IllegalArgumentException.class, ()->{School school = new School("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasasasasasasasasasasasasasaasasa", SchoolType.HIGH);});

    }

    // Testing the getter and setter for the school ID
    @Test
    void testSchoolIDGetterAndSetter() {
        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(schoolName, schoolType);
        assertEquals(0, school.getSchoolID());

        school.setSchoolID(100);
        assertEquals(100, school.getSchoolID());
    }

    // Testing valid scenarios for the School name getter and setter
    @Test
    void testValidSchoolNameGetterAndSetter() {
        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(schoolName, schoolType);
        assertEquals("Diamond HS", school.getSchoolName());

        school.setSchoolName("Gold HS");
        assertEquals("Gold HS", school.getSchoolName());

        school.setSchoolName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaa");
        assertEquals("aaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaaaaaaaaaaaaaaaaa", school.getSchoolName());
    }

    // Testing invalid scenarios for the School name getter and setter
    @Test
    void testInvalidSchoolNameGetterAndSetter() {
        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(schoolName, schoolType);
        
        //school name is null
        assertThrows(IllegalArgumentException.class, ()->{school.setSchoolName(null);});
        // school name is an empty string
        assertThrows(IllegalArgumentException.class, ()->{school.setSchoolName("");});
        // school name is too long >100
        assertThrows(IllegalArgumentException.class, ()->{school.setSchoolName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaasasasasasasasasasasasasasaasasa");});

    }

    // Testing valid scenarios for the School type getter and setter
    @Test
    void testValidSchoolTypeGetterAndSetter() {
        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.KINDER_GARDEN;
        School school = new School(schoolName, schoolType);
        assertEquals(SchoolType.KINDER_GARDEN, school.getSchoolType());

        school.setSchoolType(SchoolType.PRIMARY);
        assertEquals(SchoolType.PRIMARY, school.getSchoolType());

        school.setSchoolType(SchoolType.SECONDARY);
        assertEquals(SchoolType.SECONDARY, school.getSchoolType());

        school.setSchoolType(SchoolType.HIGH);
        assertEquals(SchoolType.HIGH, school.getSchoolType());

        school.setSchoolType(SchoolType.COLLEGE);
        assertEquals(SchoolType.COLLEGE, school.getSchoolType());

        school.setSchoolType(SchoolType.SECONDARY);
        assertEquals(SchoolType.SECONDARY, school.getSchoolType());
    }

    // Testing invalid scenarios for the School type getter and setter
    @Test
    void testInvalidSchoolTypeGetterAndSetter() {
        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school = new School(schoolName, schoolType);

        assertThrows(IllegalArgumentException.class, ()->{school.setSchoolType(null);});
    }

    // Testing valid scenarios for the equals() and hashCode() methods
    @Test
    void testValidEqualsAndHashCode() {
        String schoolName = "Diamond HS";
        SchoolType schoolType = SchoolType.HIGH;
        School school1 = new School(schoolName, schoolType);
        School school2 = new School(schoolName, schoolType);

        assertEquals(school1, school2);
        assertEquals(school1.hashCode(), school2.hashCode());

        schoolName = "Gold HS";
        school1.setSchoolName(schoolName);
        school2.setSchoolName(schoolName);

        assertEquals(school1, school2);
        assertEquals(school1.hashCode(), school2.hashCode());

        schoolType = SchoolType.COLLEGE;
        school1.setSchoolType(schoolType);
        school2.setSchoolType(schoolType);

        assertEquals(school1, school2);
        assertEquals(school1.hashCode(), school2.hashCode());
    }

    // Testing invalid scenarios for the equals() and hashCode() methods
    @Test
    void testInvalidEqualsAndHashCode() {
        String[] schoolNames = {"Diamond HS", "Gold College"};
        SchoolType[] schoolTypes = {SchoolType.HIGH, SchoolType.COLLEGE};

        for (String name1 : schoolNames) {
            for (String name2 : schoolNames) {
                for (SchoolType type1 : schoolTypes) {
                    for (SchoolType type2 : schoolTypes) {
                        if (!name1.equals(name2) || type1 != type2) {
                            School school1 = new School(name1, type1);
                            School school2 = new School(name2, type2);

                            assertNotEquals(school1, school2);
                            assertNotEquals(school1.hashCode(), school2.hashCode());
                        }
                    }
                }
            }
        }
    }
}

