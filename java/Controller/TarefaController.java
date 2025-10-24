package Controller;

import Model.Tarefa;
import Model.Disciplina;
import Model.Usuario;
import DAO.TarefaDAO;
import java.util.ArrayList;

public class TarefaController {

    private TarefaDAO dao;

    public TarefaController() {
        this.dao = new TarefaDAO();
    }
 
    public Tarefa adicionar(String titulo, String descricao, String prazo, String status,
                            int idDisciplina, int idAluno,
                            DisciplinaController disciplinas, UsuarioController usuarios) {

        Disciplina d = disciplinas.buscarPorId(idDisciplina);
        Usuario aluno = usuarios.buscarPorId(idAluno);

        if (d == null || aluno == null || !("aluno".equalsIgnoreCase(aluno.getTipo()))) {
            System.err.println("Erro: Chave Estrangeira inválida (Disciplina não existe ou Usuário não é aluno).");
            return null;
        }

        Tarefa t = new Tarefa();
        t.setTitulo(titulo);
        t.setDescricao(descricao);
        t.setPrazo(prazo);
        t.setStatus(status);
        t.setIdDisciplina(idDisciplina);
        t.setIdAluno(idAluno); 

        return dao.adicionar(t);
    }

    public ArrayList<Tarefa> listar() { return dao.listar(); }

    public Tarefa buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizar(int id, String titulo, String descricao, String prazo, String status,
                             int idDisciplina, int idAluno,
                             DisciplinaController disciplinas, UsuarioController usuarios) {

        Tarefa t = buscarPorId(id);
        if (t == null) return false;

        Disciplina d = disciplinas.buscarPorId(idDisciplina);
        Usuario aluno = usuarios.buscarPorId(idAluno);
        if (d == null || aluno == null || !("aluno".equalsIgnoreCase(aluno.getTipo()))) return false;

        t.setTitulo(titulo);
        t.setDescricao(descricao);
        t.setPrazo(prazo);
        t.setStatus(status);
        t.setIdDisciplina(idDisciplina);
        t.setIdAluno(idAluno);

        return dao.atualizar(t);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }

    public ArrayList<Tarefa> listarPorAluno(int idAluno) {
        ArrayList<Tarefa> todas = dao.listar();
        ArrayList<Tarefa> out = new ArrayList<>();
        for (Tarefa t : todas) if (t.getIdAluno() == idAluno) out.add(t);
        return out;
    }

    public ArrayList<Tarefa> listarPorDisciplina(int idDisciplina) {
        ArrayList<Tarefa> todas = dao.listar();
        ArrayList<Tarefa> out = new ArrayList<>();
        for (Tarefa t : todas) if (t.getIdDisciplina() == idDisciplina) out.add(t);
        return out;
    }

}
