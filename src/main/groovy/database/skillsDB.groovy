package database

import groovy.sql.Sql

class skillsDB extends PostgresJDBCSample {

    def criar(List<String> values) {
        List<String> fields =
                List.of("nome")

        super.criar(fields, values, "competencia")

    }

    def listar() {
        super.listar("competencia")
    }

    def atualizar(List<String> fields, List<String> values, int id) {
        super.atualizar(fields, values, id, "competencia")
    }

    def deletar(int id) {
        super.deletar(id, "competencia")
    }

}
