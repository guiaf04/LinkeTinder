package database

import database.interfaces.JDBCInterface
import groovy.sql.Sql

import java.sql.SQLException

abstract class PostgresJDBCSample implements JDBCInterface{
    static Sql connect() {
        Map dbParamns = [url       : "jdbc:postgresql://localhost:5432/linketinder"
                         , user    : "postgres"
                         , password: "postgres"
                         , driver  : "org.postgresql.Driver"]

        try {
            println("Banco conectado!")
            return Sql.newInstance(dbParamns)
        } catch (Exception e) {
            e.printStackTrace()
            if (e instanceof ClassNotFoundException) {
                System.err.println("Verifique o driver de conexão.")
            } else {
                System.err.println("Verifique se o servidor está ativo")
            }

            System.exit(-42)
            return null
        }
    }

    static void desconectar(Sql conn) {
        if (conn != null) {
            try {
                conn.close()
            } catch (SQLException e) {
                e.printStackTrace()
            }
        }
    }

    static criar(List<String> fields, List<String> values, String database) {
        assert values.size() != 0

        Sql conn = connect()

        String query = "INSERT INTO ${database.toLowerCase()} " +
                "(${fields.join(", ")}) " +
                "VALUES (${values.collect { "'${it}'" }.join(", ")})"

        println query
        conn.executeInsert(query)

        desconectar(conn)
    }

    static listar(String database) {
        Sql sql = connect()

        sql.eachRow("SELECT * FROM " + database.toLowerCase()) { row ->
            println(row.toString())
        }
    }

    static atualizar(List<String> fields, List<String> values, int id, String database) {
        assert fields.size() == values.size()

        Sql conn = connect()
        int idx
        def setClause = fields.collect { field -> "${field} = '${values.get(idx++)}'" }.join(", ")

        String query = "UPDATE ${database.toLowerCase()} SET ${setClause} WHERE id=${id}"
        println query

        conn.executeUpdate query

        desconectar(conn)
    }

    static deletar(int id, String database) {
        Sql conn = connect()

        String query = "DELETE FROM ${database.toLowerCase()} WHERE id=${id}"

        conn.execute(query)

        println "${database} with id = ${id} was deleted!"

        desconectar(conn)

    }
}
