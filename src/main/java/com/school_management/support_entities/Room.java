package com.school_management.support_entities;
import java.util.Objects;

import com.school_management.support_entities.schedule.RoomSchedule;

public class Room {
    int roomID;
    RoomType roomType;
    int studentCapacity;
    RoomSchedule schedule;


    public Room() {
    }

    public Room(int roomID, RoomType roomType, int studentCapacity, RoomSchedule schedule) {
        this.roomID = roomID;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return roomID == room.roomID && Objects.equals(roomType, room.roomType) && studentCapacity == room.studentCapacity && Objects.equals(schedule, room.schedule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID, roomType, studentCapacity, schedule);
    }

    @Override
    public String toString() {
        return "{" +
            " roomID='" + getRoomID() + "'" +
            ", roomType='" + getRoomType() + "'" +
            ", studentCapacity='" + getStudentCapacity() + "'" +
            ", schedule='" + getSchedule() + "'" +
            "}";
    }
    
}
