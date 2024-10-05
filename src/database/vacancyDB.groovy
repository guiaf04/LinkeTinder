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
    def atualizar(List<String> fields, List<String> values, int id) {
        Sql conn = connect()
        assert fields.size() == values.size()

        String query = "UPDATE candidato SET "

        for(int i =0; i<fields.size(); i++){
            query += (i == 0 ? "" : ", ")
            query += "${fields[i]}='${values[i]}'"
        }

        query += " WHERE id=${id}"

        conn.executeUpdate query
    }

    @Override
    def deletar(int id) {
        Sql conn = connect()

        String query = "DELETE FROM candidato WHERE id=${id}"

        conn.execute(query)

        println "Candidate with id = ${id} was deleted!"
    }
}
