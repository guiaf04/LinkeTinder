package controller

import model.Candidato
import service.CandidateService

class CandidateController {
    CandidateService candidateService

    void showUsers(){
        candidateService.listCandidates()
    }

    boolean addCandidate(Candidato candidato){
        if (!candidateService.addCandidate(candidato)) {
            println("Não foi possível adicionar o candidato")
            return false
        }

        println("Candidato inserido com sucesso!")
        return true
    }
}
