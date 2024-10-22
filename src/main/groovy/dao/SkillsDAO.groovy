package dao

import dao.enums.JDBCDatabases
import dao.factorys.JDBCDatabaseFactory
import dao.interfaces.JDBCInterface
import groovy.sql.Sql
import model.Competencia

import java.sql.SQLException

class SkillsDAO{
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    boolean criar(Competencia competencia) {
        Sql dbConnection = jdbcInterface.connect()

        String query = "INSERT INTO competencia " +
                "(nome) VALUES ('${competencia.getNome()}')"

        try {
            dbConnection.executeInsert(query)
        } catch (SQLException e) {
            e.printStackTrace()
            return false
        }

        jdbcInterface.disconnect(dbConnection)

        return true
    }

    String getElementByName(Competencia competencia){
        String result = ""

        Sql conn = jdbcInterface.connect()

        String query = "SELECT * FROM " +
                "competencia " +
                "WHERE nome='${competencia.getNome()}'"

        try {
            conn.eachRow(query) { row ->
                result = row.toString()
            }
        }catch (SQLException e){
            e.printStackTrace()
        }

        return result
    }

    List<String> listar() {
        Sql conn = jdbcInterface.connect()
        List<String> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM competencia") { row ->
                result.add(row.toString())
            }
        } catch (SQLException e) {
            println(e.stackTrace)
        }

        jdbcInterface.disconnect(conn)

        return result
    }

    boolean atualizar(Competencia competencia) {
        Sql conn = jdbcInterface.connect()

        String setClause = "nome='${competencia.getNome()}'"

        String query = "UPDATE competencia SET ${setClause} WHERE nome='${competencia.getNome()}'"

        try {
            conn.executeUpdate query
        } catch (SQLException e) {
            e.printStackTrace()
            return false
        }

        jdbcInterface.disconnect(conn)
        return true
    }

    boolean deletar(Competencia competencia) {
        Sql conn = jdbcInterface.connect()

        String query = "DELETE FROM competencia WHERE nome='${competencia.getNome()}'"

        try {
            conn.execute(query)
        } catch (SQLException e) {
            e.printStackTrace()
            return false
        }

        jdbcInterface.disconnect(conn)
        return true
    }

}
