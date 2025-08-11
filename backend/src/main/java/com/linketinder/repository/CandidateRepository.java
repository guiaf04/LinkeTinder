package com.linketinder.repository;

import com.linketinder.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Long> {
    Candidate getElementByCpf(String cpf);
}
