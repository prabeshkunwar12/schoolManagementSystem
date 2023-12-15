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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Building {
    private int buildingID;
    private String name;
    private List<Room> roomList;
    
    private Logger logger = LoggerFactory.getLogger(Building.class);

    /**
     * Constructor to initialize a Building with a given ID and name.
     *
     * @param buildingID The unique identifier for the building.
     * @param name       The name of the building.
     */
    public Building(int buildingID, String name) {
        this.buildingID = buildingID;
        this.name = name;
        this.roomList = new ArrayList<>();
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

    public List<Room> getRoomList() {
        return Collections.unmodifiableList(this.roomList);
    }

    /**
     * Adds a room to the list of rooms in the building.
     *
     * @param room The room to be added (must not be null).
     * @return True if the room is successfully added; otherwise, false.
     * @throws IllegalArgumentException if the room parameter is null.
     */
    public boolean addRoom(Room room){
        if(room == null) {
            logger.error("Room cannot be null.", new IllegalArgumentException());
            return false;
        } else {
            roomList.add(room);
            logger.info("Room added to the list");
            return true;
        }
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
        return buildingID == building.buildingID && Objects.equals(name, building.name) && Objects.equals(roomList, building.roomList) && Objects.equals(logger, building.logger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingID, name, roomList, logger);
    }

    @Override
    public String toString() {
        return "{" +
            " buildingID='" + getBuildingID() + "'" +
            ", name='" + getName() + "'" +
            ", roomList='" + getRoomList() + "'" +
            "}";
    }
    
}
