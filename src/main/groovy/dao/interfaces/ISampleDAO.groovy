package dao.interfaces

interface ISampleDAO <T>{
    boolean criar(T table)
    List<String> listar()
    boolean atualizar(List<String> fields, List<String> values, int id)
    boolean deletar(int id)
}