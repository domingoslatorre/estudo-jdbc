package parte1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AtualizarAluno {
    public static void main(String[] args) throws SQLException {
        //1. Criar a conexão
        String url = "jdbc:sqlite:banco.sqlite";
        Connection connection = DriverManager.getConnection(url);

        Integer id = 1;
        String nome = "Maria da Silva";
        String prontuario = "SP020202";
        String email = "maria@email.com";
        Boolean ativo = false;


        //2. Executa a consulta e usa os resultados
        String sql = """
            UPDATE alunos 
            SET nome = ?, prontuario = ?, email = ?, ativo = ?
            WHERE id = ?;
        """;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, prontuario);
        statement.setString(3, email);
        statement.setBoolean(4, ativo);
        statement.setInt(5, id);
        
        int linhasAfetadas = statement.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Aluno atualizado com sucesso");
        } else {
            System.out.println("Falha ao atualizar o aluno");
        }

        //3. Fecha a conexão
        statement.close();
        connection.close();
    }
}
