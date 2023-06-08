package parte4;

import java.sql.SQLException;

public class Principal {

    public static void main(String[] args) throws SQLException {
        AlunoDAO alunoDao = new AlunoDAO();
        
        Aluno aluno = new Aluno("João", "SP0101", "joao@email", true);

        Aluno alunoCriado = alunoDao.create(aluno);

        System.out.println(alunoCriado.getId());

        System.out.println(alunoDao.findById(2));
    }
    
}
