package model.dao.sample

import groovy.sql.Sql
import model.dao.enums.JDBCDatabases
import model.dao.factorys.JDBCDatabaseFactory
import model.dao.interfaces.IJdbcCRUDSample
import model.dao.interfaces.JDBCInterface

import java.sql.SQLException

class PostgreSampleDAO implements IJdbcCRUDSample {
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    boolean create(List<String> fields, List<String> values, String database) {
        assert values.size() != 0

        Sql conn = jdbcInterface.connect()

        String query = "INSERT INTO ${database.toLowerCase()} " +
                "(${fields.join(", ")}) " +
                "VALUES (${values.collect { "'${it}'" }.join(", ")})"

        println query

        try {
            conn.executeInsert(query)
        } catch (SQLException e) {
            println(e.stackTrace)
            println("Não foi possível executar a query, verifique os parâmetros")
            return false
        }

        jdbcInterface.disconnect(conn)

        return true
    }

    List<String> read(String database) {
        Sql conn = jdbcInterface.connect()
        List<String> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM " + database.toLowerCase()) { row ->
//                println(row.toString())
                result.add(row.toString())
            }
        } catch (SQLException e) {
            println(e.stackTrace)
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return null
        }

        jdbcInterface.disconnect(conn)

        return result
    }

    List<String> readGeneric(String database, String field, String value) {
        Sql conn = jdbcInterface.connect()

        String query = "SELECT * FROM " +
                "${database.toLowerCase()} " +
                "WHERE ${field}='${value}'"

        List<String> result = new ArrayList<>()

        conn.eachRow(query) { row ->
            result.add(row.toString())
        }

        return result
    }

    boolean update(List<String> fields, List<String> values, int id, String database) {
        println("SampleDAO: " +values)
        assert fields.size() == values.size()

        Sql conn = jdbcInterface.connect()
        int idx
        def setClause = fields.collect { field -> "${field} = '${values.get(idx++)}'" }.join(", ")

        String query = "UPDATE ${database.toLowerCase()} SET ${setClause} WHERE id=${id}"
        try {
            println query
            conn.executeUpdate query
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }

        jdbcInterface.disconnect(conn)
        return true
    }

    boolean delete(int id, String database) {
        Sql conn = jdbcInterface.connect()

        String query = "DELETE FROM ${database.toLowerCase()} WHERE id=${id}"

        try {
            conn.execute(query)
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }
        println "${database} with id = ${id} was deleted!"

        jdbcInterface.disconnect(conn)
        return true
    }
}
