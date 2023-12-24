package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Celulares {
    int id = 0;
    public String marca = "";
    public String modelo = "";
    public String cor = "";
    public String armazenamento = "";
    public String tela = "";
    public String numero = "";
    public String preco = "";


    public Celulares(){

    }

    public Celulares(String marca, String modelo, String cor, String armazenamento, String tela, String numero, String preco){

        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.armazenamento = armazenamento;
        this.tela = tela;
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

    public String getCor(){
        return cor;
    }

    public void setCor(String Cor){
        this.cor = cor;
    }

    public String getArmazenamento(){
        return armazenamento;
    }

    public void setArmazenamento(String armazenamento){
        this.armazenamento = armazenamento;
    }

    public String getTela(){
        return tela;
    }

    public void setTela(String tela){
        this.tela = tela;
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
        json.put("cor", cor);
        json.put("armazenamento", armazenamento);
        json.put("tela", tela );
        json.put("numero", numero);
        json.put("preco", preco);

        return json;
    }

    public JSONObject arrayToJson(List<Celulares> celularesList) {
        JSONObject json = new JSONObject();

        if (!celularesList.isEmpty()) {
            var keyJson = 0;

            for (Celulares celulares : celularesList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("marca", celulares.getMarca());
                jsonFor.put("modelo", celulares.getModelo());
                jsonFor.put("cor", celulares.getCor());
                jsonFor.put("armazenamento", celulares.getArmazenamento());
                jsonFor.put("tela", celulares.getTela());
                jsonFor.put("numero", celulares.getNumero());
                jsonFor.put("preco", celulares.getPreco());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(celulares.getMarca());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Celulares getCelulares(int index, List<Celulares> celularesList){

        if(index >= 0 && index < celularesList.size())  {

            return celularesList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Celulares> getPhones(List<Celulares> celularesList){
        return celularesList;
    }
}

