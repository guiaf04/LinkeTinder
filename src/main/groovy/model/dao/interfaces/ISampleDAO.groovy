package model.dao.interfaces

interface ISampleDAO {
    boolean addCandidate(List<String> values)
    def listar()
    def atualizar(List<String> fields, List<String> values, int id)
    def deletar(int id)
}