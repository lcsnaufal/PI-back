package pi.java.Services;

import com.sun.net.httpserver.Headers;
import pi.java.Controllers.ProductsController;
import pi.java.Controllers.SalesPersonController;
import pi.java.Controllers.UserController;
import com.sun.net.httpserver.HttpServer;  // Criar um servidor
import com.sun.net.httpserver.HttpExchange;  //  Envia a requisicao do front pro back (passa pelos caminhos)
import com.sun.net.httpserver.HttpHandler; //

import java.io.IOException; //Erros do servidor e o que fazer
import java.net.InetSocketAddress; // Protocolo de leitura da internet // ele quem abre o caminho para as informacoes na internet


public class Servidor {


    public void apiServer() throws IOException{


        HttpServer server = HttpServer.create(new InetSocketAddress(8080),
                0);

        HttpHandler SalespersonHandler = new SalesPersonController.SalesPersonHandler();
        HttpHandler UserHandler = new UserController.UserHandler();
        HttpHandler ProductsHandler = new ProductsController.ProductsHandler();


        server.createContext("/api/vendedor", exchange -> {
            configureCorsHeaders(exchange);
            SalespersonHandler.handle(exchange);
        });

        server.createContext("/api/usuario", exchange -> {
            configureCorsHeaders(exchange);
            UserHandler.handle(exchange);
        });

        server.createContext("/api/produtos", exchange -> {
            configureCorsHeaders(exchange);
            ProductsHandler.handle(exchange);
        });

        server.setExecutor(null);
        System.out.println("Servidor Iniciado");
        server.start();
    }

    private void configureCorsHeaders(HttpExchange exchange){
        Headers headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        headers.set("Access-Control-Allow-Headers", "Content-Type");

    }
}