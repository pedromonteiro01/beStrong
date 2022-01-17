package com.beStrong.beStrong_server.service;

import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> saveClients(List<Client> clients) {
        return clientRepository.saveAll(clients);
    }

    public Client getClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    public Client getClientById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public String removeClient(Integer id) {
        clientRepository.deleteById(id);
        return "Client removed ( id: " + id + " )";
    }

    public Client findClientByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }

    public Client findClientByUsername(String name) {
        return clientRepository.findByUsername(name).orElse(null);
    }
}
