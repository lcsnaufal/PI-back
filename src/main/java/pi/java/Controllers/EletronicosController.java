package pi.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import pi.java.Domain.Eletronicos;
import pi.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class EletronicosController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Eletronicos> eletronicosList = new ArrayList<>();
    public static class EletronicosHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Eletronicos> getAllFromArray = Eletronicos.getAllEletronicos(eletronicosList);


                if(!getAllFromArray.isEmpty()){

                    Eletronicos eletronicos = new Eletronicos();

                    for(Eletronicos eletronicosJson : getAllFromArray){
                        System.out.println("Imagem: " + eletronicosJson.getImagem());
                        System.out.println("Marca: " + eletronicosJson.getMarca());
                        System.out.println("Modelo: " + eletronicosJson.getModelo());
                        System.out.println("Cor" + eletronicosJson.getCor());
                        System.out.println("Armazenamento: " + eletronicosJson.getArmazenamento());
                        System.out.println("Tela: " + eletronicosJson.getTela());
                        System.out.println("Numero de telefone: " + eletronicosJson.getNumero());
                        System.out.println("Preco: " + eletronicosJson.getPreco());
                        System.out.println("");
                    }

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, eletronicos.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Eletronicos eletronicos = new Eletronicos(
                            json.getString("imagem"),
                            json.getString("marca"),
                            json.getString("modelo"),
                            json.getString("cor"),
                            json.getString("armazenamento"),
                            json.getString("tela"),
                            json.getString("numero"),
                            json.getString("preco")
                    );

                    eletronicosList.add(eletronicos);

                    System.out.println("EletronicsList contém: " + eletronicos.toJson());

                    res.enviarResponseJson(exchange, eletronicos.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de eletronicos - PUT";
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