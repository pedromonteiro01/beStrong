package com.beStrong.beStrong_server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.beStrong.beStrong_server.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.*;
import com.beStrong.beStrong_server.repository.*;

@RestController
@CrossOrigin
@RequestMapping("/trainers")
public class TrainerController {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private TrainerService trainerService;

    @GetMapping
    @ResponseBody
    public List<Trainer> getAllTrainers(HttpServletRequest request) {
        return trainerService.getTrainers();
    }
}
