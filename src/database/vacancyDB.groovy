package database

import groovy.sql.Sql

class vacancyDB extends DatabaseSample {
    @Override
    def listar(String query) {
        Sql sql = connect()
        sql.eachRow("SELECT * FROM vaga") { row ->
            println(row.toString())
        }
    }

    @Override
    def atualizar(String query) {
        return null
    }

    @Override
    def deletar(String query) {
        return null
    }
}
