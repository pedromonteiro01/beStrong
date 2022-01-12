package com.beStrong.beStrong_server.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beStrong.beStrong_server.model.Client;
import com.beStrong.beStrong_server.model.Trainer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer>{
    Optional<Client> findByEmail(String email);
    Page<Client> findByTrainer(Trainer trainer, Pageable pageable);
    Optional<Client> findByUsername(String username);
    Optional<Client> findById(Integer id);
}
