package com.beStrong.beStrong_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.beStrong.beStrong_server.model.Class;
import com.beStrong.beStrong_server.model.Trainer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;


@Repository
public interface ClassRepository extends JpaRepository<Class, Long>{
    Page<Class> findByTrainer(Trainer trainer, Pageable pageable);
    Optional<Class> findById(int id);
}
