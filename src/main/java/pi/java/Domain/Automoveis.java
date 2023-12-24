package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Automoveis {
    int id = 0;
    public String marca = "";
    public String modelo = "";
    public String ano = "";
    public String cor = "";
    public String km = "";
    public String numero = "";
    public String preco = "";


    public Automoveis(){

    }

    public Automoveis(String marca, String modelo, String ano, String cor, String km, String numero, String preco){

        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.km = km;
        this.numero = numero;
        this.preco = preco;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public String getAno(){
        return ano;
    }

    public void setAno(String ano){
        this.ano = ano;
    }

    public String getCor(){
        return cor;
    }

    public void setCor(String Cor){
        this.cor = cor;
    }

    public String getKm(){
        return km;
    }

    public void setKm(String km){
        this.km = km;
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

        json.put("marca",marca);
        json.put("modelo", modelo);
        json.put("ano", ano);
        json.put("cor", cor);
        json.put("km", km);
        json.put("numero", numero);
        json.put("preco", preco);

        return json;
    }

    public JSONObject arrayToJson(List<Automoveis> automoveisList) {
        JSONObject json = new JSONObject();

        if (!automoveisList.isEmpty()) {
            var keyJson = 0;

            for (Automoveis automoveis : automoveisList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("marca", automoveis.getMarca());
                jsonFor.put("modelo", automoveis.getModelo());
                jsonFor.put("ano", automoveis.getAno());
                jsonFor.put("cor", automoveis.getCor());
                jsonFor.put("km", automoveis.getKm());
                jsonFor.put("numero", automoveis.getNumero());
                jsonFor.put("preco", automoveis.getPreco());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(automoveis.getMarca());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Automoveis getAutomoveis(int index, List<Automoveis> automoveisList){

        if(index >= 0 && index < automoveisList.size())  {

            return automoveisList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Automoveis> getAutomoveis(List<Automoveis> automoveisList){
        return automoveisList;
    }
}

