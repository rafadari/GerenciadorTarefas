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

    // Adiciona uma nova Tarefa
    public Tarefa adicionar(String titulo, String descricao, String prazo, String status,
                            int idDisciplina, int idAluno,
                            DisciplinaController disciplinas, UsuarioController usuarios) {

        // 1. Validações de Chave Estrangeira (Disciplina e Aluno devem existir e ser 'aluno')
        Disciplina d = disciplinas.buscarPorId(idDisciplina);
        Usuario aluno = usuarios.buscarPorId(idAluno);

        if (d == null || aluno == null || !("aluno".equalsIgnoreCase(aluno.getTipo()))) {
            System.err.println("Erro: Chave Estrangeira inválida (Disciplina não existe ou Usuário não é aluno).");
            return null;
        }

        // 2. Cria o objeto Model e envia para o DAO persistir
        Tarefa t = new Tarefa();
        t.setTitulo(titulo);
        t.setDescricao(descricao);
        t.setPrazo(prazo);
        t.setStatus(status);
        t.setIdDisciplina(idDisciplina);
        t.setIdAluno(idAluno); // Usa idAluno

        return dao.adicionar(t);
    }

    // Lista todas as Tarefas
    public ArrayList<Tarefa> listar() { return dao.listar(); }

    // Busca uma Tarefa por ID
    public Tarefa buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Atualiza uma Tarefa
    public boolean atualizar(int id, String titulo, String descricao, String prazo, String status,
                             int idDisciplina, int idAluno,
                             DisciplinaController disciplinas, UsuarioController usuarios) {

        Tarefa t = buscarPorId(id);
        if (t == null) return false;

        // 1. Validação de Chave Estrangeira para novos IDs
        Disciplina d = disciplinas.buscarPorId(idDisciplina);
        Usuario aluno = usuarios.buscarPorId(idAluno);
        if (d == null || aluno == null || !("aluno".equalsIgnoreCase(aluno.getTipo()))) return false;

        // 2. Atualiza o objeto Model
        t.setTitulo(titulo);
        t.setDescricao(descricao);
        t.setPrazo(prazo);
        t.setStatus(status);
        t.setIdDisciplina(idDisciplina);
        t.setIdAluno(idAluno);

        // 3. Persiste a mudança
        return dao.atualizar(t);
    }

    // Remove uma Tarefa
    public boolean remover(int id) {
        return dao.remover(id);
    }

    // Métodos de listagem (idealmente seriam SELECTs no DAO, mas aqui apenas filtram o listar() completo)
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