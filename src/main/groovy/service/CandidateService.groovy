package service

import model.Candidato
import model.dao.CandidateDAO
import model.dao.interfaces.ISampleDAO

class CandidateService {
    ISampleDAO candidateDAO = new CandidateDAO()

    private static String getID(String candidate){
        def matcher = (candidate =~ /id:(\d+)/)
        matcher.find()
        return matcher.group(1)
    }

    boolean addCandidate(Candidato candidato){
        if (candidateDAO.getElementByCPF(candidato.getCpf()) != null){
            println("Esse usuário já está cadastrado, tente usar informações diferentes para cadastrar um novo usuário")
            return false
        }

        return candidateDAO.criar(candidato)
    }

    List<String> listCandidates(){
        return candidateDAO.listar()
    }

    boolean editCandidate(Candidato candidato){
        String olderCandidate = candidateDAO.getElementByCPF(candidato.getCpf())

        if (olderCandidate == null){
            println "Esse usuário não está cadastrado, então não é possível editá-lo"
            return false
        }

        int id = getID(olderCandidate).toInteger()

        List<String> values = new ArrayList<>()
        List<String> fields = new ArrayList<>()

        // Converter a string em um mapa, ignorando o campo 'id'
        List<String> campos = olderCandidate.replaceAll(/[\[|\]]/, '').split(', ')

        Map<String, String> novoCandidatoMap = campos.collectEntries { campo ->
            def (chave, valor) = campo.split(':')
            [(chave): valor]
        }.findAll { chave, _ -> chave != 'id' } // Remover o 'id' do mapa

        // Comparar e encontrar valores alterados
        novoCandidatoMap.each { chave, valorAtual ->
            String propriedade = chave.replace('_', '') // Remover underscore para corresponder aos nomes das propriedades
            String novoValor = candidato."$propriedade"

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

        return candidateDAO.atualizar(fields, values, id)
    }

    boolean deleteCandidate(Candidato candidato){
        String candidate = candidateDAO.getElementByCPF(candidato.getCpf())

        if (candidate == null){
            println("Esse usuário não está cadastrado, tente novamente")
            return false
        }

        int id = getID(candidate).toInteger()

        return candidateDAO.deletar(id)
    }
}
