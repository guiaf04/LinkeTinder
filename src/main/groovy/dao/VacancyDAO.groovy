package dao

import dao.enums.JDBCDatabases
import dao.factorys.JDBCDatabaseFactory
import dao.interfaces.JDBCInterface
import groovy.sql.Sql
import model.Vaga
import model.Vaga
import dao.interfaces.ISampleDAO
import dao.sample.PostgreSampleDAO

import java.sql.SQLException

class VacancyDAO{
    JDBCDatabaseFactory databaseFactory = new JDBCDatabaseFactory()
    JDBCInterface jdbcInterface = databaseFactory.getDatabase(JDBCDatabases.PostgreSQL)

    String getElementByIdEmpresaAndName(Vaga vaga) {
        Sql conn = jdbcInterface.connect()

        String query = "SELECT * FROM vaga " +
                "WHERE nome='${vaga.getNome()}' AND id_empresa='${vaga.getIdempresa()}'"

        String result = ""

        try {
            conn.eachRow(query) { row ->
                result = row.toString()
            }
        }catch (SQLException e){
            e.printStackTrace()
        }

        return result
    }

    boolean criar(Vaga vaga) {
        Sql dbConnection = jdbcInterface.connect()

        String query = "INSERT INTO vaga " +
                "(nome, descricao, local, id_empresa) " +
                "VALUES ('${vaga.getNome()}', '${vaga.getDescricao()}'," +
                " '${vaga.getLocal()}', '${vaga.getIdempresa()}')"

        try {
            dbConnection.executeInsert(query)
        } catch (SQLException e) {
            println(e.stackTrace)
            println("Não foi possível executar a query, verifique os parâmetros")
            return false
        }

        jdbcInterface.disconnect(dbConnection)

        return true
    }

    List<String> listar() {
        Sql conn = jdbcInterface.connect()
        List<String> result = new ArrayList<>()

        try {
            conn.eachRow("SELECT * FROM vaga") { row ->
                result.add(row.toString())
            }
        } catch (SQLException e) {
            println(e.stackTrace)
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
        }

        jdbcInterface.disconnect(conn)

        return result
    }

    boolean atualizar(Vaga vaga) {
        Sql conn = jdbcInterface.connect()

        String setClause = "nome='${vaga.getNome()}', descricao='${vaga.getDescricao()}'" +
                ", local='${vaga.getLocal()}', id_empresa='${vaga.getIdempresa()}'"

        String query = "UPDATE vaga SET ${setClause} WHERE id_empresa='${vaga.getIdempresa()} AND nome='${vaga.getIdempresa()}'"

        try {
            conn.executeUpdate query
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }

        jdbcInterface.disconnect(conn)
        return true
    }

    boolean deletar(Vaga vaga) {
        Sql conn = jdbcInterface.connect()

        String query = "DELETE FROM vaga WHERE id_empresa='${vaga.getIdempresa()}' AND nome='${vaga.getNome()}'"

        try {
            conn.execute(query)
        } catch (SQLException e) {
            e.printStackTrace()
            println "Não foi possível executar a query, verifique se os valores passados foram corretos"
            return false
        }

        jdbcInterface.disconnect(conn)
        return true
    }

}
