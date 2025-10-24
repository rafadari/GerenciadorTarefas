package DAO;

import Conexao.Conexao;
import Model.Nota;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class NotaDAO {

    private Nota mapearNota(ResultSet rs) throws SQLException {
        Nota n = new Nota();
        n.setIdNota(rs.getInt("id_nota"));
        n.setValor(rs.getDouble("valor"));
        n.setIdTarefa(rs.getInt("id_tarefa"));

        return n;
    }

    public ArrayList<Nota> listar() {
        ArrayList<Nota> lista = new ArrayList<>();
        String sql = "SELECT id_nota, valor, id_tarefa FROM notas";

        try (Connection conexao = Conexao.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearNota(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar notas: " + e.getMessage());
        }
        return lista;
    }

    public Nota buscarPorId(int id) {
        String sql = "SELECT id_nota, valor, id_tarefa FROM notas WHERE id_nota = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearNota(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Nota por ID: " + e.getMessage());
        }
        return null;
    }

    public Nota adicionar(Nota n) {
        String sql = "INSERT INTO notas (valor, id_tarefa) VALUES (?, ?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setDouble(1, n.getValor());
            stmt.setInt(2, n.getIdTarefa()); 

            if (stmt.executeUpdate() > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        n.setIdNota(rs.getInt(1));
                        return n;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir nota: " + e.getMessage());
        }
        return null;
    }

    public boolean atualizar(Nota n) {
        String sql = "UPDATE notas SET valor = ? WHERE id_nota = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setDouble(1, n.getValor());
            stmt.setInt(2, n.getIdNota());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a nota: " + e.getMessage());
            return false;
        }
    }

    public boolean remover(int id) {
        String sql = "DELETE FROM notas WHERE id_nota = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover a nota: " + e.getMessage());
            return false;
        }
    }

    public Nota buscarPorTarefa(int idTarefa) {
        String sql = "SELECT id_nota, valor, id_tarefa FROM notas WHERE id_tarefa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, idTarefa);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearNota(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar nota por Tarefa ID: " + e.getMessage());
        }
        return null;
    }

}
