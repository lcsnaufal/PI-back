package pi.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pi.java.Domain.Instrumentos;



public class InstrumentosDal {


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
    public int inserirMovel(String marca, String tipo, String cor, String numero, String preco) throws SQLException{
        String sql = "INSERT INTO instrumentos (marca, tipo, cor, numero, preco) VALUES(?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, tipo);
            statement.setString(3, cor);
            statement.setString(4, numero);
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

    public List listarInstrumentos() throws SQLException{
        String sql = "SELECT * FROM instrumentos";
        ResultSet result = null;

        List<Instrumentos> instrumentosArray = new ArrayList<>();


        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos instrumentos: ");

            while (result.next()){
                int id = result.getInt("id");
                String marca = result.getString("marca");
                String tipo = result.getString("tipo");
                String cor = result.getString("cor");
                String numero = result.getString("numero");
                String preco = result.getString("preco");


                Instrumentos currentInstrumento = new Instrumentos(id, marca, tipo, cor, numero, preco);

                instrumentosArray.add(currentInstrumento);


                System.out.println("id: " + id);
                System.out.println("marca: " + marca);
                System.out.println("tipo: " + tipo);
                System.out.println("cor: " + cor);
                System.out.println("numero: " + numero);
                System.out.println("preco: " + preco);
                System.out.println(" ");
            }

            result.close();

            return instrumentosArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }

        return instrumentosArray;
    }

    public int atualizarInstrumentos(int id, String marca, String tipo, String cor, String numero, String preco) throws SQLException{
        String sql = "UPDATE instrumentos SET marca = ?, tipo = ?, cor = ?, numero = ?, preco = ?, WHERE id = ?";

        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, marca);
            statement.setString(2, tipo);
            statement.setString(3, cor);
            statement.setString(4, numero);
            statement.setString(5, preco);
            statement.setInt(6, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");
        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirInstrumentos(int id) throws SQLException{

        String sql = "DELETE FROM instrumentos WHERE id = ?";

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