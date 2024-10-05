package database

import groovy.sql.Sql

class employeeDB extends DatabaseSample {
    @Override
    def listar(String query) {
        Sql sql = connect()
        sql.eachRow("SELECT * FROM empresa") { row ->
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
