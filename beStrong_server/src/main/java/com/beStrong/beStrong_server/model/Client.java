package com.beStrong.beStrong_server.model;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@Table(name = "clients")
public class Client extends User {

    @Column(name = "weight")
    private double weight;

    @Column(name = "height") 
    private double height;

    //trainer assigned to client
    @ManyToOne(targetEntity = Trainer.class)
    @JoinColumn(name = "trainer_id")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Trainer trainer;

    @ManyToMany( mappedBy = "clients")
    Set<FitnessClass> fitnessClasses = new HashSet<>();

    public Client(){

    }

    public Client(String email, String username, String password, String phone_number, double weight, double height) {
        super(email, username, password, phone_number);
        this.weight = weight;
        this.height = height;
    }

    public void setWeight(Double weight){
        this.weight=weight;
    } 

    public double getWeight(){
        return this.weight;
    }

    public double getHeight(){
        return this.height;
    }

    public void setHeight(Double height){
        this.height=height;
    } 

    public void setTrainer(Trainer t){
        this.trainer=t;
    }

    public Trainer getTrainer(){
        return this.trainer;
    }

    public Set<FitnessClass> getFitnessClasses() {
        return fitnessClasses;
    }

    public void setFitnessClasses(Set<FitnessClass> fitnessClasses) {
        this.fitnessClasses = fitnessClasses;
    }

    public void addReservation(FitnessClass fitnessClass) {
        this.fitnessClasses.add(fitnessClass);
    }
}

