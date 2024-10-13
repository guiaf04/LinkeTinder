package model.dao.factorys

import model.dao.enums.JDBCDatabases
import model.dao.interfaces.JDBCInterface
import model.dao.sample.PostgresJDBCSample

class JDBCDatabaseFactory {
    static JDBCInterface getDatabase(JDBCDatabases database){
        if (database == null)
            return null

        switch (database){
            case JDBCDatabases.PostgreSQL:
                return new PostgresJDBCSample()
            default:
                println("Banco inválido")
                return null
        }
    }
}
