package service

import model.Competencia
import dao.SkillsDAO
import dao.interfaces.ISampleDAO

class SkillsService {
    ISampleDAO skillsDAO = new SkillsDAO()

    private static String getID(String skills){
        def matcher = (skills =~ /id:(\d+)/)
        matcher.find()
        return matcher.group(1)
    }

    boolean addSkills(Competencia competencia){
        if (skillsDAO.getElementByName(competencia.getNome()) != null){
            println("Esse usuário já está cadastrado, tente usar informações diferentes para cadastrar um novo usuário")
            return false
        }

        return skillsDAO.criar(competencia)
    }

    List<String> listSkills(){
        return skillsDAO.listar()
    }

    @SuppressWarnings('GroovyMissingReturnStatement')
    boolean editSkills(Competencia competencia){
        String olderSkills = skillsDAO.getElementByName(competencia.getNome())

        if (olderSkills == null){
            println "Essa competencia não está cadastrada, então não é possível editá-la"
            return false
        }

        if (olderSkills.equalsIgnoreCase(competencia.getNome())){
            println("Já existe uma competencia com esse nome, então não é possível atualizar")
            return false
        }

        int id = getID(olderSkills).toInteger()

        List<String> values = new ArrayList<>()
        List<String> fields = new ArrayList<>()

        values.add(competencia.getNome())


        return skillsDAO.atualizar(fields, values, id)
    }

    boolean deleteSkills(Competencia competencia){
        String skills = skillsDAO.getElementByName(competencia.getNome())

        if (skills == null){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        int id = getID(skills).toInteger()

        return skillsDAO.deletar(id)
    }
}
