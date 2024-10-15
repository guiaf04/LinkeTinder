package model.dao

import model.Competencia
import model.Vaga
import model.dao.interfaces.ISampleDAO
import model.dao.sample.PostgreSampleDAO
import model.dao.sample.PostgresJDBCSample

class SkillsDAO implements ISampleDAO<Competencia>{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO()

    boolean criar(Vaga vaga) {
        List<String> fields =
                List.of("nome")

       return jdbcCRUDSample.create(fields, values, "competencia")
    }

    boolean listar() {
       return jdbcCRUDSample.read("competencia")
    }

    boolean atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "competencia")
    }

    def deletar(int id) {
        jdbcCRUDSample.delete(id, "competencia")
    }

}
