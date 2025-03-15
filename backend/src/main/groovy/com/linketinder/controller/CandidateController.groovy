package com.linketinder.controller

import com.linketinder.dto.CandidateDTO
import com.linketinder.dto.UnmatchedCandidateDTO
import com.linketinder.model.Candidate
import com.linketinder.service.CandidateService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/candidates")
class CandidateController {

    CandidateService candidateService

    CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<UnmatchedCandidateDTO> findAll(@RequestBody(required = false) Long employeeId) {
        if (employeeId == null) {
            employeeId = 0L
        }
        return candidateService.listCandidates(employeeId)
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<CandidateDTO> createCandidate(@RequestBody Candidate candidate){
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.addCandidate(candidate))
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    CandidateDTO editCandidate(@RequestBody Candidate candidate){
        return candidateService.editCandidate(candidate)
    }

    @DeleteMapping(value = "/{cpf}")
    ResponseEntity<?> deleteCandidate(@PathVariable("cpf") String cpf){
        candidateService.deleteCandidate(cpf)
        return ResponseEntity.noContent().build()
    }
}
