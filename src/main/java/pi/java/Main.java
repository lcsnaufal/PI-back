package pi.java;

import pi.java.Services.Servidor;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException{
        Servidor servidor = new Servidor();

        servidor.apiServer();
    }
}