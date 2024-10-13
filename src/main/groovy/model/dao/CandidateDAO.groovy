package model.dao

import model.Candidato
import model.dao.interfaces.ISampleDAO
import model.dao.sample.PostgreSampleDAO

class CandidateDAO implements ISampleDAO<Candidato>{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO()

    boolean criar(Candidato candidato) {
        List<String> fields =
                List.of("nome", "sobrenome", "data_nascimento",
                "email", "cpf", "pais", "cep",
                "descricao_pessoal", "senha")

        List<String> values = candidato.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>


        return jdbcCRUDSample.create(fields, values, "candidato")
    }

    List<String> listar() {
        return jdbcCRUDSample.read("candidato")
    }

    String getElementByCPF(String cpf) {
        assert cpf != null

        List<String> result = jdbcCRUDSample.readGeneric("candidato", "cpf", cpf)

        if(result.size() == 0){
            return null
        }


        return result.first
    }

    boolean atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "candidato")
    }

    boolean deletar(int id) {
        jdbcCRUDSample.delete(id, "candidato")
    }
}
