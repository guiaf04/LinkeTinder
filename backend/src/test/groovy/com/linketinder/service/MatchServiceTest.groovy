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
import spock.lang.Specification

class MatchServiceTest extends Specification {
    final CandidateRepository candidateRepository = Mock()
    final JobRepository jobRepository = Mock()
    final MatchRepository matchRepository = Mock()
    final CandidateSkillRepository candidateSkillRepository = Mock()

    MatchService matchService = new MatchService(candidateRepository, jobRepository, matchRepository, candidateSkillRepository)

    def "should create a match"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        Candidate candidate = new Candidate(id: 1L)
        Job job = new Job(id: 1L)
        Match match = new Match(candidate: candidate, job: job)
        candidateRepository.findById(matchDTO.candidateId) >> Optional.of(candidate)
        jobRepository.findById(matchDTO.jobId) >> Optional.of(job)
        matchRepository.save(_) >> match

        when:
        def result = matchService.createMatch(matchDTO)

        then:
        result == match
    }

    def "should throw exception when creating match with non-existent candidate"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        candidateRepository.findById(matchDTO.candidateId) >> Optional.empty()

        when:
        matchService.createMatch(matchDTO)

        then:
        thrown(EntityNotFound)
    }

    def "should throw exception when creating match with non-existent job"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        Candidate candidate = new Candidate(id: 1L)
        candidateRepository.findById(matchDTO.candidateId) >> Optional.of(candidate)
        jobRepository.findById(matchDTO.jobId) >> Optional.empty()

        when:
        matchService.createMatch(matchDTO)

        then:
        thrown(EntityNotFound)
    }

    def "should find a match"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        Match match = new Match(candidate: new Candidate(id: 1L), job: new Job(id: 1L))
        matchRepository.findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId) >> Optional.of(match)

        when:
        def result = matchService.findMatch(matchDTO)

        then:
        result == match
    }

    def "should throw exception when match not found"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        matchRepository.findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId) >> Optional.empty()

        when:
        matchService.findMatch(matchDTO)

        then:
        thrown(EntityNotFound)
    }

    def "should like candidate"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        Match match = new Match(candidate: new Candidate(id: 1L), job: new Job(id: 1L))
        matchRepository.findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId) >> Optional.of(match)

        when:
        matchService.candidateLike(matchDTO)

        then:
        match.candidateLiked
    }

    def "should throw exception when liking candidate and match not found"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L)
        matchRepository.findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId) >> Optional.empty()

        when:
        matchService.candidateLike(matchDTO)

        then:
        thrown(EntityNotFound)
    }

    def "should like employee"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 1L)
        Job job = new Job(id: 1L, idEmployee: 1L)
        Match match = new Match(candidate: new Candidate(id: 1L), job: job)
        jobRepository.findById(matchDTO.jobId) >> Optional.of(job)
        matchRepository.findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId) >> Optional.of(match)

        when:
        matchService.employeeLike(matchDTO)

        then:
        match.employerLiked
    }

    def "should throw exception when employee can't like job"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 2L)
        Job job = new Job(id: 1L, idEmployee: 1L)
        jobRepository.findById(matchDTO.jobId) >> Optional.of(job)

        when:
        matchService.employeeLike(matchDTO)

        then:
        thrown(UnauthorizedOperation)
    }

    def "should throw exception when liking employee and match not found"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 1L)
        Job job = new Job(id: 1L, idEmployee: 1L)
        jobRepository.findById(matchDTO.jobId) >> Optional.of(job)
        matchRepository.findByCandidateIdAndJobId(matchDTO.candidateId, matchDTO.jobId) >> Optional.empty()

        when:
        matchService.employeeLike(matchDTO)

        then:
        thrown(EntityNotFound)
    }

    def "should find potential matches"() {
        given:
        Long employeeId = 1L
        Job job = new Job(id: 1L, idEmployee: employeeId)
        Candidate candidate = new Candidate(id: 1L)
        UnmatchedCandidateDTO unmatchedCandidateDTO = new UnmatchedCandidateDTO(id: 1L, skills: [])
        jobRepository.findAllByIdEmployee(employeeId) >> [job]
        matchRepository.findAllCandidateByJobIdIn([job.id]) >> [candidate]
        candidateSkillRepository.findAllSkillByCandidateId(candidate.id) >> []

        when:
        def result = matchService.findPotentialMatches(employeeId)

        then:
        result == [unmatchedCandidateDTO]
    }

}
