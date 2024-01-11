package pi.java.Domain;

import org.json.JSONObject;

import java.util.List;

public class Eletronicos {

    public int id = 0;
    public String imagem = "";
    public String marca = "";
    public String modelo = "";
    public String cor = "";
    public String armazenamento = "";
    public String tela = "";
    public String numero = "";
    public String preco = "";


    public Eletronicos(){

    }

    public Eletronicos(String imagem, String marca, String modelo, String cor, String armazenamento, String tela, String numero, String preco){

        this.imagem = imagem;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.armazenamento = armazenamento;
        this.tela = tela;
        this.numero = numero;
        this.preco = preco;

    }

    public Eletronicos(int id, String imagem, String marca, String modelo, String cor, String armazenamento, String tela, String numero, String preco){

        this.id = id;
        this.imagem = imagem;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.armazenamento = armazenamento;
        this.tela = tela;
        this.numero = numero;
        this.preco = preco;

    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
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

        json.put("imagem", imagem);
        json.put("marca", marca);
        json.put("modelo", modelo);
        json.put("cor", cor);
        json.put("armazenamento", armazenamento);
        json.put("tela", tela );
        json.put("numero", numero);
        json.put("preco", preco);

        return json;
    }

    public JSONObject arrayToJson(List<Eletronicos> eletronicosList) {
        JSONObject json = new JSONObject();

        if (!eletronicosList.isEmpty()) {
            var keyJson = 0;

            for (Eletronicos eletronicos : eletronicosList) {

                JSONObject jsonFor = new JSONObject();



                jsonFor.put("id", eletronicos.getId());
                jsonFor.put("imagem", eletronicos.getImagem());
                jsonFor.put("marca", eletronicos.getMarca());
                jsonFor.put("modelo", eletronicos.getModelo());
                jsonFor.put("cor", eletronicos.getCor());
                jsonFor.put("armazenamento", eletronicos.getArmazenamento());
                jsonFor.put("tela", eletronicos.getTela());
                jsonFor.put("numero", eletronicos.getNumero());
                jsonFor.put("preco", eletronicos.getPreco());


                json.put(String.valueOf(keyJson), jsonFor);

                keyJson++;

            }
            return json;
        }

        else{

            return null;
        }

    }

    public static Eletronicos getEletronicos(int index, List<Eletronicos> eletronicosList){

        if(index >= 0 && index < eletronicosList.size())  {

            return eletronicosList.get(index);

        }

        else{
            return null;
        }
    }

    public static List<Eletronicos> getAllEletronicos(List<Eletronicos> eletronicosList){
        return eletronicosList;
    }
}

