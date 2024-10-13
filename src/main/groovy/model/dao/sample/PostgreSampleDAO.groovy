package model.dao.sample

import model.dao.enums.JDBCDatabases
import model.dao.factorys.JDBCDatabaseFactory
import groovy.sql.Sql
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
        }catch (SQLException e){
            println(e.stackTrace)
            println("Não foi possível executar a query, verifique os parâmetros")
            return false
        }

         jdbcInterface.disconnect(conn)

         return true
    }

    void read(String database) {
        Sql conn = jdbcInterface.connect()

        conn.eachRow("SELECT * FROM " + database.toLowerCase()) { row ->
            println(row.toString())
        }

        jdbcInterface.disconnect(conn)
    }

    void update(List<String> fields, List<String> values, int id, String database) {
        assert fields.size() == values.size()

        Sql conn = jdbcInterface.connect()
        int idx
        def setClause = fields.collect { field -> "${field} = '${values.get(idx++)}'" }.join(", ")

        String query = "UPDATE ${database.toLowerCase()} SET ${setClause} WHERE id=${id}"
        println query

        conn.executeUpdate query

        jdbcInterface.disconnect(conn)
    }

    void delete(int id, String database) {
        Sql conn = jdbcInterface.connect()

        String query = "DELETE FROM ${database.toLowerCase()} WHERE id=${id}"

        conn.execute(query)

        println "${database} with id = ${id} was deleted!"

        jdbcInterface.disconnect(conn)
    }
}
