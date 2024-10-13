package model.dao.interfaces

interface ISampleDAO <T>{
    boolean addCandidate(T table)
    List<String> listar()
    String getElementByCPF(String cpf)
    boolean atualizar(List<String> fields, List<String> values, int id)
    boolean deletar(int id)
}