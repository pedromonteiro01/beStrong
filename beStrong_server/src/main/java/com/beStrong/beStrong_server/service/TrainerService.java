package com.beStrong.beStrong_server.service;

import com.beStrong.beStrong_server.model.Trainer;
import com.beStrong.beStrong_server.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    public Trainer saveTrainer(Trainer trainer) {
        return trainerRepository.save(trainer);
    }

    public List<Trainer> saveTrainers(List<Trainer> trainers) {
        return trainerRepository.saveAll(trainers);
    }

    public List<Trainer> getTrainers() {
        return trainerRepository.findAll();
    }

    public Trainer getTrainerById(Integer id) {
        return trainerRepository.findById(id).orElse(null);
    }

    public Trainer getTrainerByName(String name) {
        return trainerRepository.findByUsername(name).orElse(null);
    }

    public String deleteTrainer(int id) {
        trainerRepository.deleteById(id);
        return "trainer removed !! " + id;
    }
}
