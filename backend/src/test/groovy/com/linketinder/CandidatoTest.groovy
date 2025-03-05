package com.linketinder

import com.linketinder.controller.CandidateController
import com.linketinder.dao.CandidateDAO
import com.linketinder.model.Candidato
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import com.linketinder.service.CandidateService
import static org.junit.jupiter.api.Assertions.*

import static org.mockito.Mockito.*

class CandidatoTest {
    CandidateDAO candidateDAO = mock(CandidateDAO)
    CandidateService candidateService = new CandidateService(candidateDAO: candidateDAO)
    CandidateController candidateController = new CandidateController(candidateService: candidateService)

    Candidato candidato1 = new Candidato()
    Candidato candidato2 = new Candidato()


    @BeforeEach
    void configTests(){
        when(candidateDAO.getElementByCPF(candidato1)).thenReturn("")
        when(candidateDAO.getElementByCPF(candidato2)).thenReturn(".....")
        when(candidateDAO.criar(any(Candidato.class))).thenReturn(true)
    }

    @Test
    void createUniqueCandidateTest() {
        boolean success = candidateService.addCandidate(candidato1)

        assertTrue(success)
    }

    @Test
    void createDuplicateCandidateTest() {
        boolean success = candidateService.addCandidate(candidato2)

        assertFalse(success)
    }

}
