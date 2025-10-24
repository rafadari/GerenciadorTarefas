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

    public Nota adicionar(double valor, int idTarefa, TarefaController tarefas) {
        Tarefa t = tarefas.buscarPorId(idTarefa);
        if (t == null) {
            System.err.println("Erro: Tarefa com ID " + idTarefa + " não encontrada.");
            return null;
        }

        if (dao.buscarPorTarefa(idTarefa) != null) {
            System.err.println("Erro: Já existe uma nota para a Tarefa com ID " + idTarefa + ".");
            return null;
        }

        Nota n = new Nota();
        n.setValor(valor);
        n.setIdTarefa(idTarefa);

        return dao.adicionar(n);
    }

    public ArrayList<Nota> listar() {
        return dao.listar();
    }

    public Nota buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public Nota buscarPorTarefa(int idTarefa) {
        return dao.buscarPorTarefa(idTarefa);
    }

    public boolean atualizar(int id, double valor) {
        Nota n = buscarPorId(id);
        if (n == null) return false;

        n.setValor(valor);

        return dao.atualizar(n);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }

}
