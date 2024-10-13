package model.dao

import model.dao.interfaces.ISampleDAO
import model.dao.sample.PostgreSampleDAO
import model.dao.sample.PostgresJDBCSample

class CandidateDAO implements ISampleDAO{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO()

    boolean addCandidate(List<String> values) {
        List<String> fields =
                List.of("nome", "sobrenome", "data_nascimento",
                "email", "cpf", "pais", "cep",
                "descricao_pessoal", "senha")

        boolean success = jdbcCRUDSample.create(fields, values, "candidato")

        return success
    }

    def listar() {
        jdbcCRUDSample.read("candidato")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "candidato")
    }

    def deletar(int id) {
        jdbcCRUDSample.delete(id, "candidato")
    }
}
