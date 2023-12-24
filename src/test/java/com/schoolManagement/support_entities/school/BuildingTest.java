package com.schoolManagement.support_entities.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.school_management.model.support_entities.school.Building;
import com.school_management.model.support_entities.school.School;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuildingTest {

    @Mock
    private School mockSchool;

    private Building building;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        building = new Building("Building A", mockSchool);
    }

    @Test
    void testValidConstructor() {
        Building building1 = new Building("Building 1", mockSchool);
        assertNotNull(building1);
        assertEquals("Building 1", building1.getBuildingName());
        assertEquals(mockSchool, building1.getSchool());
    }

    @Test
    void testInvalidConstructor() {
        //building name null
        assertThrows(IllegalArgumentException.class, () -> new Building(null, mockSchool));
        //building name empty
        assertThrows(IllegalArgumentException.class, () -> new Building("", mockSchool));
        //building name is too long >100 characters
        assertThrows(IllegalArgumentException.class, () -> new Building("aaaaaaaaaaaaaaaaaaaaaaaaasasasaasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasasas", mockSchool));
        //school is null
        assertThrows(IllegalArgumentException.class, () -> new Building("Building A", null));
    }

    @Test
    void testValidSetterAndGetterForBuildingID() {
        assertEquals(0, building.getBuildingID()); // Assuming default value is 0
        building.setBuildingID(5);
        assertEquals(5, building.getBuildingID());
    }

    @Test
    void testValidSetterAndGetterForBuildingName() {
        assertEquals("Building A", building.getBuildingName());
        building.setBuildingName("New Building Name");
        assertEquals("New Building Name", building.getBuildingName());
    }

    @Test
    void testInvalidSetterAndGetterForBuildingName() {       
        //building name null
        assertThrows(IllegalArgumentException.class, () -> {building.setBuildingName(null);});
        //building name empty
        assertThrows(IllegalArgumentException.class, () -> {building.setBuildingName("");});
        //building name is to long 
        assertThrows(IllegalArgumentException.class, () -> {building.setBuildingName("aaaaaaaaaaaaaaaaaaaaaaaaaasdfsddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");});
    }

    @Test
    void testValidSetterAndGetterForSchool() {
        School newMockSchool = mock(School.class);
        building.setSchool(newMockSchool);
        assertEquals(newMockSchool, building.getSchool());
    }

    @Test
    void testInvalidSetterAndGetterForSchool() {
        //school is null
        assertThrows(IllegalArgumentException.class, () -> {building.setSchool(null);});
    }

    @Test
    void testValidEqualsAndHashCode() {
        Building building1 = new Building("Building A", mockSchool);
        Building building2 = new Building("Building A", mockSchool);
        assertEquals(building1, building2);
        assertEquals(building1.hashCode(), building2.hashCode());
    }

    @Test
    void testInvalidEqualsAndHashCode() {
        String[] buildingNames = {"building A", "building B"};
        School[] schools = {mock(School.class), mock(School.class)};
        
        for(String buildingName1: buildingNames) {
            for(String buildingName2: buildingNames) {
                for(School school1: schools) {
                    for(School school2: schools) {
                        if(!(buildingName1.equals(buildingName2) || school1.equals(school2))) {
                            assertNotEquals(new Building(buildingName1, school1), new Building(buildingName2, school2));
                        }
                    }
                }
            }
        }
    }

    @Test
    void testToString() {
        String expectedString = "{ buildingID='0', buildingName='Building A', school='" + mockSchool + "'}";
        assertEquals(expectedString, building.toString());
    }
}

