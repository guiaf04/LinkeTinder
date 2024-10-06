package database

import groovy.sql.Sql

import java.sql.SQLException

abstract class DatabaseSample {
    static Sql connect(){
        Map dbParamns = [url:"jdbc:postgresql://localhost:5432/linketinder"
                         ,user:"postgres"
                         ,password:"postgres"
                         ,driver:"org.postgresql.Driver"]

        try{
            println("Banco conectado!")
            return Sql.newInstance(dbParamns)
        }catch (Exception e){
            e.printStackTrace()
            if(e instanceof ClassNotFoundException){
                System.err.println("Verifique o driver de conexão.")
            }else{
                System.err.println("Verifique se o servidor está ativo")
            }

            System.exit(-42)
            return null
        }
    }

    static void desconectar(Sql conn){
        if (conn != null){
            try {
                conn.close()
            }catch (SQLException e){
                e.printStackTrace()
            }
        }
    }

    abstract criar(List<String> values);
    abstract listar(String query);
    abstract atualizar(List<String> fields, List<String> values, int id);
    abstract deletar(int id);
}
