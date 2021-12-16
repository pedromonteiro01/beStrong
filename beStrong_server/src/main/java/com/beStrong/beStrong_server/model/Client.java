package com.beStrong.beStrong_server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@DiscriminatorValue("Client")
public class Client extends User {

    @Column(name = "weight")
    private double weight;

    @Column(name = "height") 
    private double height;

    //trainer assigned to client
    @ManyToOne
    @JoinColumn(name = "trainer_id")
    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Trainer trainer;

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
}

