package dao

import model.Empresa
import dao.interfaces.ISampleDAO
import dao.sample.PostgreSampleDAO

class EmployeeDAO implements ISampleDAO<Empresa>{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO()

    boolean criar(Empresa empresa) {
        List<String> fields =
                List.of("nome", "email", "cnpj",
                        "pais", "cep", "descricao", "senha")

        List<String> values = empresa.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>

        jdbcCRUDSample.create(fields, values, "empresa")
    }

    List<String> listar() {
        jdbcCRUDSample.read("empresa")
    }

    String getElementByCNPJ(String cnpj) {
        assert cnpj != null

        List<String> result = jdbcCRUDSample.readGeneric("empresa", "cnpj", cnpj)

        if(result.size() == 0){
            return null
        }

        return result.first
    }

    boolean atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "empresa")
    }

    boolean deletar(int id) {
        jdbcCRUDSample.delete(id, "empresa")
    }
}
