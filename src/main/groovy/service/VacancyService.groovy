package service

import model.Vaga
import dao.VacancyDAO

class VacancyService {
    VacancyDAO vacancyDAO

    boolean addVacancy(Vaga vaga){
        if (vacancyDAO.getElementByIdEmpresaAndName(vaga) != ""){
            return false
        }

        return vacancyDAO.criar(vaga)
    }

    List<Vaga> listVacancys(){
        return vacancyDAO.listar()
    }

    boolean editVacancy(Vaga vaga){
        if (vacancyDAO.getElementByIdEmpresaAndName(vaga) == ""){
            return false
        }

        return vacancyDAO.atualizar(vaga)
    }

    boolean deleteVacancy(Vaga vaga){
        if (vacancyDAO.getElementByIdEmpresaAndName(vaga) == ""){
            return false
        }

        return vacancyDAO.deletar(vaga)
    }
}
