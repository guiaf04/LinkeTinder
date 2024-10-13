package model.dao

import model.dao.sample.PostgreSampleDAO
import model.dao.sample.PostgresJDBCSample

class VacancyDAO {
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO(new PostgresJDBCSample())

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
