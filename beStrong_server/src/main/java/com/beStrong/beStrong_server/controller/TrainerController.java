package com.beStrong.beStrong_server.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.*;
import com.beStrong.beStrong_server.model.Class;
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

    public TrainerController(ClientRepository clientRepository, UserRepository userRepository, ClassRepository traineRepository){
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
