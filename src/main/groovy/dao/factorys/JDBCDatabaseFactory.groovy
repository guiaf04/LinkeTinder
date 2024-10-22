package dao.factorys

import dao.enums.JDBCDatabases
import dao.interfaces.JDBCInterface
import dao.sample.PostgresJDBCSample

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
