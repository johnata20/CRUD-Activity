package com.example.poc.pocproject;

import com.example.poc.PocApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface POCRepository extends JpaRepository<POCProject , Long> {

    @Query("SELECT j FROM POCProject j WHERE j.email = ?1")
    Optional<POCProject> findPOCProjectByEmail(String email);
}
