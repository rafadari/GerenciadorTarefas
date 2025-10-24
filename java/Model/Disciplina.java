package Model;

public class Disciplina {
    private int idDisciplina;
    private String nome;
    private int idProfessor;

    public Disciplina () {}

    public Disciplina(int idDisciplina, String nome, int idProfessor) {
        this.idDisciplina = idDisciplina;
        this.nome = nome;
        this.idProfessor = idProfessor;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }
}