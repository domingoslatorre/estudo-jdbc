package parte5.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import parte5.model.Aluno;

// DAO = Data Access Object 
public class AlunoDAO {
    // CRUD

    // public Aluno save(Aluno aluno) {
    // return null;
    // }
    public Aluno create(Aluno aluno) throws DatabaseException {
        String sql = """
                    INSERT INTO alunos (nome, prontuario, email, ativo)
                    VALUES (?, ?, ?, ?);
                """;

        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection
                        .prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getProntuario());
            statement.setString(3, aluno.getEmail());
            statement.setBoolean(4, aluno.getAtivo());
            statement.executeUpdate();

            ResultSet rs = statement.getGeneratedKeys();

            if (rs.next()) {
                aluno.setId(rs.getInt(1));
            }

            rs.close();

            return aluno;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

    }

    public Aluno update(Aluno aluno) throws DatabaseException {
        String sql = """
                    UPDATE alunos
                    SET nome = ?, prontuario = ?, email = ?, ativo = ?
                    WHERE id = ?;
                """;

        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {

            statement.setString(1, aluno.getNome());
            statement.setString(2, aluno.getProntuario());
            statement.setString(3, aluno.getEmail());
            statement.setBoolean(4, aluno.getAtivo());
            statement.setInt(5, aluno.getId());

            int linhasAfetadas = statement.executeUpdate();

            if (linhasAfetadas > 0) {
                return aluno;
            }

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void delete(Integer id) throws DatabaseException {
        String sql = "DELETE FROM alunos WHERE id = ?;";

        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    public void delete(Aluno aluno) throws DatabaseException {
        delete(aluno.getId());
    }

    public Aluno findById(Integer id) throws DatabaseException {
        String sql = "SELECT * FROM alunos WHERE id = ?;";

        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return resultSetToAluno(rs);
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return null;
    }

    public Aluno findByEmail(String email) throws DatabaseException {
        String sql = "SELECT * FROM alunos WHERE email = ?;";

        try (
                Connection connection = Conexao.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setString(1, email);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Aluno(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("prontuario"),
                        rs.getString("email"),
                        rs.getBoolean("ativo"));
            }

            rs.close();

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }

        return null;
    }

    public List<Aluno> findAll() throws DatabaseException {
        String sql = "SELECT * FROM alunos;";
        List<Aluno> alunos = new ArrayList<>();

        try (
                Connection connection = Conexao.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sql);) {
            while (rs.next()) {
                alunos.add(resultSetToAluno(rs));
            }

            return alunos;

        } catch (Exception e) {
            e.printStackTrace();
            throw new DatabaseException();
        }
    }

    private Aluno resultSetToAluno(ResultSet rs) throws SQLException {
        return new Aluno(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("prontuario"),
                rs.getString("email"),
                rs.getBoolean("ativo"));
    }
}
