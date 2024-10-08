package database

import groovy.sql.Sql

class employeeDB extends PostgresJDBCSample {
    def criar(List<String> values) {
        List<String> fields =
                List.of("nome", "email", "cnpj",
                        "pais", "cep", "descricao", "senha")

        super.criar(fields, values, "empresa")
    }

    def listar() {
        super.listar("empresa")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        super.atualizar(fields, values, id, "empresa")
    }

    def deletar(int id) {
        super.deletar(id, "empresa")
    }
}
