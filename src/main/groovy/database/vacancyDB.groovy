package database

import database.interfaces.PostgresCRUDTables
import database.sample.PostgresCRUDSample
import database.sample.PostgresJDBCSample

class vacancyDB implements PostgresCRUDTables {
    PostgresCRUDSample jdbcCRUDSample = new PostgresCRUDSample(new PostgresJDBCSample())

    def criar(List<String> values) {
        List<String> fields =
                List.of("nome", "descricao", "local", "id_empresa")

        jdbcCRUDSample.create(fields, values, "vaga")
    }

    def listar() {
        jdbcCRUDSample.read("vaga")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "vaga")
    }

    def deletar(int id) {
        jdbcCRUDSample.delete(id, "vaga")
    }
}
