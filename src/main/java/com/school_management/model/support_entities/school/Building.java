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
package com.school_management.model.support_entities.school;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "Building")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "building_id")
    private int buildingID;
    
    @Column(name = "building_name")
    private String buildingName;
    
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    private Logger logger = LoggerFactory.getLogger(Building.class);

    //default constructor for JPA compliance
    public Building() {}

    /**
     * Constructor to initialize a Building with a given ID and name.
     *
     * @param buildingID The unique identifier for the building.
     * @param buildingName       The name of the building.
     */
    public Building(String buildingName, School school) {
        setBuildingName(buildingName);
        setSchool(school);
        logger.info("New building {} initialized", getBuildingID());
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

    public String getBuildingName() {
        return this.buildingName;
    }

    /**
     * Sets the name of the building.
     *
     * @param name The new name for the building.
     */
    public void setBuildingName(String buildingName) {
        if(buildingName == null) {
            logger.error("buildingName is null", new IllegalArgumentException());
            throw new IllegalArgumentException("buildingName cannot be null");
        }
        if(buildingName.length() < 1 || buildingName.length() > 100) {
            logger.error("buildingName should be between 1 and 100 characters.", new IllegalArgumentException());
            throw new IllegalArgumentException("buildingName is too short(<1) or too long(>100)");
        }
        this.buildingName = buildingName;
        logger.info("buildingName for {} has been modified to {}", getBuildingID(), getBuildingName());
    }

    public School getSchool() {
        return this.school;
    }

    public void setSchool(School school) {
        if(school == null) {
            logger.error("school is null", new IllegalArgumentException());
            throw new IllegalArgumentException("school cannot be null");
        }
        this.school = school;
        logger.info("school for {} has been modified to {}", getBuildingName(), getSchool().getSchoolName());
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
        return buildingID == building.buildingID && Objects.equals(buildingName, building.buildingName) && Objects.equals(school, building.school);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buildingID, buildingName, school);
    }

    @Override
    public String toString() {
        return "{" +
            " buildingID='" + getBuildingID() + "'" +
            ", buildingName='" + getBuildingName() + "'" +
            ", school='" + getSchool() + "'" +
            "}";
    }
    
}
