package controller

import model.Candidato
import service.CandidateService

class CandidateController {
    CandidateService candidateService

    List<String> showUsers(){
        return candidateService.listCandidates()
    }

    boolean addCandidate(Candidato candidato){
        return candidateService.addCandidate(candidato)
    }

    boolean editCandidate(Candidato candidato){
        return candidateService.editCandidate(candidato)
    }

    boolean deleteCandidate(Candidato candidato){
        return candidateService.deleteCandidate(candidato)
    }
}
