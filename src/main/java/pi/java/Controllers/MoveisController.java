package pi.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import pi.java.Domain.Moveis;
import pi.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class MoveisController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Moveis> moveisList = new ArrayList<>();
    public static class MoveisHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Moveis> getAllFromArray = Moveis.getAllMoveis(moveisList);


                if(!getAllFromArray.isEmpty()){

                    Moveis moveis = new Moveis();

                    for(Moveis moveisJson : getAllFromArray){
                        System.out.println("imagem" + moveisJson.getImagem());
                        System.out.println("Movel: " + moveisJson.getMovel());
                        System.out.println("Tamanho: " + moveisJson.getTamanho());
                        System.out.println("Cor" + moveisJson.getCor());
                        System.out.println("Numero: " + moveisJson.getNumero());
                        System.out.println("Preco: " + moveisJson.getPreco());
                        System.out.println("");
                    }

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, moveis.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Moveis moveis = new Moveis(
                            json.getString("imagem"),
                            json.getString("movel"),
                            json.getString("tamanho"),
                            json.getString("cor"),
                            json.getString("numero"),
                            json.getString("preco")
                    );

                    moveisList.add(moveis);

                    System.out.println("MoveisList contém: " + moveis.toJson());

                    res.enviarResponseJson(exchange, moveis.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de moveis - PUT";
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