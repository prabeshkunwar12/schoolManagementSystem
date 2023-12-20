package com.school_management.support_entities.school;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.schedule.RoomSchedule;

public class Room {
    private int roomID;
    private String roomName;
    private Building building;
    private RoomType roomType;
    private int studentCapacity;
    private RoomSchedule schedule;

    private Logger logger = LoggerFactory.getLogger(Room.class);

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
        logger.info("A new room in building {} is initialized", building.getName());
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
