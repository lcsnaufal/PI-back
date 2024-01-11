package pi.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pi.java.Domain.Roupas;



public class RoupasDal {

    public Connection conectar(){
        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=pi;trustServerCertificate=true";    //IntegratedSecurity=true em casa
            String usuario = "user";
            String senha = "123456";

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                return conexao;
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);
        }
        return conexao;
    }

    //Inserir - Create
    public int inserirRoupa(String marca, String tipo, String tamanho, String cor, String numero, String preco) throws SQLException{
        String sql = "INSERT INTO roupas (marca, tipo, tamanho, cor, numero, preco) VALUES(?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, tipo);
            statement.setString(3, tamanho);
            statement.setString(4, cor);
            statement.setString(5, numero);
            statement.setString(5, preco);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            conexao.close();
            return linhasAfetadas;
        }catch(SQLException e){
            System.out.println("O Erro na Inserção de dados foi: " + e);
            conexao.close();
        }
        conexao.close();
        return linhasAfetadas;
    }

    public List listarRoupas() throws SQLException{
        String sql = "SELECT * FROM roupas";
        ResultSet result = null;

        List<Roupas> roupasArray = new ArrayList<>();


        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos móveis: ");

            while (result.next()){
                int id = result.getInt("id");
                String marca = result.getString("marca");
                String tipo = result.getString("tipo");
                String tamanho = result.getString("tamanho");
                String cor = result.getString("cor");
                String numero = result.getString("numero");
                String preco = result.getString("preco");


                Roupas currentRoupas = new Roupas(id, marca, tipo, tamanho, cor, numero, preco);

                roupasArray.add(currentRoupas);


                System.out.println("id: " + id);
                System.out.println("marca: " + marca);
                System.out.println("tipo: " + tipo);
                System.out.println("tamanho: " + tamanho);
                System.out.println("cor: " + cor);
                System.out.println("numero: " + numero);
                System.out.println("preco: " + preco);
                System.out.println(" ");
            }

            result.close();

            return roupasArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }

        return roupasArray;
    }

    public int atualizarRoupas(int id, String marca, String tipo, String tamanho, String cor, String numero, String preco) throws SQLException{
        String sql = "UPDATE roupas SET marca = ?, tipo = ?, tamanho = ?, cor = ?, numero = ?, preco = ?, WHERE id = ?";

        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, tipo);
            statement.setString(3, tamanho);
            statement.setString(4, cor);
            statement.setString(5, numero);
            statement.setString(6, preco);
            statement.setInt(7, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");
        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirRoupas(int id) throws SQLException{

        String sql = "DELETE FROM roupas WHERE id = ?";

        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setInt(1, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");

            return linhasAfetadas;
        }catch(SQLException e){
            System.out.println("O Erro na inserção de dados foi: " + e);
        }
        return linhasAfetadas;
    }
}