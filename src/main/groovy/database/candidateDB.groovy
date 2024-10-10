package database

import database.interfaces.PostgresCRUDTables
import database.sample.PostgresCRUDSample
import database.sample.PostgresJDBCSample

class candidateDB implements PostgresCRUDTables{
    PostgresCRUDSample jdbcCRUDSample = new PostgresCRUDSample(new PostgresJDBCSample())

    def criar(List<String> values) {
        List<String> fields =
                List.of("nome", "sobrenome", "data_nascimento",
                "email", "cpf", "pais", "cep",
                "descricao_pessoal", "senha")

        jdbcCRUDSample.create(fields, values, "candidato")
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
