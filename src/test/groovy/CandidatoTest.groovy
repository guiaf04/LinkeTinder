import controller.CandidateController
import dao.CandidateDAO
import model.Candidato
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import service.CandidateService
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
        boolean success = candidateController.addCandidate(candidato1)

        assertTrue(success)
    }

    @Test
    void createDuplicateCandidateTest() {
        boolean success = candidateController.addCandidate(candidato2)

        assertFalse(success)
    }

}
