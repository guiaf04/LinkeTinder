package database

import groovy.sql.Sql

class vacancyDB extends PostgresJDBCSample {
    def criar(List<String> values) {
        List<String> fields =
                List.of("nome", "descricao", "local", "id_empresa")

        super.criar(fields, values, "vaga")
    }

    def listar() {
        super.listar("vaga")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        super.atualizar(fields, values, id, "vaga")
    }

    def deletar(int id) {
        super.deletar(id, "vaga")
    }
}
