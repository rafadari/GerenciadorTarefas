package Controller;

import Model.Usuario;
import DAO.UsuarioDAO;
import java.util.ArrayList;

public class UsuarioController {
   
    private UsuarioDAO dao;

    public UsuarioController() {
        this.dao = new UsuarioDAO();
    }

    public Usuario adicionar(String nome, String email, String tipo) {
        Usuario u = new Usuario();
        u.setNome(nome);
        u.setEmail(email);
        u.setTipo(tipo);
        return dao.adicionar(u); 
    }

    public ArrayList<Usuario> listar() { return dao.listar(); }

    public Usuario buscarPorId(int id) {
        return dao.buscarPorId(id);
    }

}
