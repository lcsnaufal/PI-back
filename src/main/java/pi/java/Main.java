package pi.java;

import pi.java.Services.Servidor;
import pi.java.Services.SqlConnection;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{

        SqlConnection conexao = new SqlConnection();
        Servidor servidor = new Servidor();

        conexao.Conectar();
        servidor.apiServer();
    }
}