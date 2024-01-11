package pi.java.Services;

import com.sun.net.httpserver.Headers;
import pi.java.Controllers.*;
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
        HttpHandler EletronicosHandler = new EletronicosController.EletronicosHandler();
        HttpHandler AutomoveisHandler = new AutomoveisController.AutomoveisHandler();
        HttpHandler MoveisHandler = new MoveisController.MoveisHandler();
        HttpHandler RoupasHandler = new RoupasController.RoupasHandler();
        HttpHandler InstrumentosHandler = new InstrumentosController.InstrumentosHandler();


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

        server.createContext("/api/eletronicos", exchange -> {
            configureCorsHeaders(exchange);
            EletronicosHandler.handle(exchange);
        });

        server.createContext("/api/moveis", exchange -> {
            configureCorsHeaders(exchange);
            MoveisHandler.handle(exchange);
        });

        server.createContext("/api/roupas", exchange -> {
            configureCorsHeaders(exchange);
            RoupasHandler.handle(exchange);
        });

        server.createContext("/api/instrumentos", exchange -> {
            configureCorsHeaders(exchange);
            InstrumentosHandler.handle(exchange);
        });

        server.createContext("/api/automoveis", exchange -> {
            configureCorsHeaders(exchange);
            AutomoveisHandler.handle(exchange);
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

//    private void configureCorsHeaders(HttpExchange exchange){
//        Headers headers = exchange.getResponseHeaders();
//        String requestOrigin = exchange.getRequestHeaders().getFirst("Origin");
//        if (requestOrigin !=null) {
//            headers.set("Acess-Control-Allow-Origin", requestOrigin);
//        }
//
//        headers.set("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//        headers.set("Access-Control-Allow-Headers", "Content-Type, Authorization");
//        headers.set("Access-Control-Allow-Credentials", "true");
//        headers.set("Access-Control-Max-Age", "3600");
//    }
}
