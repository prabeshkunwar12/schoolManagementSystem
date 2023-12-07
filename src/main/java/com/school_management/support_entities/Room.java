package com.school_management.support_entities;
import java.util.Objects;

public class Room {
    int roomID;
    RoomType roomType;
    int studentCapacity;
    Schedule schedule;


    public Room() {
    }

    public Room(int roomID, RoomType roomType, int studentCapacity, Schedule schedule) {
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

    public Schedule getSchedule() {
        return this.schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public Room roomID(int roomID) {
        setRoomID(roomID);
        return this;
    }

    public Room roomType(RoomType roomType) {
        setRoomType(roomType);
        return this;
    }

    public Room studentCapacity(int studentCapacity) {
        setStudentCapacity(studentCapacity);
        return this;
    }

    public Room schedule(Schedule schedule) {
        setSchedule(schedule);
        return this;
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
