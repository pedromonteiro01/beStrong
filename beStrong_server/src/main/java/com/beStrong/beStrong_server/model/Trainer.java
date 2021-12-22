package com.beStrong.beStrong_server.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@Table(name = "trainers")
public class Trainer extends User {

    // classes assigned to trainer
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<FitnessClass> fitnessClasses;

    //clients assigned to trainer
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = Client.class)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Client> clients;

    public Trainer(){
    }

    public Trainer(String email, String username, String password, String phone_number) {
        super(email, username, password, phone_number);
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setEmail(String email) {
        super.setEmail(email);;
    }

    public String getName() {
        return super.getName();
    }

    public void setUsername(String full_name) {
        super.setName(full_name);;
    }

    public String getPhone() {
        return super.getPhone();
    }

    public void setPhone(String phone_number) {
        super.setPhone(phone_number);
    }

    public String getPassword() {
        return super.getPassword();
    }

    public void setPassword(String password) {
        super.setPassword(password);;
    }

}

