package Controller;

import Model.Usuario;
import DAO.UsuarioDAO;
import java.util.ArrayList;

public class UsuarioController {
    // Substituído ArrayList por DAO
    private UsuarioDAO dao;

    public UsuarioController() {
        this.dao = new UsuarioDAO();
    }

    // Adiciona no BD e retorna o objeto com o ID gerado pelo BD
    public Usuario adicionar(String nome, String email, String tipo) {
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        u.setTipo(tipo);
        return dao.adicionar(u); // Chama o método do DAO
    }

    // Lista chamando o DAO, que busca no BD
    public ArrayList<Usuario> listar() { return dao.listar(); }

    // Busca no BD
    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }
}