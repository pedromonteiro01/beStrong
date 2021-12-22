package com.beStrong.beStrong_server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.*;
import com.beStrong.beStrong_server.repository.*;

@RestController
@RequestMapping("/trainer")
public class TrainerController {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public TrainerController(ClientRepository clientRepository, UserRepository userRepository, FitnessClassRepository traineRepository){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.trainerRepository = trainerRepository;
    }

    @GetMapping
    @ResponseBody
    public List<Trainer> getAllTrainers(HttpServletRequest request) {
            return trainerRepository.findAll();
    }
}
