package com.beStrong.beStrong_server.model;

import java.util.Set;

import java.sql.Date;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "Class")
public class Class {

    private int id;
    private String type;
    private Date start_date;
    private Date ending_date; 
    private String local;
    private int capacity;


     //trainer assigned to class
     @OneToOne(mappedBy = "class", cascade = CascadeType.ALL, orphanRemoval = true)
     @ToString.Exclude
     @EqualsAndHashCode.Exclude
     @JsonIgnore
     private Trainer trainer;

     //clients assigned to class
     @OneToMany(mappedBy = "class", cascade = CascadeType.ALL, orphanRemoval = true)
     @ToString.Exclude
     @EqualsAndHashCode.Exclude
     @JsonIgnore
     private Set<Client> clients;

    public Class() {
    }

    public Class(String type, Date start_date, Date ending_date, String local) {
        this.type = type;
        this.start_date = start_date;
        this.ending_date = ending_date;
        this.local = local;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "type", nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "start_date", nullable = false)
    public Date getStarting() {
        return start_date;
    }

    public void setStarting(Date start) {
        this.start_date = start;
    }

    @Column(name = "ending_date", nullable = false)
    public Date getEnding() {
        return ending_date;
    }

    public void setEnding(Date ending) {
        this.ending_date = ending;
    }

    @Column(name = "local", nullable = false)
    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Column(name = "capacity", nullable = false)
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
