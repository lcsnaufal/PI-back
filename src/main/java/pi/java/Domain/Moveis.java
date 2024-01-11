package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Moveis {

    public int id = 0;
    public String movel = "";
    public String tamanho = "";
    public String cor = "";
    public String numero = "";
    public String preco = "";


    public Moveis(){

    }
    public Moveis(int id){
        this.id = id;
    }

    public Moveis(String movel, String tamanho, String cor, String numero, String preco){

        this.movel = movel;
        this.tamanho = tamanho;
        this.cor = cor;
        this.numero = numero;
        this.preco = preco;
    }

    public Moveis(int id, String movel, String tamanho, String cor, String numero, String preco){

        this.id = id;
        this.movel = movel;
        this.tamanho = tamanho;
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
    public String getMovel(){
        return movel;
    }

    public void setMovel(String movel){
        this.movel = movel;
    }

    public String getTamanho(){
        return tamanho;
    }

    public void setTamanho(String tamanho){
        this.tamanho = tamanho;
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

        json.put("movel", movel);
        json.put("tamanho", tamanho);
        json.put("cor", cor);
        json.put("numero", numero);
        json.put("preco", preco);

        return json;
    }

    public JSONObject arrayToJson(List<Moveis> moveisList) {
        JSONObject json = new JSONObject();

        if (!moveisList.isEmpty()) {
            var keyJson = 0;

            for (Moveis moveis : moveisList) {

                JSONObject jsonFor = new JSONObject();

                jsonFor.put("id", moveis.getId());
                jsonFor.put("movel", moveis.getMovel());
                jsonFor.put("tamanho", moveis.getTamanho());
                jsonFor.put("cor", moveis.getCor());
                jsonFor.put("numero", moveis.getNumero());
                jsonFor.put("preco", moveis.getPreco());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(moveis.getMovel());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Moveis getMoveis(int index, List<Moveis> moveisList){

        if(index >= 0 && index < moveisList.size())  {

            return moveisList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Moveis> getAllMoveis(List<Moveis> moveisList){
        return moveisList;
    }
}

