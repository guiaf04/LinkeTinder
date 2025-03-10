package com.linketinder.repository

import com.linketinder.model.Candidate
import com.linketinder.model.CandidateSkill
import com.linketinder.model.Skill
import org.springframework.data.jpa.repository.JpaRepository

interface CandidateSkillRepository extends JpaRepository<CandidateSkill, Long> {
    Optional<Candidate> findByCandidate(Candidate candidate)
    List<CandidateSkill> findByCandidateId(Long candidateId)
    List<Skill> findAllSkillByCandidateId(Long candidateId)
    Optional<CandidateSkill> findByCandidateAndSkill(Candidate candidate, Skill skill)
}
