package com.mypieceofcode.evfinder.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String name;
    private String address;
    private String description;
    private long date;
    private double latitude;
    private double longitude;
    private String profile;
    private double correlation;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "event_user", joinColumns = {@JoinColumn(name = "eventId")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<>();


}
