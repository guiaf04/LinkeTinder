package database.interfaces

interface PostgresCRUDTables {
    def criar(List<String> values)

    def listar()

    def atualizar(List<String> fields, List<String> values, int id)

    def deletar(int id)
}