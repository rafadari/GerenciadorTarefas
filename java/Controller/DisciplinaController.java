package Controller;

import Model.Disciplina;
import Model.Usuario;
import DAO.DisciplinaDAO; // Importe o novo DAO
import java.util.ArrayList;

public class DisciplinaController {
    // Substituído ArrayList por DAO
    private DisciplinaDAO dao;

    public DisciplinaController() {
        this.dao = new DisciplinaDAO();
    }

    // Valida no Controller, persiste no DAO
    public Disciplina adicionar(String nome, int idProfessor, UsuarioController usuarios) {
        Usuario prof = usuarios.buscarPorId(idProfessor);
        if (prof == null || !"professor".equalsIgnoreCase(prof.getTipo())) {
            return null;
        }
        Disciplina d = new Disciplina();
        d.setNome(nome);
        d.setIdProfessor(idProfessor);
        return dao.adicionar(d); // Persiste no BD e retorna o objeto com o ID
    }

    public ArrayList<Disciplina> listar() { return dao.listar(); }

    public Disciplina buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

    public boolean atualizar(int id, String nome, int idProfessor, UsuarioController usuarios) {
        Disciplina d = buscarPorId(id);
        if (d == null) return false;

        Usuario prof = usuarios.buscarPorId(idProfessor);
        if (prof == null || !"professor".equalsIgnoreCase(prof.getTipo())) return false;

        // Atualiza o objeto Model e envia para o DAO fazer o UPDATE no BD
        d.setNome(nome);
        d.setIdProfessor(idProfessor);
        d.setIdDisciplina(id); // Garante que o ID está no objeto
        return dao.atualizar(d);
    }

    public boolean remover(int id) {
        return dao.remover(id);
    }
}
