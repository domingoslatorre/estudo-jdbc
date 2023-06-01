package parte2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ListarTodos {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:sqlite:banco.sqlite";
        String sql = "SELECT * FROM alunos;";
        
        try (
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
        ) {
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String prontuario = rs.getString("prontuario");
                String email = rs.getString("email");
                boolean ativo = rs.getBoolean("ativo");
    
                System.out.println("id: " + id);
                System.out.println("nome: " + nome);
                System.out.println("prontuario: " + prontuario);
                System.out.println("email: " + email);
                System.out.println("ativo: " + ativo);
                System.out.println("------------------------");
            }
        } catch (SQLException e) {
            // TODO: handle exception
        }
    }
}
