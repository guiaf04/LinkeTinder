package database

import groovy.sql.Sql

class skillsDB extends DatabaseSample {
    @Override
    def listar(String query) {
        Sql sql = connect()
        sql.eachRow("SELECT * FROM competencia") { row ->
            println(row.toString())
        }
    }

    @Override
    def atualizar(List<String> fields, List<String> values, int id) {
        return null
    }

    @Override
    def deletar(int id) {
        return null
    }

}
