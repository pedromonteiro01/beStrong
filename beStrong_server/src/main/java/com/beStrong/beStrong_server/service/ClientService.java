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

    public Client saveClient(Client Client) {
        return clientRepository.save(Client);
    }

    public List<Client> saveClientes(List<Client> Clientes) {
        return clientRepository.saveAll(Clientes);
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
}
