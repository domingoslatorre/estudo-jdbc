package parte2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarAluno {
    public static void main(String[] args) throws SQLException {
        //1. Criar a conexão
        String url = "jdbc:sqlite:banco.sqlite";
        Connection connection = DriverManager.getConnection(url);

        Integer id = 1;

        //2. Executa a consulta e usa os resultados
        String sql = "DELETE FROM alunos WHERE id = ?;";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);
        
        int linhasAfetadas = statement.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Aluno removido com sucesso");
        } else {
            System.out.println("Falha ao remover o aluno");
        }

        //3. Fecha a conexão
        statement.close();
        connection.close();
    }
}
