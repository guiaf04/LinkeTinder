package com.linketinder.controller

import com.linketinder.dto.MatchDTO
import com.linketinder.dto.UnmatchedCandidateDTO
import com.linketinder.model.Match
import com.linketinder.service.MatchService
import spock.lang.Specification
import spock.lang.Subject

class MatchControllerTest extends Specification {

    MatchService matchService = Mock()
    @Subject
    MatchController matchController = new MatchController(matchService)

    def "should like candidate"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 1L)

        when:
        def response = matchController.likeCandidate(matchDTO)

        then:
        1 * matchService.candidateLike(matchDTO)
        response == "Candidate liked"
    }

    def "should like employee"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 1L)

        when:
        def response = matchController.likeEmployee(matchDTO)

        then:
        1 * matchService.employeeLike(matchDTO)
        response == "Employee liked"
    }

    def "should find match"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 1L)
        Match match = new Match(id: 1L, candidateLiked: true, employerLiked: true, match: true)
        matchService.findMatch(matchDTO) >> match

        when:
        def result = matchController.findMatch(matchDTO)

        then:
        result == match
    }

    def "should find possible matches"() {
        given:
        Long employeeId = 1L
        List<UnmatchedCandidateDTO> candidates = [new UnmatchedCandidateDTO(id: 1L, country: "Brazil", cep: "12345", personalDescription: "Experienced", skills: [])]
        matchService.findPotentialMatches(employeeId) >> candidates

        when:
        def result = matchController.findPossibleMatches(employeeId)

        then:
        result == candidates
    }

    def "should create match"() {
        given:
        MatchDTO matchDTO = new MatchDTO(candidateId: 1L, jobId: 1L, employeeId: 1L)

        when:
        def response = matchController.createMatch(matchDTO)

        then:
        1 * matchService.createMatch(matchDTO)
        response == "Match created"
    }
}