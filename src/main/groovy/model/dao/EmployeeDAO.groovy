package model.dao


import model.dao.sample.PostgreSampleDAO
import model.dao.sample.PostgresJDBCSample

class EmployeeDAO {
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO(new PostgresJDBCSample())

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
