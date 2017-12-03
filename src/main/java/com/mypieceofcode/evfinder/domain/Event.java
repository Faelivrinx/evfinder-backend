package com.mypieceofcode.evfinder.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;

    private String name;
    private String address;

    @Column(length = 99999)
    private String description;

    private long date;
    private double latitude;
    private double longitude;
    private String profile;
    private double correlation;

    // TODO: 03.12.2017 CHANGE LAZY
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "event_user", joinColumns = {@JoinColumn(name = "eventId")}, inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "event")
    private Set<Comment> comments = new HashSet<>();


}
