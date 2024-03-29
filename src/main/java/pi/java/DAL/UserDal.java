package pi.java.DAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pi.java.Domain.Users;



public class UserDal {

    public static Connection conectar(){

        Connection conexao = null;

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433;databaseName=pi;trustServerCertificate=true";       //TESTAR O trustServerCertificate=true se não funcionar remover, criar o banco de dados pi
            String usuario = "user";
            String senha = "123456";

            conexao = DriverManager.getConnection(url, usuario, senha);

            if(conexao != null){
                System.out.println("Conexão com o banco feita com sucesso");
                conexao.close();
                return conexao;
            }
        } catch(ClassNotFoundException | SQLException e){
            System.out.println("O Erro foi: " + e);

        }

        return conexao;
    }

    //Inserir - Create
    public int inserirUsuario(String name, String lastName, String age, String address, String email, String password, String cpf) throws SQLException{
        String sql = "INSERT INTO Users (name, lastName, age, address, email, password, cpf) VALUES(?, ?, ?, ?, ?, ?, ?,)";
        int linhasAfetadas = 0;
        Connection conexao = conectar();

        try(PreparedStatement statement = conexao.prepareStatement(sql)){
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, age);
            statement.setString(4, address);
            statement.setString(5, email);
            statement.setString(6, password);
            statement.setString(7, cpf);

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

    public List listarUsuario() throws SQLException{
        String sql = "SELECT * FROM Users";
        ResultSet result = null;

        List<Users> userArray = new ArrayList<>();


        try(PreparedStatement statement = conectar().prepareStatement(sql)){
            result = statement.executeQuery();

            System.out.println("Listagem dos usuários: ");

            while (result.next()){
                int id = result.getInt("id");
                String name = result.getString("name");
                String lastName = result.getString("lastName");
                String age = result.getString("age");
                String address = result.getString("address");
                String email = result.getString("email");
                String password = result.getString("password");
                String cpf = result.getString("cpf");


                Users currentUser = new Users(id, name, lastName, age, address, email, password, cpf);

                userArray.add(currentUser);


                System.out.println("id: " + id);
                System.out.println("nome: " + name);
                System.out.println("lastName: " + lastName);
                System.out.println("age: " + age);
                System.out.println("address: " + address);
                System.out.println("email: " + email);
                System.out.println("password: " + password);
                System.out.println("cpf: " + cpf);
                System.out.println(" ");
            }

            result.close();

            return userArray;

        }catch (SQLException e){
            System.out.println("O Erro na Listagem de dados foi: " + e);
        }

        return userArray;
    }

    public int atualizarUsuario() throws SQLException{
        String sql = "UPDATE Users SET nome = ?, lastName = ?, age = ?, address = ?, email = ?, password = ?, cpf = ?< WHERE id = ?";
        int linhasAfetadas = 0;
        try(PreparedStatement statement = conectar().prepareStatement(sql)){
//            statement.setString(1, name);
//            statement.setString(2, lastName);
//            statement.setString(3, age);
//            statement.setString(4, address);
//            statement.setString(5, email);
//            statement.setString(6, password);
//            statement.setString(7, cpf);
//            statement.setInt(8, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");
        }catch(SQLException e){
            System.out.println("O Erro na Atualização de dados foi: " + e);
        }
        return linhasAfetadas;
    }

    public int excluirUsuario() throws SQLException{

        String sql = "DELETE FROM Users WHERE id = ?";
        int linhasAfetadas = 0;

        try(PreparedStatement statement = conectar().prepareStatement(sql)){
//            statement.setInt(1, id);

            linhasAfetadas = statement.executeUpdate();

            System.out.println("Foram modificadas " + linhasAfetadas + " no banco de dados");
            return linhasAfetadas;
        }catch(SQLException e){
            System.out.println("O Erro na inserção de dados foi: " + e);
        }
        return linhasAfetadas;
    }
}