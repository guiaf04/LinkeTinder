package database.sample

import database.factorys.JDBCDatabaseFactory
import database.enums.JDBCDatabases
import database.interfaces.IJdbcCRUDSample
import database.interfaces.JDBCInterface
import groovy.sql.Sql

class PostgresCRUDSample implements IJdbcCRUDSample {
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    PostgresCRUDSample(JDBCInterface jdbcInterface){
        this.jdbcInterface = jdbcInterface
    }

     void create(List<String> fields, List<String> values, String database) {
        assert values.size() != 0

        Sql conn = jdbcInterface.connect()

        String query = "INSERT INTO ${database.toLowerCase()} " +
                "(${fields.join(", ")}) " +
                "VALUES (${values.collect { "'${it}'" }.join(", ")})"

        println query
        conn.executeInsert(query)

         jdbcInterface.disconnect(conn)
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
