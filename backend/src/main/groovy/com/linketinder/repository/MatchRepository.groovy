package com.linketinder.repository

import com.linketinder.model.Candidate
import com.linketinder.model.Match
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MatchRepository extends JpaRepository<Match, Long> {
    Optional<Match> findByCandidateIdAndJobId(Long candidateId, Long jobId)
    @Query("""
    SELECT c FROM Candidate c
    JOIN Match m ON c.id = m.candidate.id
    WHERE m.job.id IN :jobIds
""")
    List<Candidate> findAllCandidateByJobIdIn(@Param("jobIds") List<Long> jobIds)

    @Query("""
    SELECT COUNT(m) > 0 FROM Match m
    WHERE m.candidate.id = :candidateId
    AND m.job.idEmployee = :employeeId
    AND m.match = true
""")
    boolean existsByCandidateIdAndEmployeeId(@Param("candidateId") Long candidateId, @Param("employeeId") Long employeeId);

}
