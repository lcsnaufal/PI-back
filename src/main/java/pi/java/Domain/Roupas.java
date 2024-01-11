package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Roupas {
    int id = 0;
    public String imagem = "";
    public String marca = "";
    public String tipo = "";
    public String tamanho = "";
    public String cor = "";
    public String numero = "";
    public String preco = "";


    public Roupas(){

    }

    public Roupas(String marca, String tipo, String tamanho, String cor, String numero, String preco, String imagem){

        this.imagem = imagem;
        this.marca = marca;
        this.tipo = tipo;
        this.tamanho = tamanho;
        this.cor = cor;
        this.numero = numero;
        this.preco = preco;
    }
    public Roupas(int id, String imagem, String marca, String tipo, String tamanho, String cor, String numero, String preco){

        this.id = id;
        this.imagem = imagem;
        this.marca = marca;
        this.tipo = tipo;
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
    public String getImagem(){
        return imagem;
    }
    public void setImagem(String imagem){
        this.imagem = imagem;
    }




    public JSONObject toJson(){
        JSONObject json = new JSONObject();

        json.put("imagem", imagem);
        json.put("marca",marca);
        json.put("tipo", tipo);
        json.put("tamanho", tamanho);
        json.put("cor", cor);
        json.put("numero", numero);
        json.put("preco", preco);

        return json;
    }

    public JSONObject arrayToJson(List<Roupas> roupasList) {
        JSONObject json = new JSONObject();

        if (!roupasList.isEmpty()) {
            var keyJson = 0;

            for (Roupas roupas : roupasList) {

                JSONObject jsonFor = new JSONObject();


                jsonFor.put("id", roupas.getId());
                jsonFor.put("imagem", roupas.getImagem());
                jsonFor.put("marca", roupas.getMarca());
                jsonFor.put("tipo", roupas.getTipo());
                jsonFor.put("tamanho", roupas.getTamanho());
                jsonFor.put("cor", roupas.getCor());
                jsonFor.put("numero", roupas.getNumero());
                jsonFor.put("preco", roupas.getPreco());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

                System.out.println(roupas.getMarca());



            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Roupas getRoupas(int index, List<Roupas> roupasList){

        if(index >= 0 && index < roupasList.size())  {

            return roupasList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Roupas> getAllRoupas(List<Roupas> roupasList){
        return roupasList;
    }
}

