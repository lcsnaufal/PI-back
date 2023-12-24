package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Instrumentos {
    int id = 0;
    public String tipo = "";
    public String cor = "";
    public String numero = "";
    public String preco = "";


    public Instrumentos(){

    }

    public Instrumentos(String tipo, String cor, String numero, String preco){

        this.tipo = tipo;
        this.cor = cor;
        this.numero = numero;
        this.preco = preco;
    }

    public String getTipo(){
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    public String getCor(){
        return cor;
    }

    public void setCor(String Cor){
        this.cor = cor;
    }

    public String getNumero(){
        return numero;
    }

    public void setNumero(String numero){
        this.numero = numero;
    }

    public String getPreco(){
        return preco;
    }

    public void setPreco(String preco){
        this.preco = preco;
    }



    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("tipo",tipo);
        json.put("cor", cor);
        json.put("numero", numero);
        json.put("preco", preco);

        return json;
    }

    public JSONObject arrayToJson(List<Instrumentos> instrumentosList) {
        JSONObject json = new JSONObject();

        if (!instrumentosList.isEmpty()) {
            var keyJson = 0;

            for (Instrumentos instrumentos : instrumentosList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("tipo", instrumentos.getTipo());
                jsonFor.put("cor", instrumentos.getCor());
                jsonFor.put("numero", instrumentos.getNumero());
                jsonFor.put("preco", instrumentos.getPreco());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(instrumentos.getTipo());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Instrumentos getInstrumentos(int index, List<Instrumentos> instrumentosList){

        if(index >= 0 && index < instrumentosList.size())  {

            return instrumentosList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Instrumentos> getAllInstrumentos(List<Instrumentos> instrumentosList){
        return instrumentosList;
    }
}

