package model.dao

import model.Vaga
import model.dao.interfaces.ISampleDAO
import model.dao.sample.PostgreSampleDAO

class VacancyDAO implements ISampleDAO<Vaga>{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO()

    boolean criar(Vaga vaga) {
        List<String> fields =
                List.of("nome", "descricao", "local", "id_empresa")

        List<String> values = vaga.getProperties().findAll {!it.key.toString().equalsIgnoreCase("class")}.values() as List<String>


        return jdbcCRUDSample.create(fields, values, "vaga")
    }

    String getElementByIdEmpresaAndName(String name, String id_empresa) {
        assert name != null
        assert id_empresa != null

        List<String> values = new ArrayList<>()
        values.add(name)
        values.add(id_empresa)

        List<String> fields = new ArrayList<>()
        fields.add("nome")
        fields.add("id_empresa")

        List<String> result = jdbcCRUDSample.readGeneric("vaga", fields, values)

        if(result.size() == 0){
            return null
        }

        return result.first
    }

    List<String> listar() {
        return jdbcCRUDSample.read("vaga")
    }

    boolean atualizar(List<String> fields, List<String> values, int id) {
        return jdbcCRUDSample.update(fields, values, id, "vaga")
    }

    boolean deletar(int id) {
        return jdbcCRUDSample.delete(id, "vaga")
    }
}
