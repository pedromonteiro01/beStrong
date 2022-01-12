package com.beStrong.beStrong_server.service;

import com.beStrong.beStrong_server.model.FitnessType;
import com.beStrong.beStrong_server.repository.FitnessTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessTypeService{

    @Autowired
    private FitnessTypeRepository fitnessTypeRepository;

    public FitnessType saveFitnessClass(FitnessType fitnessType) {
        return fitnessTypeRepository.save(fitnessType);
    }

    public List<FitnessType> saveFitnessClasses(List<FitnessType> fitnessTypes) {
        return fitnessTypeRepository.saveAll(fitnessTypes);
    }

    public FitnessType getFitnessClassById(long id) {
        return fitnessTypeRepository.findById(id).orElse(null);
    }

    public List<FitnessType> getFitnessTypes() {
        return fitnessTypeRepository.findAll();
    }

    public List<FitnessType> getFitnessTypeByName(String name) {
        return fitnessTypeRepository.findByName(name);
    }

    public String removeFitnessType(long id) {
        fitnessTypeRepository.deleteById(id);
        return "Fitness Class removed ( id: " + id + " )";
    }
}
