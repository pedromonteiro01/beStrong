package com.beStrong.beStrong_server.controller;

import java.util.List;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.repository.*;

@RestController
@RequestMapping("/classes")
public class ClassController {
    
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FitnessClassRepository fitnessClassRepository;

    public ClassController(ClientRepository clientRepository, UserRepository userRepository, FitnessClassRepository fitnessClassRepository){
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
        this.fitnessClassRepository = fitnessClassRepository;
    }

    @GetMapping
    @ResponseBody
    public List<FitnessClass> getAllClasses(HttpServletRequest request) {

        Principal principal = request.getUserPrincipal();
        
        return fitnessClassRepository.findAll();
    }
}
