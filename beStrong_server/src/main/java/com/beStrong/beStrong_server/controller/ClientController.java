package com.beStrong.beStrong_server.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.beStrong.beStrong_server.exception.UnprocessableEntityException;
import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.Trainer;
import com.beStrong.beStrong_server.model.User;
import com.beStrong.beStrong_server.service.ClientService;
import com.beStrong.beStrong_server.service.TrainerService;
import com.beStrong.beStrong_server.repository.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    @ResponseBody
    public List<Client> getAllClients(HttpServletRequest request) {
        return clientService.getClients();
    }

    @PostMapping
    public Client createClient(@Valid @RequestBody Client client, HttpServletRequest request) throws UnprocessableEntityException, Exception{
        try{
            Optional<Client> u1 = clientRepository.findByEmail(client.getEmail());
            Optional<Client> u2 = clientRepository.findByUsername(client.getName());
            if(u2.isPresent()){
                throw new UnprocessableEntityException("Username already exists");
            }
            else if(u1.isPresent()){
                throw new UnprocessableEntityException("Email already exists");
            }
            client.setPassword(client.getPassword());
            List<Client> lst = clientService.getClients();
            client.setId(lst.get(lst.size()-1).getId()+1);
            while (true){
                try{
                    clientService.saveClient(client);
                    break;
                }
                catch (Exception e){
                    client.setId(client.getId()+1);
                }
            }
            System.out.println("final " + client.getId());
            return client;
        }
        catch (Exception e) {
            throw e;
        }
        
    }
}