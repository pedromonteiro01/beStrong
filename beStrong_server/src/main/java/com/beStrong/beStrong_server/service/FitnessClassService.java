package com.beStrong.beStrong_server.service;

import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.repository.FitnessClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessClassService{

    @Autowired
    private FitnessClassRepository fitnessClassRepository;

    public FitnessClass saveFitnessClass(FitnessClass fitnessClass) {
        return fitnessClassRepository.save(fitnessClass);
    }

    public List<FitnessClass> saveFitnessClasses(List<FitnessClass> fitnessClasses) {
        return fitnessClassRepository.saveAll(fitnessClasses);
    }

    public FitnessClass getFitnessClassById(long id) {
        return fitnessClassRepository.findById(id).orElse(null);
    }

    public List<FitnessClass> getFitnessClasses() {
        return fitnessClassRepository.findAll();
    }

    public String removeFitnessClass(long id) {
        fitnessClassRepository.deleteById(id);
        return "Fitness Class removed ( id: " + id + " )";
    }
}
