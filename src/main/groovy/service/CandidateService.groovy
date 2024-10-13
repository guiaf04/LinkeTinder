package service

import model.Candidato
import model.dao.CandidateDAO
import model.dao.interfaces.ISampleDAO

class CandidateService {
    ISampleDAO candidateDAO

    boolean addCandidate(Candidato candidato){
        List<String> values = candidato.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>
//        values.push("teste")
//        values.push("teste")
//        values[2] = "01/01/1999"
        boolean success = candidateDAO.addCandidate(values)

        return success
    }

    void listCandidates(){
        candidateDAO.listar()
    }
}
