package com.linketinder.service

import com.linketinder.model.Competencia
import com.linketinder.dao.SkillsDAO

class SkillsService {
    SkillsDAO skillsDAO

    boolean addSkills(Competencia competencia){
        if (skillsDAO.getElementByName(competencia) != ""){
            println("Esse usuário já está cadastrado, tente usar informações diferentes para cadastrar um novo usuário")
            return false
        }

        return skillsDAO.criar(competencia)
    }

    List<Competencia> listSkills(){
        return skillsDAO.listar()
    }

    @SuppressWarnings('GroovyMissingReturnStatement')
    boolean editSkills(Competencia competencia){
        if (skillsDAO.getElementByName(competencia) == ""){
            println "Esse usuário não está cadastrado, então não é possível editá-lo"
            return false
        }

        return skillsDAO.atualizar(competencia)
    }

    boolean deleteSkills(Competencia competencia){
        if (skillsDAO.getElementByName(competencia) == ""){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        return skillsDAO.deletar(competencia)
    }
}
