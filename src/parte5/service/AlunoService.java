package parte5.service;

import java.util.List;

import parte5.data.AlunoDAO;
import parte5.data.DatabaseException;
import parte5.model.Aluno;

public class AlunoService {
    private AlunoDAO alunoDAO;

    public AlunoService(AlunoDAO alunoDAO) {
        this.alunoDAO = alunoDAO;
    }

    public Aluno cadastrar(Aluno aluno) throws DatabaseException, AlunoAlreadyExistsException {
        if (alunoDAO.findByEmail(aluno.getEmail()) != null) {
            throw new AlunoAlreadyExistsException("Aluno já cadastrado com e-mail " +
                    aluno.getEmail());
        }

        Aluno alunoSalvo = alunoDAO.create(aluno);

        return alunoSalvo;
    }

    public List<Aluno> findAll() throws DatabaseException {
        return alunoDAO.findAll();
    }

    public void deletar(int id) throws DatabaseException, AlunoNotFoundException {
        if (alunoDAO.findById(id) == null) {
            throw new AlunoNotFoundException("Aluno não encontrado com id " + id);
        }

        alunoDAO.delete(id);
    }
}
