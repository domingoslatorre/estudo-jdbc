package parte5.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabela {
    public static void main(String[] args) throws SQLException {
        Connection connection = Conexao.getConnection();
        String sql = """
                    CREATE TABLE alunos(
                        id INTEGER PRIMARY KEY,
                        nome TEXT,
                        prontuario TEXT,
                        email TEXT,
                        ativo INTEGER
                    );
                """;

        Statement statement = connection.createStatement();
        statement.execute(sql);

        statement.close();
        connection.close();
    }
}
