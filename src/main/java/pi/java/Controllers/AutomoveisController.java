package pi.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import pi.java.Domain.Automoveis;
import pi.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class AutomoveisController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Automoveis> AutomoveisList = new ArrayList<>();
    public static class AutomoveisHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Automoveis> getAllFromArray = Automoveis.getAllAutomoveis(AutomoveisList);


                if(!getAllFromArray.isEmpty()){

                    Automoveis automoveis = new Automoveis();

                    for(Automoveis automoveisJson : getAllFromArray){
                        System.out.println("Marca: " + automoveisJson.getMarca());
                        System.out.println("Modelo: " + automoveisJson.getModelo());
                        System.out.println("Ano: " + automoveisJson.getAno());
                        System.out.println("Cor" + automoveisJson.getCor());
                        System.out.println("Km" + automoveisJson.getKm());
                        System.out.println("Numero: " + automoveisJson.getNumero());
                        System.out.println("Preco: " + automoveisJson.getPreco());
                        System.out.println("");
                    }

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, automoveis.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Automoveis automoveis = new Automoveis(
                            json.getString("marca"),
                            json.getString("modelo"),
                            json.getString("ano"),
                            json.getString("cor"),
                            json.getString("km"),
                            json.getString("numero"),
                            json.getString("preco")
                    );

                    AutomoveisList.add(automoveis);

                    System.out.println("AutomoveisList contém: " + automoveis.toJson());

                    res.enviarResponseJson(exchange, automoveis.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de automoveis - PUT";
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