package pi.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pi.java.Domain.Eletronicos;
import pi.java.Domain.Moveis;



public class EletronicosDal {

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
    public int inserirEletronico(String marca, String modelo, String cor, String armazenamento, String tela, String numero, String preco) throws SQLException{
        String sql = "INSERT INTO eletronicos (marca, modelo, cor, armazenamento, tela, numero, preco) VALUES(?, ?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, cor);
            statement.setString(4, armazenamento);
            statement.setString(5, tela);
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

    public List listarEletronico() throws SQLException{
        String sql = "SELECT * FROM eletronicos";
        ResultSet result = null;

        List<Eletronicos> eletronicosArray = new ArrayList<>();


        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos eletronicos: ");

            while (result.next()){
                int id = result.getInt("id");
                String marca = result.getString("marca");
                String modelo = result.getString("modelo");
                String cor = result.getString("cor");
                String armazenamento = result.getString("armazenamento");
                String tela = result.getString("tela");
                String numero = result.getString("numero");
                String preco = result.getString("preco");


                Eletronicos currentEletronico = new Eletronicos(id, marca, modelo, cor, armazenamento, tela, numero, preco);

                eletronicosArray.add(currentEletronico);


                System.out.println("id: " + id);
                System.out.println("marca: " + marca);
                System.out.println("modelo: " + modelo);
                System.out.println("cor: " + cor);
                System.out.println("armazenamento: " + armazenamento);
                System.out.println("tela: " + tela);
                System.out.println("numero: " + numero);
                System.out.println("preco: " + preco);
                System.out.println(" ");
            }

            result.close();

            return eletronicosArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }

        return eletronicosArray;
    }

    public int atualizarEletronico(int id, String marca, String modelo, String cor, String armazenamento, String tela, String numero, String preco) throws SQLException{
        String sql = "UPDATE eletronicos SET marca = ?, modelo = ?, cor = ?, armazenamento = ?, tela = ?, numero = ?, preco = ?, WHERE id = ?";

        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, modelo);
            statement.setString(3, cor);
            statement.setString(4, armazenamento);
            statement.setString(5, tela);
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

    public int excluirEletronico(int id) throws SQLException{

        String sql = "DELETE FROM eletronicos WHERE id = ?";

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