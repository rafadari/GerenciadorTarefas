package Controller;

import Model.Nota;
import Model.Tarefa;
import DAO.NotaDAO;
import java.util.ArrayList;

public class NotaController {

    private NotaDAO dao;

    public NotaController() {
        this.dao = new NotaDAO();
    }

    // Adiciona uma nova Nota. Requer que a Tarefa exista e não tenha nota.
    public Nota adicionar(double valor, int idTarefa, TarefaController tarefas) {
        // 1. Validação de Regra de Negócio (Tarefa deve existir)
        Tarefa t = tarefas.buscarPorId(idTarefa);
        if (t == null) {
            System.err.println("Erro: Tarefa com ID " + idTarefa + " não encontrada.");
            return null;
        }

        // 2. Validação de Regra de Negócio (Relação 1:1, Tarefa só pode ter 1 Nota)
        if (dao.buscarPorTarefa(idTarefa) != null) {
            System.err.println("Erro: Já existe uma nota para a Tarefa com ID " + idTarefa + ".");
            return null;
        }

        // 3. Cria o objeto Model e envia para o DAO persistir no MySQL
        Nota n = new Nota();
        n.setValor(valor);
        n.setIdTarefa(idTarefa); // Usa idTarefa

        return dao.adicionar(n);
    }

    // Lista todas as Notas
    public ArrayList<Nota> listar() {
        return dao.listar();
    }

    // Busca uma Nota por ID (chave primária)
    public Nota buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    // Busca uma Nota pelo ID da Tarefa
    public Nota buscarPorTarefa(int idTarefa) {
        return dao.buscarPorTarefa(idTarefa);
    }

    // Atualiza o valor de uma Nota
    public boolean atualizar(int id, double valor) {
        Nota n = buscarPorId(id);
        if (n == null) return false;

        n.setValor(valor);

        return dao.atualizar(n);
    }

    // Remove uma Nota
    public boolean remover(int id) {
        return dao.remover(id);
    }
}