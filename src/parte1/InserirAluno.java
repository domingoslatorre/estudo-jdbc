package parte1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InserirAluno {
    public static void main(String[] args) throws SQLException {
        //1. Criar a conexão
        String url = "jdbc:sqlite:banco.sqlite";
        Connection connection = DriverManager.getConnection(url);

        String nome = "João da Silva";
        String prontuario = "SP010101";
        String email = "joao@email.com";
        Boolean ativo = true;


        //2. Executa a consulta e usa os resultados
        String sql = """
            INSERT INTO alunos (nome, prontuario, email, ativo)
            VALUES (?, ?, ?, ?);
        """;

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, prontuario);
        statement.setString(3, email);
        statement.setBoolean(4, ativo);
        
        int linhasAfetadas = statement.executeUpdate();

        if (linhasAfetadas > 0) {
            System.out.println("Aluno inserido com sucesso");
        } else {
            System.out.println("Falha ao inserir o aluno");
        }

        //3. Fecha a conexão
        statement.close();
        connection.close();
    }
}
