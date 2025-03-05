package com.linketinder.controller

import com.linketinder.model.Candidato
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
    List<Candidato> findAll()  {
        return candidateService.listCandidates()
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    ResponseEntity<Candidato> createCandidate(@RequestBody Candidato candidato){
        return ResponseEntity.status(HttpStatus.CREATED).body(candidateService.addCandidate(candidato))
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    Candidato editCandidate(@RequestBody Candidato candidato){
        return candidateService.editCandidate(candidato)
    }

    @DeleteMapping(value = "/{cpf}")
    ResponseEntity<?> deleteCandidate(@PathVariable("cpf") String cpf){
        candidateService.deleteCandidate(cpf)
        return ResponseEntity.noContent().build()
    }
}
