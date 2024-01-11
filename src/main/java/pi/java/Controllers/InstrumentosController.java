package pi.java.Controllers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

import pi.java.Domain.Instrumentos;
import pi.java.Services.ResponseEndPoints;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class InstrumentosController {

    static ResponseEndPoints res = new ResponseEndPoints();

    private static List<Instrumentos> InstrumentosList = new ArrayList<>();
    public static class InstrumentosHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            String response = "";

            if ("GET".equals(exchange.getRequestMethod())){

                List<Instrumentos> getAllFromArray = Instrumentos.getAllInstrumentos(InstrumentosList);


                if(!getAllFromArray.isEmpty()){

                    Instrumentos instrumentos = new Instrumentos();

                    for(Instrumentos instrumentosJson : getAllFromArray){
                        System.out.println("Marca: " + instrumentosJson.getMarca());
                        System.out.println("Tipo: " + instrumentosJson.getTipo());
                        System.out.println("Cor: " + instrumentosJson.getCor());
                        System.out.println("Numero: " + instrumentosJson.getNumero());
                        System.out.println("Preco" + instrumentosJson.getPreco());
                        System.out.println("");
                    }

                    response = "Dados encontrados com sucesso";
                    res.enviarResponseJson(exchange, instrumentos.arrayToJson(getAllFromArray), 200);
                }

                else{
                    response = "Dados não encontrados";
                    res.enviarResponse(exchange, response);
                }
            }

            else if ("POST".equals(exchange.getRequestMethod())){

                try(InputStream requestBody = exchange.getRequestBody()){

                    JSONObject json = new JSONObject(new String(requestBody.readAllBytes()));

                    Instrumentos instrumentos = new Instrumentos(
                            json.getString("marca"),
                            json.getString("tipo"),
                            json.getString("cor"),
                            json.getString("numero"),
                            json.getString("preco")
                    );

                    InstrumentosList.add(instrumentos);

                    System.out.println("InstrumentosList contém: " + instrumentos.toJson());

                    res.enviarResponseJson(exchange, instrumentos.toJson(), 200);
                }
                catch(Exception e){
                    String ExceptionResponse = e.toString();

                    System.out.println(ExceptionResponse);
                    System.out.println("------------------------------------------------");

                }
            }

            else if ("PUT".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de instrumentos - PUT";
                res.enviarResponse(exchange, response);
            }
            else if ("DELETE".equals(exchange.getRequestMethod())){
                response = "Essa e a rota de instrumentos - DELETE";
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