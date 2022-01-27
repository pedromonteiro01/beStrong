package com.beStrong.beStrong_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beStrong.beStrong_server.model.FitnessType;

import java.util.List;
import java.util.Optional;


@Repository
public interface FitnessTypeRepository extends JpaRepository<FitnessType, Long>{
    List<FitnessType> findByName(String name);
    Optional<FitnessType> findById(long id);
}
