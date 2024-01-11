package pi.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pi.java.Domain.Automoveis;



public class AutomoveisDal {


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
    public int inserirMovel(String marca, String modelo, String ano, String cor, String km, String numero, String preco) throws SQLException{
        String sql = "INSERT INTO automoveis (marca, modelo, ano, cor, String km, numero, preco) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, ano);
            statement.setString(4, cor);
            statement.setString(5,km);
            statement.setString(6, numero);
            statement.setString(7, preco);

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

    public List listarAutomoveis() throws SQLException{
        String sql = "SELECT * FROM automoveis";
        ResultSet result = null;

        List<Automoveis> automoveisArray = new ArrayList<>();


        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos automoveis: ");

            while (result.next()){
                int id = result.getInt("id");
                String marca = result.getString("marca");
                String modelo = result.getString("modelo");
                String ano = result.getString("ano");
                String cor = result.getString("cor");
                String km = result.getString("km");
                String numero = result.getString("numero");
                String preco = result.getString("preco");


                Automoveis currentAutomovel = new Automoveis(id, marca, modelo, ano, cor, km, numero, preco);

                automoveisArray.add(currentAutomovel);


                System.out.println("id: " + id);
                System.out.println("marca: " + marca);
                System.out.println("modelo: " + modelo);
                System.out.println("ano: " + ano);
                System.out.println("cor: " + cor);
                System.out.println("numero: " + numero);
                System.out.println("preco: " + preco);
                System.out.println(" ");
            }

            result.close();

            return automoveisArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }

        return automoveisArray;
    }

    public int atualizarAutomoveis(int id, String marca, String modelo, String ano, String cor, String km, String numero, String preco) throws SQLException{
        String sql = "UPDATE automoveis SET marca = ?, modelo = ?, ano = ?, cor = ?, km = ?, numero = ?, preco = ?, WHERE id = ?";

        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, ano);
            statement.setString(4, cor);
            statement.setString(5, km);
            statement.setString(6, numero);
            statement.setString(7, preco);
            statement.setInt(8, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");
        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirAutomoveis(int id) throws SQLException{

        String sql = "DELETE FROM automoveis WHERE id = ?";

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