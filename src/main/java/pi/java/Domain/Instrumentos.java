package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Instrumentos {
    public int id = 0;
    public String marca = "";
    public String tipo = "";
    public String cor = "";
    public String numero = "";
    public String preco = "";


    public Instrumentos(){

    }

    public Instrumentos(String marca, String tipo, String cor, String numero, String preco){

        this.marca = marca;
        this.tipo = tipo;
        this.cor = cor;
        this.numero = numero;
        this.preco = preco;
    }

    public Instrumentos(int id, String marca, String tipo, String cor, String numero, String preco){

        this.id = id;
        this.marca = marca;
        this.tipo = tipo;
        this.cor = cor;
        this.numero = numero;
        this.preco = preco;
    }


    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
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

        json.put("marca", marca);
        json.put("tipo", tipo);
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


                jsonFor.put("id", instrumentos.getId());
                jsonFor.put("marca", instrumentos.getMarca());
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

