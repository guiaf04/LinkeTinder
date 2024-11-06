package service

import model.Candidato
import dao.CandidateDAO
import model.Competencia

import java.sql.SQLException

class CandidateService {
    CandidateDAO candidateDAO

    boolean addCandidate(Candidato candidato){
        if (candidateDAO.getElementByCPF(candidato) != ""){
            println("Esse usuário já está cadastrado, tente usar informações diferentes para cadastrar um novo usuário")
            return false
        }

        return candidateDAO.criar(candidato)
    }

    List<Candidato> listCandidates(){
        return candidateDAO.listar()
    }

    boolean addSkills(List<Competencia> competencias, int candidatoID){
        try {
            competencias.forEach {
                candidateDAO.insertSkill((Competencia) it, candidatoID)
            }
            return true
        }catch (SQLException e){
            return false
        }
    }

    boolean editCandidate(Candidato candidato){
        if (candidateDAO.getElementByCPF(candidato) == ""){
            println "Esse usuário não está cadastrado, então não é possível editá-lo"
            return false
        }

        return candidateDAO.atualizar(candidato)
    }

    boolean deleteCandidate(Candidato candidato){
        if (candidateDAO.getElementByCPF(candidato) == ""){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        return candidateDAO.deletar(candidato)
    }
}
