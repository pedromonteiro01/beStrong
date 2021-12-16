package com.beStrong.beStrong_server.model;

import java.util.Set;

import com.beStrong.beStrong_server.model.Class;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
@DiscriminatorValue("Trainer")
public class Trainer extends User {

    // classes assigned to trainer
    @Column(name = "classes")
    @ElementCollection(targetClass = Class.class)
    private Set<Class> classes;

    //clients assigned to trainer
    @OneToMany(mappedBy = "trainer", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private Set<Client> clients;

    public Trainer(){
    }

    public Trainer(String email, String username, String password, String phone_number) {
        super(email, username, password, phone_number);
    }

    public void setClass(Class c){
        this.classes.add(c);
    } 

    public void rmClass(Class c){
        this.classes.remove(c);
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

