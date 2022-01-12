package com.beStrong.beStrong_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beStrong.beStrong_server.model.FitnessType;
import com.beStrong.beStrong_server.model.Trainer;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Repository
public interface FitnessTypeRepository extends JpaRepository<FitnessType, Long>{
    List<FitnessType> findByName(String name);
    Optional<FitnessType> findById(long id);
}
