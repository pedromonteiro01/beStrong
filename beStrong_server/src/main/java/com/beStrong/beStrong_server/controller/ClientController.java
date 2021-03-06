package com.beStrong.beStrong_server.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.exception.UnprocessableEntityException;
import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.service.ClientService;

@RestController
@CrossOrigin
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    @ResponseBody
    public List<Client> getAllClients(HttpServletRequest request) {
        return clientService.getClients();
    }

    @PostMapping( produces = "application/json")
    public Client createClient(@Valid @RequestBody Client client, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        Optional<Client> comp = clientService.checkClientByEmail(client.getEmail());
        if (comp.isPresent()) {
            throw new UnprocessableEntityException("Email already exists");
        }
        comp = clientService.checkClientByUsername(client.getName());
        if (comp.isPresent()) {
            throw new UnprocessableEntityException("Username already exists");
        }
        return clientService.saveClient(client);
    }
}