package service

import model.Vaga
import model.dao.VacancyDAO
import model.dao.interfaces.ISampleDAO

class VacancyService {
    ISampleDAO vacancyDAO = new VacancyDAO()

    private static String getID(String vacancy){
        def matcher = (vacancy =~ /id:(\d+)/)
        matcher.find()
        return matcher.group(1)
    }

    boolean addVacancy(Vaga vaga){
        if (vacancyDAO.getElementByIdEmpresaAndName(vaga.getNome(), vaga.getIdempresa()) != null){
            println("Essa vaga já está cadastrado, tente usar informações diferentes para cadastrar uma nova vaga")
            return false
        }
        return true
//        return vacancyDAO.criar(vaga)
    }

    List<String> listVacancys(){
        return vacancyDAO.listar()
    }

    boolean editVacancy(Vaga vaga){
        String olderVacancy = vacancyDAO.getElementByIdEmpresaAndName(vaga.getNome(), vaga.getIdempresa())

        if (olderVacancy == null){
            println "Essa vaga não está cadastrado, então não é possível editá-la"
            return false
        }

        int id = getID(olderVacancy).toInteger()

        List<String> values = new ArrayList<>()
        List<String> fields = new ArrayList<>()

        // Converter a string em um mapa, ignorando o campo 'id'
        List<String> campos = olderVacancy.replaceAll(/[\[|\]]/, '').split(', ')

        Map<String, String> novoVagaMap = campos.collectEntries { campo ->
            def (chave, valor) = campo.split(':')
            [(chave): valor]
        }.findAll { chave, _ -> chave != 'id' } // Remover o 'id' do mapa

        // Comparar e encontrar valores alterados
        novoVagaMap.each { chave, valorAtual ->
            String propriedade = chave.replace('_', '') // Remover underscore para corresponder aos nomes das propriedades
            String novoValor = vaga."$propriedade"

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

        return vacancyDAO.atualizar(fields, values, id)
    }

    boolean deleteVacancy(Vaga vaga){
        String vacancy = vacancyDAO.getElementByIdEmpresaAndName(vaga.getNome(), vaga.getIdempresa())

        if (vacancy == null){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        int id = getID(vacancy).toInteger()

        return vacancyDAO.deletar(id)
    }
}
