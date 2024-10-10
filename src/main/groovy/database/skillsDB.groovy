package database

import database.interfaces.PostgresCRUDTables
import database.sample.PostgresCRUDSample
import database.sample.PostgresJDBCSample

class skillsDB implements PostgresCRUDTables {
    PostgresCRUDSample jdbcCRUDSample = new PostgresCRUDSample(new PostgresJDBCSample())

    def criar(List<String> values) {
        List<String> fields =
                List.of("nome")

        jdbcCRUDSample.create(fields, values, "competencia")

    }

    def listar() {
        jdbcCRUDSample.read("competencia")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "competencia")
    }

    def deletar(int id) {
        jdbcCRUDSample.delete(id, "competencia")
    }

}
