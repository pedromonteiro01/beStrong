package com.beStrong.beStrong_server.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(generator = "sequence-generator")
    @GenericGenerator(
            name = "sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "1001"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Integer id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password; //String or hash (depending on implementation might be better to use hash

    @Column(name = "phone", nullable = false)
    private String phone;

    public User() {
    }

    public User(String email, String full_name, String password, String phone_number) {
        this.email = email;
        this.username = full_name;
        this.password = password;
        this.phone = phone_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return username;
    }

    public void setName(String full_name) {
        this.username = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone_number) {
        this.phone = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
