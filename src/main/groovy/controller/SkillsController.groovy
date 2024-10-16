package controller

import model.Competencia
import service.SkillsService

class SkillsController {
    SkillsService skillsService

    void showSkills(){
        println skillsService.listSkills().join("\n")
    }

    boolean addSkills(Competencia competencias){
        if (!skillsService.addSkills(competencias)) {
            println("Não foi possível adicionar o competencias")
            return false
        }

        println("Competencias inserido com sucesso!")
        return true
    }

    boolean editSkills(Competencia competencias){
        if (!skillsService.editSkills(competencias)) {
            println("Não foi possível editar o competencias")
            return false
        }

        println("Competencias editado com sucesso!")
        return true
    }

    boolean deleteSkills(Competencia competencias){
        if (!skillsService.deleteSkills(competencias)) {
            println("Não foi possível deletar o competencias")
            return false
        }

        println("Competencias deletado com sucesso!")
        return true
    }
}
