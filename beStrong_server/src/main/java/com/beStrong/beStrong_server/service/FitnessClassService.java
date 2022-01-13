package com.beStrong.beStrong_server.service;

import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.repository.FitnessClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessClassService{

    @Autowired
    private FitnessClassRepository fitnessClassRepository;
    @Autowired
    private ClientService clientService;

    public FitnessClass saveFitnessClass(FitnessClass fitnessClass) {
        return fitnessClassRepository.save(fitnessClass);
    }

    public List<FitnessClass> saveFitnessClasses(List<FitnessClass> fitnessClasses) {
        return fitnessClassRepository.saveAll(fitnessClasses);
    }

    public FitnessClass getFitnessClassById(int id) {
        return fitnessClassRepository.findById(id).orElse(null);
    }

    public List<FitnessClass> getFitnessClassesByType(String type) {
        return fitnessClassRepository.findByType(type);
    }

    public List<FitnessClass> getFitnessClasses() {
        return fitnessClassRepository.findAll();
    }

    public String removeFitnessClass(int id) {
        fitnessClassRepository.deleteById(id);
        return "Fitness Class removed ( id: " + id + " )";
    }

    public String makeReservation(int class_id, int client_id) {

        FitnessClass fitnessClass = this.getFitnessClassById(class_id);

        if (fitnessClass == null)
            return "No fitness class with ID " + class_id;

        if (fitnessClass.getCurrentCapacity() >= fitnessClass.getMaxCapacity())
            return "Fitness class with ID " + class_id + " is full";

        Client client = this.clientService.getClientById(client_id);

        if (client == null)
            return  "No client with ID " + client_id;

        fitnessClass.addClient(client);
        client.addReservation(fitnessClass);

        fitnessClassRepository.save(fitnessClass);

        return "Reservation made for class " + class_id + " by client " + client_id;
    }


}
