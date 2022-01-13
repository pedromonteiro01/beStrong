package com.beStrong.beStrong_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beStrong.beStrong_server.model.FitnessClass;
import com.beStrong.beStrong_server.model.Trainer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Repository
public interface FitnessClassRepository extends JpaRepository<FitnessClass, Integer>{
    Page<FitnessClass> findByTrainer(Trainer trainer, Pageable pageable);
    List<FitnessClass> findByType(String type);
    Optional<FitnessClass> findById(int id);
}
