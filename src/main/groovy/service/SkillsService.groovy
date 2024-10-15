package service

import model.Competencia
import model.dao.SkillsDAO
import model.dao.interfaces.ISampleDAO

class SkillsService {
    ISampleDAO skillsDAO = new SkillsDAO()

    private static String getID(String skills){
        def matcher = (skills =~ /id:(\d+)/)
        matcher.find()
        return matcher.group(1)
    }

    boolean addSkills(Competencia competencia){
        if (skillsDAO.getElementByCPF(competencia.getCpf()) != null){
            println("Esse usuário já está cadastrado, tente usar informações diferentes para cadastrar um novo usuário")
            return false
        }

        return skillsDAO.criar(competencia)
    }

    List<String> listSkillss(){
        return skillsDAO.listar()
    }

    @SuppressWarnings('GroovyMissingReturnStatement')
    boolean editSkills(Competencia competencia){
        String olderSkills = skillsDAO.getElementByCPF(competencia.getCpf())

        if (olderSkills == null){
            println "Esse usuário não está cadastrado, então não é possível editá-lo"
            return false
        }

        int id = getID(olderSkills).toInteger()

        List<String> values = new ArrayList<>()
        List<String> fields = new ArrayList<>()

        // Converter a string em um mapa, ignorando o campo 'id'
        List<String> campos = olderSkills.replaceAll(/[\[|\]]/, '').split(', ')

        Map<String, String> novoCompetenciaMap = campos.collectEntries { campo ->
            def (chave, valor) = campo.split(':')
            [(chave): valor]
        }.findAll { chave, _ -> chave != 'id' } // Remover o 'id' do mapa

        // Comparar e encontrar valores alterados
        novoCompetenciaMap.each { chave, valorAtual ->
            String propriedade = chave.replace('_', '') // Remover underscore para corresponder aos nomes das propriedades
            String novoValor = competencia."$propriedade"

            if (valorAtual.toString() != novoValor.toString()) {
                fields.add(chave)
                values.add(novoValor)
            }
        }

        if (fields.isEmpty()) {
            println "Nenhuma alteração foi encontrada."
            return false
        }

        println "Campos a serem atualizados: $fields"
        println "Novos valores: $values"

        return skillsDAO.atualizar(fields, values, id)
    }

    boolean deleteSkills(Competencia competencia){
        String skills = skillsDAO.getElementByCPF(competencia.getCpf())

        if (skills == null){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        int id = getID(skills).toInteger()

        return skillsDAO.deletar(id)
    }
}
