package controller

import model.Competencia
import service.SkillsService

class SkillsController {
    SkillsService skillsService

    List<String> showSkills(){
        return skillsService.listSkills()
    }

    boolean addSkills(Competencia competencias){
        return skillsService.addSkills(competencias)
    }

    boolean editSkills(Competencia competencias){
        return skillsService.editSkills(competencias)
    }

    boolean deleteSkills(Competencia competencias){
        return skillsService.deleteSkills(competencias)
    }
}
