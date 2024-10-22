package controller

import model.Vaga
import service.VacancyService

class VacancyController {
    VacancyService vacancyService

    List<String> showVacancies(){
        return vacancyService.listVacancys()
    }

    boolean addVacancy(Vaga vaga){
        return vacancyService.addVacancy(vaga)
    }

    boolean editVacancy(Vaga vaga){
        return vacancyService.editVacancy(vaga)
    }

    boolean deleteVacancy(Vaga vaga){
        return vacancyService.deleteVacancy(vaga)
    }
}
