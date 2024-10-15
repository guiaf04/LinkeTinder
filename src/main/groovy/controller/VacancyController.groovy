package controller

import model.Vaga
import service.VacancyService

class VacancyController {
    VacancyService vacancyService

    void showVacancies(){
        println vacancyService.listVacancys().join("\n")
    }

    boolean addVacancy(Vaga vaga){
        if (!vacancyService.addVacancy(vaga)) {
            println("Não foi possível adicionar o vaga")
            return false
        }

        println("Vaga inserido com sucesso!")
        return true
    }

    boolean editVacancy(Vaga vaga){
        if (!vacancyService.editVacancy(vaga)) {
            println("Não foi possível editar o vaga")
            return false
        }

        println("Vaga editado com sucesso!")
        return true
    }

    boolean deleteVacancy(Vaga vaga){
        if (!vacancyService.deleteVacancy(vaga)) {
            println("Não foi possível deletar o vaga")
            return false
        }

        println("Vaga deletado com sucesso!")
        return true
    }
}
