package database.factorys

import database.candidateDB
import database.employeeDB
import database.enums.JDBCTables
import database.interfaces.PostgresCRUDTables
import database.skillsDB
import database.vacancyDB

class PostgresTableFactory {
    PostgresCRUDTables getTable(JDBCTables table){
        if (table == null)
            return null

        switch (table){
            case JDBCTables.Candidate:
                return new candidateDB()
            case JDBCTables.Employee:
                return new employeeDB()
            case JDBCTables.Skills:
                return new skillsDB()
            case JDBCTables.Vacancy:
                return new vacancyDB()
            default:
                println("Tabela inválida")
                return null
        }
    }
}
