package com.linketinder.controller

import com.linketinder.dto.CandidateDTO
import com.linketinder.model.Candidate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.linketinder.service.CandidateService

@RestController
@RequestMapping("/candidates")
class CandidateController {

    @Autowired
    CandidateService candidateService

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<CandidateDTO> findAll()  {
        return candidateService.listCandidates()
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
