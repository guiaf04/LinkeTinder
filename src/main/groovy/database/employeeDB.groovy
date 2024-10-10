package database

import database.interfaces.PostgresCRUDTables
import database.sample.PostgresCRUDSample
import database.sample.PostgresJDBCSample

class employeeDB implements PostgresCRUDTables {
    PostgresCRUDSample jdbcCRUDSample = new PostgresCRUDSample(new PostgresJDBCSample())

    def criar(List<String> values) {
        List<String> fields =
                List.of("nome", "email", "cnpj",
                        "pais", "cep", "descricao", "senha")

        jdbcCRUDSample.create(fields, values, "empresa")
    }

    def listar() {
        jdbcCRUDSample.read("empresa")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "empresa")
    }

    def deletar(int id) {
        jdbcCRUDSample.delete(id, "empresa")
    }
}
