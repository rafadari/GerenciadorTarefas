package Model;

public class Tarefa {
    private int idTarefa;
    private String titulo;
    private String descricao;
    private String prazo;
    private String status;
    private int idDisciplina;
    private int idAluno;

    public Tarefa () {}

    public Tarefa(int idTarefa, String titulo, String descricao, String prazo, String status, int idDiciplina, int idAluno) {
        this.idTarefa = idTarefa;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prazo = prazo;
        this.status = status;
        this.idDisciplina = idDiciplina;
        this.idAluno = idAluno;

    }
    public int getIdTarefa() { return idTarefa; }
    public void setIdTarefa(int idTarefa) { this.idTarefa = idTarefa; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getPrazo() { return prazo; }
    public void setPrazo(String prazo) { this.prazo = prazo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdDisciplina() { return idDisciplina; }
    public void setIdDisciplina(int idDisciplina) { this.idDisciplina = idDisciplina; }

    public int getIdAluno() { return idAluno; }
    public void setIdAluno(int idAluno) { this.idAluno = idAluno; }
}

