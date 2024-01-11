package pi.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pi.java.Domain.Moveis;



public class MoveisDal {

    public Connection conectar(){
        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=Moveis;trustServerCertificate=true";    //IntegratedSecurity=true em casa
            String usuario = "user";
            String senha = "123456";

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                return conexao;
            }
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);
        }
//        finally{
//            try {
//                if (conexao != null && !conexao.isClosed()){
//                    conexao.close();
//                }
//            }catch(SQLException e){
//                System.out.println("O erro no finaly foi: " + e);
//            }
//        }
        return conexao;
    }

    //Inserir - Create
    public int inserirMovel(String movel, String tamanho, String cor, String numero, String preco, String imagem) throws SQLException{
        String sql = "INSERT INTO Moveis (movel, tamanho, cor, numero, preco, imagem) VALUES(?, ?, ?, ?, ?, ?)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, movel);
            statement.setString(2, tamanho);
            statement.setString(3, cor);
            statement.setString(4, numero);
            statement.setString(5, preco);
            statement.setString(6, imagem);

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

    public List listarMoveis() throws SQLException{
        String sql = "SELECT * FROM Users";
        ResultSet result = null;

        List<Moveis> moveisArray = new ArrayList<>();


        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos móveis: ");

            while (result.next()){
                int id = result.getInt("id");
                String movel = result.getString("movel");
                String tamanho = result.getString("tamanho");
                String cor = result.getString("cor");
                String numero = result.getString("numero");
                String preco = result.getString("preco");
                String imagem = result.getString("imagem");


                Moveis currentMovel = new Moveis(id, movel, tamanho, cor, numero, preco, imagem);

                moveisArray.add(currentMovel);


                System.out.println("id: " + id);
                System.out.println("movel: " + movel);
                System.out.println("tamanho: " + tamanho);
                System.out.println("cor: " + cor);
                System.out.println("numero: " + numero);
                System.out.println("preco: " + preco);
                System.out.println("imagem: " + imagem);
                System.out.println(" ");
            }

            result.close();

            return moveisArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }

        return moveisArray;
    }

    public int atualizarMoveis(int id, String movel, String tamanho, String cor, String numero, String preco, String imagem) throws SQLException{
        String sql = "UPDATE Users SET movel = ?, tamanho = ?, cor = ?, numero = ?, preco = ?, imagem = ? WHERE id = ?";

        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            statement.setString(1, movel);
            statement.setString(2, tamanho);
            statement.setString(3, cor);
            statement.setString(4, numero);
            statement.setString(5, preco);
            statement.setString(6, imagem);
            statement.setInt(7, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");
        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirMoveis(int id) throws SQLException{

        String sql = "DELETE FROM Users WHERE id = ?";

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