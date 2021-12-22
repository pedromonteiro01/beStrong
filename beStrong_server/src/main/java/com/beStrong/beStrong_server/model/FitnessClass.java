package com.beStrong.beStrong_server.model;

import java.sql.Time;
import java.util.Set;

import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name = "FitnessClass")
public class FitnessClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "date", nullable = false)
    private Date date;

    @Column(name = "start_hour", nullable = false)
    private Time start_hour;

    @Column(name = "ending_hour", nullable = false)
    private Time ending_hour;

    @Column(name = "local", nullable = false)
    private String local;

    @Column(name = "capacity", nullable = false)
    private int capacity;

     //trainer assigned to class
     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn( referencedColumnName = "id", name="trainer_id", nullable=false)
     private Trainer trainer;

     //clients assigned to class
     @ManyToMany(mappedBy = "fitnessClasses")
     private Set<Client> clients;

    public FitnessClass() {
    }

    public FitnessClass(Trainer trainer, String type, Date date, Time start_hour, Time ending_hour, String local) {
        this.trainer = trainer;
        this.type = type;
        this.date = date;
        this.start_hour = start_hour;
        this.ending_hour = ending_hour;
        this.local = local;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStarting() {
        return start_hour;
    }

    public void setStarting(Time start) {
        this.start_hour = start;
    }

    public Time getEnding() {
        return ending_hour;
    }

    public void setEnding(Time ending) {
        this.ending_hour = ending;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setTrainer(Trainer t){
        this.trainer=t;
    }

    public Trainer getTrainer(){
        return this.trainer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
