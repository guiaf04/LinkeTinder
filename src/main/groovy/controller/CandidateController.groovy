package controller

import model.Candidato
import service.CandidateService

class CandidateController {
    CandidateService candidateService

    void showUsers(){
        println candidateService.listCandidates().join("\n")
    }

    boolean addCandidate(Candidato candidato){
        if (!candidateService.addCandidate(candidato)) {
            println("Não foi possível adicionar o candidato")
            return false
        }

        println("Candidato inserido com sucesso!")
        return true
    }

    boolean editCandidate(Candidato candidato){
        if (!candidateService.editCandidate(candidato)) {
            println("Não foi possível editar o candidato")
            return false
        }

        println("Candidato editado com sucesso!")
        return true
    }

    boolean deleteCandidate(Candidato candidato){
        if (!candidateService.deleteCandidate(candidato)) {
            println("Não foi possível deletar o candidato")
            return false
        }

        println("Candidato deletado com sucesso!")
        return true
    }
}
