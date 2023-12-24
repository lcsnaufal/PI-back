package pi.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import pi.java.Domain.Roupas;
import pi.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class RoupasController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Roupas> roupasList = new ArrayList<>();
    public static class RoupasHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Roupas> getAllFromArray = Roupas.getAllRoupas(roupasList);


                if(!getAllFromArray.isEmpty()){

                    Roupas roupas = new Roupas();

                    for(Roupas roupasJson : getAllFromArray){
                        System.out.println("marca: " + roupasJson.getMarca());
                        System.out.println("tipo: " + roupasJson.getTipo());
                        System.out.println("tamanho" + roupasJson.getTamanho());
                        System.out.println("Cor: " + roupasJson.getCor());
                        System.out.println("Numero: " + roupasJson.getNumero());
                        System.out.println("Preco: " + roupasJson.getPreco());
                        System.out.println("");
                    }

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, roupas.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Roupas roupas = new Roupas(
                            json.getString("marca"),
                            json.getString("tipo"),
                            json.getString("tamanho"),
                            json.getString("cor"),
                            json.getString("numero"),
                            json.getString("preco")
                    );

                    roupasList.add(roupas);

                    System.out.println("RoupasList contém: " + roupas.toJson());

                    res.enviarResponseJson(exchange, roupas.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de roupas - PUT";
                res.enviarResponse(exchange, response);
            }
            else if ("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de vendedor - DELETE";
                res.enviarResponse(exchange, response);
            }
            else if ("OPTIONS".equals(exchange.getRequestMethod())){
                exchange.sendResponseHeaders(204, -1);
                exchange.close();
                return;
            }
            else {
                response = "nao definido." + "O metodo utilizado foi: " + exchange.getRequestMethod() + " So aceitamos get, put, post e delete";
                res.enviarResponse(exchange, response);
            }
        }
    }
}