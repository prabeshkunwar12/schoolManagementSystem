/**
 * The {@code Building} class represents a physical structure within a school or educational institution.
 * It encapsulates information about the building's unique identifier, name, and the list of rooms it contains.
 * 
 * This class provides methods to manage and retrieve information about the building,
 * such as modifying the building ID, changing the building's name, adding rooms to the building,
 * and retrieving an unmodifiable view of the list of rooms.
 * 
 * Usage:
 * Building building = new Building(buildingID, name);
 * building.addRoom(room); // Adding a room to the building
 * 
 * @see Room
 */
package com.school_management.support_entities.school;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Building {
    private int buildingID;
    private String name;
    private final School school;

    private Logger logger = LoggerFactory.getLogger(Building.class);

    /**
     * Constructor to initialize a Building with a given ID and name.
     *
     * @param buildingID The unique identifier for the building.
     * @param name       The name of the building.
     */
    public Building(String name, School school) {
        if(name==null || school==null){
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.school = school;
        logger.info("New building initialized");
    }

    public int getBuildingID() {
        return this.buildingID;
    }

    /**
     * Sets the building ID to a new value.
     *
     * @param buildingID The new identifier for the building.
     */
    public void setBuildingID(int buildingID) {
        this.buildingID = buildingID;
        logger.info("Building ID modified");
    }

    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the building.
     *
     * @param name The new name for the building.
     */
    public void setName(String name) {
        this.name = name;
        logger.info("Building name changed");
    }

    public School getSchool() {
        return this.school;
    }

    // Equals, hashCode, and toString methods are overridden
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Building)) {
            return false;
        }
        Building building = (Building) o;
        return buildingID == building.buildingID && Objects.equals(name, building.name) && Objects.equals(school, building.school);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingID, name, school);
    }

    @Override
    public String toString() {
        return "{" +
            " buildingID='" + getBuildingID() + "'" +
            ", name='" + getName() + "'" +
            ", school='" + getSchool() + "'" +
            "}";
    }
    
}
