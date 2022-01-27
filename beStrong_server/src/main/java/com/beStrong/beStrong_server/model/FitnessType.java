package com.beStrong.beStrong_server.model;

import javax.persistence.*;


@Entity
@Table(name = "fitnesstypes")
public class FitnessType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "goals", nullable = false)
    private String goals;

    @Column(name = "images", nullable = false)
    private String images;

    @Column(name = "intensity", nullable = false)
    private String intensity;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "duration", nullable = false)
    private String duration;

    public FitnessType(){
    }

    public FitnessType(String name, String description, String goals, String intensity, String type, String duration, String images) {
        this.name=name;
        this.description=description;
        this.goals=goals;
        this.intensity = intensity;
        this.type= type;
        this.duration=duration;
        this.images=images;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGoals(String goals){
        this.goals=goals;
    }

    public String getGoals(){
        return goals;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration (String duration) {
        this.duration = duration;
    }
}

