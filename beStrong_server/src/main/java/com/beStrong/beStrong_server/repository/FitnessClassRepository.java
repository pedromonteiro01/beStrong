package com.beStrong.beStrong_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Repository
public interface FitnessClassRepository extends JpaRepository<FitnessClass, Integer>{
    List<FitnessClass> findByTrainer(Trainer trainer);
    List<FitnessClass> findByTrainerAndType(Trainer trainer, String type);
    List<FitnessClass> findByType(String type);
    Optional<FitnessClass> findById(int id);
}
