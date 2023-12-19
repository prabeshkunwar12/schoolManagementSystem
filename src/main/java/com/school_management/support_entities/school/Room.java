package com.school_management.support_entities.school;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.school_management.support_entities.schedule.CourseSectionSchedule;
import com.school_management.support_entities.schedule.RoomSchedule;

public class Room {
    private int roomID;
    private Building building;
    private RoomType roomType;
    private int studentCapacity;
    private RoomSchedule schedule;

    private Logger logger = LoggerFactory.getLogger(Room.class);

    public Room(Building building, RoomType roomType, int studentCapacity, RoomSchedule schedule) {
        if(building==null || roomType==null || schedule==null) {
            logger.error("parameters cannot be null", new IllegalArgumentException());
            throw new IllegalArgumentException();
        }
        this.building = building;
        this.roomType = roomType;
        this.studentCapacity = studentCapacity;
        this.schedule = schedule;
    }

    public int getRoomID() {
        return this.roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public Building getBuilding() {
        return this.building;
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getStudentCapacity() {
        return this.studentCapacity;
    }

    public void setStudentCapacity(int studentCapacity) {
        this.studentCapacity = studentCapacity;
    }

    public RoomSchedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(RoomSchedule schedule) {
        this.schedule = schedule;
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
