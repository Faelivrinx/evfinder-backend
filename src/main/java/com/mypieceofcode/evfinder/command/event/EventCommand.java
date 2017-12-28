package com.mypieceofcode.evfinder.command.event;


import java.util.ArrayList;
import java.util.List;

public class EventCommand {

    private Long id;
    private String name;
    private String address;
    private long date;
    private String description;
    private String profile;
    private double longitude;
    private double latitude;
    private List<UserAttendCommand> users = new ArrayList<>();

    private List<CommentCommand> commentCommands = new ArrayList<>();

    public List<UserAttendCommand> getUsers() {
        return users;
    }

    public void setUsers(List<UserAttendCommand> users) {
        this.users = users;
    }

    public List<CommentCommand> getCommentCommands() {
        return commentCommands;
    }

    public void setCommentCommands(List<CommentCommand> commentCommands) {
        this.commentCommands = commentCommands;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
