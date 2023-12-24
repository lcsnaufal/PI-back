package pi.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import pi.java.Domain.Celulares;
import pi.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class CelularesController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Celulares> celularesList = new ArrayList<>();
    public static class CelularesHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Celulares> getAllFromArray = Celulares.getAllCelulares(celularesList);


                if(!getAllFromArray.isEmpty()){

                    Celulares celulares = new Celulares();

                    for(Celulares celularesJson : getAllFromArray){
                        System.out.println("Marca: " + celularesJson.getMarca());
                        System.out.println("Modelo: " + celularesJson.getModelo());
                        System.out.println("Cpf" + celularesJson.getCor());
                        System.out.println("Email: " + celularesJson.getArmazenamento());
                        System.out.println("Número de telefone: " + celularesJson.getTela());
                        System.out.println("Address: " + celularesJson.getNumero());
                        System.out.println("Preco: " + celularesJson.getPreco());
                        System.out.println("");
                    }

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, celulares.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Celulares celulares = new Celulares(
                            json.getString("marca"),
                            json.getString("modelo"),
                            json.getString("cor"),
                            json.getString("armazenamento"),
                            json.getString("tela"),
                            json.getString("numero"),
                            json.getString("preco")
                    );

                    celularesList.add(celulares);

                    System.out.println("CelularesList contém: " + celulares.toJson());

                    res.enviarResponseJson(exchange, celulares.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de celulares - PUT";
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