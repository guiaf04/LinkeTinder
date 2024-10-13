package model.dao


import model.dao.sample.PostgreSampleDAO
import model.dao.sample.PostgresJDBCSample

class SkillsDAO{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO(new PostgresJDBCSample())

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
