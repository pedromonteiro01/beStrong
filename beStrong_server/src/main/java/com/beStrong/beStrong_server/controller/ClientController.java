package com.beStrong.beStrong_server.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.User;
import com.beStrong.beStrong_server.service.ClientService;
import com.beStrong.beStrong_server.repository.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;
    private UserRepository userRepository;

    @GetMapping
    @ResponseBody
    public List<Client> getAllClients(HttpServletRequest request) {
        return clientService.getClients();
    }
}
