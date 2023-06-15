package parte5;


public class Aluno {
    private Integer id;
    private String nome;
    private String prontuario;
    private String email;
    private Boolean ativo;

    public Aluno(Integer id, String nome, String prontuario, String email, Boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
        this.ativo = ativo;
    }

    public Aluno(String nome, String prontuario, String email, Boolean ativo) {
        this.nome = nome;
        this.prontuario = prontuario;
        this.email = email;
        this.ativo = ativo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProntuario() {
        return prontuario;
    }

    public void setProntuario(String prontuario) {
        this.prontuario = prontuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Aluno [id=" + id + ", nome=" + nome + ", prontuario=" + prontuario + ", email=" + email + ", ativo="
                + ativo + "]";
    }

    

}
