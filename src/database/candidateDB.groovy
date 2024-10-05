package database

import groovy.sql.GroovyResultSet
import groovy.sql.Sql

class candidateDB extends DatabaseSample {
    @Override
    def listar(String query) {
        Sql sql = connect()
        sql.eachRow("SELECT * FROM candidato") { row ->
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
