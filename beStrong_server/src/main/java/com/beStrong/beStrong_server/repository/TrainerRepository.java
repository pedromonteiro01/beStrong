package com.beStrong.beStrong_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.beStrong.beStrong_server.model.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
    Optional<Trainer> findById(long id);
    Optional<Trainer> findByUsername(String full_name);
    Optional<Trainer> findByEmail(String email);
}