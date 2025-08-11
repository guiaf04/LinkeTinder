package com.linketinder.repository;

import com.linketinder.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    public abstract Candidate getElementByCpf(String cpf);
}
