package com.beStrong.beStrong_server.model;

import com.fasterxml.jackson.annotation.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import java.sql.Date;

import javax.persistence.*;


@Entity
@Table(name = "fitnessclasses")
public class FitnessClass {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "class_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "4"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
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

    @Column(name = "max_capacity", nullable = false)
    private int max_capacity;

     //trainer assigned to class
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn( referencedColumnName = "id", name="trainer_id", nullable=false)
     @JsonIncludeProperties("id")
     private Trainer trainer;

     //clients assigned to class
     @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
     @JoinTable(
             name = "fitness_class_reservations",
             joinColumns = @JoinColumn(name = "fitness_class_id", referencedColumnName = "id"),
             inverseJoinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id")
     )
     @ToString.Exclude
     @EqualsAndHashCode.Exclude
     @JsonIgnore
     private Set<Client> clients = new HashSet<>();

    public FitnessClass() {
    }

    public FitnessClass(Trainer trainer, String type, Date date, Time start_hour, Time ending_hour, String local, int max_capacity) {
        this.trainer = trainer;
        this.type = type;
        this.date = date;
        this.start_hour = start_hour;
        this.ending_hour = ending_hour;
        this.local = local;
        this.max_capacity = max_capacity;
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

    public int getMaxCapacity() {
        return max_capacity;
    }

    public void setMaxCapacity(int capacity) {
        this.max_capacity = capacity;
    }

    public int getCurrentCapacity() {
        return clients.size();
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        this.clients.add(client);
    }

    public void removeClient(Client client) {
        this.clients.remove(client);
    }

    public String toString(){
        return this.id + "  " + this.local + "   " + this.type + "  " + this.date + "   " + this.start_hour + " " + this.ending_hour + "    " + this.max_capacity;
    }
}
