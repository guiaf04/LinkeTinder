package com.linketinder.repository;

import com.linketinder.model.Candidate;
import com.linketinder.model.CandidateSkill;
import com.linketinder.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
  Optional<Candidate> findByCandidate(Candidate candidate);

  List<CandidateSkill> findByCandidateId(Long candidateId);

  @Query("""
    SELECT s FROM Skill s
    JOIN CandidateSkill cs ON s.id = cs.skill.id
    WHERE cs.candidate.id = :candidateId
""")
  List<Skill> findAllSkillByCandidateId(@Param("candidateId") Long candidateId);

  Optional<CandidateSkill> findByCandidateAndSkill(Candidate candidate, Skill skill);
}
