package pi.java.Services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

    public static Connection Conectar(){

        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=teste;trustServerCertificate=true";       //TESTAR O trustServerCertificate=true se não funcionar remover, criar o banco de dados pi
            String usuario = "user";
            String senha = "123456";

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                System.out.println("Conexão com o banco feita com sucesso");
                conexao.close();
                return conexao;
            }
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);

        }

        return conexao;
    }
}












//        finally {
//
//            try{
//
//                if(conexao != null && !conexao.isClosed()){
//                    conexao.close();
//                }
//
//            }catch (SQLException e) {
//                System.out.println("O Erro no finally foi: " + e);
//            }
//        }
