package com.school_management.model.support_entities.school;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.model.support_entities.schedule.CourseSectionSchedule;
import com.school_management.model.support_entities.schedule.RoomSchedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "Room")
public class Room {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomID;
    
    @Column(name = "room_name")
    private String roomName;
    
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    
    @Column(name = "room_type")
    private RoomType roomType;
    
    @Column(name = "student_capacity")
    private int studentCapacity;
    
    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private RoomSchedule schedule;

    private Logger logger = LoggerFactory.getLogger(Room.class);

    //default constructor for JPA compliance
    public Room() {}

    public Room(String roomName, Building building, RoomType roomType, int studentCapacity, RoomSchedule schedule) {
        if(roomName==null || building==null || roomType==null || schedule==null) {
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.roomName = roomName;
        this.building = building;
        this.roomType = roomType;
        this.studentCapacity = studentCapacity;
        this.schedule = schedule;
        logger.info("A new room in building {} is initialized", building.getBuildingName());
    }

    public int getRoomID() {
        return this.roomID;
    }

    public String getRoomName() {
        return this.roomName;
    }

    public void setRoomName(String roomName) {
        if(roomName==null) {
            logger.error("Room name cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.roomName = roomName;
        logger.info("Room name of {} changed to {}", roomID, roomName);
    }

    public Building getBuilding() {
        return this.building;
    }

    public void setBuilding(Building building) {
        if(building == null) {
            logger.error("building is null", new IllegalArgumentException());
            throw new IllegalArgumentException("building cannot be null");
        }
        this.building = building;
        logger.info("building for {} has been modified to {}", getRoomName(), getBuilding().getBuildingName());
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        if(roomType==null) {
            logger.error("Room type cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.roomType = roomType;
        logger.info("Room type of {} changed to {}", roomID, roomType);
    }

    public int getStudentCapacity() {
        return this.studentCapacity;
    }

    public void setStudentCapacity(int studentCapacity) {
        if(studentCapacity<=0) {
            logger.error("student capacity must be higher than 0", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.studentCapacity = studentCapacity;
        logger.info("Room capacity of {} changed to {}", roomID, studentCapacity);
    }

    public RoomSchedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(RoomSchedule schedule) {
        if(schedule==null) {
            logger.error("room schedule cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.schedule = schedule;
        logger.info("Room schedule of {} changed to {}", roomID, schedule);
    }

    public boolean bookRoom(CourseSectionSchedule schedule) {
        return this.schedule.addCourseSectionSchedule(schedule);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return roomID == room.roomID && Objects.equals(roomType, room.roomType) && Objects.equals(building, room.building) && studentCapacity == room.studentCapacity && Objects.equals(schedule, room.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID, roomType, studentCapacity, schedule);
    }

    @Override
    public String toString() {
        return "{" +
            " roomID='" + getRoomID() + "'" +
            " building='" + getBuilding() + "'" +
            ", roomType='" + getRoomType() + "'" +
            ", studentCapacity='" + getStudentCapacity() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
    
}
