package dao

import model.Competencia
import dao.interfaces.ISampleDAO
import dao.sample.PostgreSampleDAO

class SkillsDAO implements ISampleDAO<Competencia>{
    PostgreSampleDAO jdbcCRUDSample = new PostgreSampleDAO()

    boolean criar(Competencia competencia) {
        List<String> fields =
                List.of("nome")

        List<String> values = List.of(competencia.getNome())

       return jdbcCRUDSample.create(fields, values, "competencia")
    }

    String getElementByName(String name){
        assert name != null

        List<String> result = jdbcCRUDSample.readGeneric("competencia", "nome", name)

        if (result.size()==0){
            return null
        }

         return result.first()
    }

    List<String> listar() {
       return jdbcCRUDSample.read("competencia")
    }

    boolean atualizar(List<String> fields, List<String> values, int id) {
        jdbcCRUDSample.update(fields, values, id, "competencia")
    }

    boolean deletar(int id) {
        jdbcCRUDSample.delete(id, "competencia")
    }

}
