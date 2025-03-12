package com.linketinder.service


import com.linketinder.dto.MatchDTO
import com.linketinder.dto.UnmatchedCandidateDTO
import com.linketinder.exception.EntityNotFound
import com.linketinder.exception.UnauthorizedOperation
import com.linketinder.model.Candidate
import com.linketinder.model.Job
import com.linketinder.model.Match
import com.linketinder.repository.CandidateRepository
import com.linketinder.repository.CandidateSkillRepository
import com.linketinder.repository.JobRepository
import com.linketinder.repository.MatchRepository
import org.springframework.stereotype.Service

import static com.linketinder.mapper.ObjectMapper.parseListObject

@Service
class MatchService {
    final CandidateRepository candidateRepository
    final JobRepository jobRepository
    final MatchRepository matchRepository
    final CandidateSkillRepository candidateSkillRepository

    MatchService(
            CandidateRepository candidateRepository,
            JobRepository jobRepository,
            MatchRepository matchRepository,
            CandidateSkillRepository candidateSkillRepository
    ) {
        this.candidateRepository = candidateRepository
        this.jobRepository = jobRepository
        this.matchRepository = matchRepository
        this.candidateSkillRepository = candidateSkillRepository
    }

    Match createMatch(MatchDTO matchDTO){
        Candidate candidate = candidateRepository
                .findById(matchDTO.candidateId)
                .orElseThrow {
                    new EntityNotFound("Candidate not found") }
        Job job = jobRepository.findById(matchDTO.jobId).orElseThrow { new EntityNotFound("Job not found") }

        Match match = new Match(candidate: candidate, job: job)

        return matchRepository.save(match)
    }

    Match findMatch(MatchDTO matchDTO){
        return matchRepository
                .findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId)
                .orElseThrow {
                    new EntityNotFound("Match not found")
                }
    }

    void candidateLike(MatchDTO matchDTO){
        Match match = matchRepository
                .findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId)
                .orElseThrow {
                    new EntityNotFound("Match not found")
                }

        match.candidateLiked = true
    }

    void employeeLike(MatchDTO matchDTO){
        Job job = jobRepository.findById(matchDTO.jobId).orElseThrow { new EntityNotFound("Job not found") }

        if (job.idEmployee != matchDTO.employeeId){
            throw new UnauthorizedOperation("Employee can't like this job")
        }

        Match match = matchRepository
                .findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId)
                .orElseThrow {
                    new EntityNotFound("Match not found")
                }

        match.employerLiked = true

        if(match.candidateLiked){
            match.match = true
        }
    }

    List<UnmatchedCandidateDTO> findPotentialMatches(Long employeeId){
        List<Job> jobs = jobRepository.findAllByIdEmployee(employeeId)
        List<Candidate> candidates = matchRepository.findAllCandidateByJobIdIn(jobs.collect { it.id })

        List<UnmatchedCandidateDTO> candidateDTOS = parseListObject(candidates, UnmatchedCandidateDTO.class)
        candidateDTOS.each {candidate -> candidate.skills = candidateSkillRepository.findAllSkillByCandidateId(candidate.id)}

        return candidateDTOS
    }
}
