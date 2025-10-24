package DAO;

import Conexao.Conexao;
import Model.Tarefa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class TarefaDAO {

    /**
     * Mapeia um registro (ResultSet) do banco de dados para um objeto Tarefa.
     * Garante o uso de nomes de colunas em snake_case como no SQL.
     */
    private Tarefa mapearTarefa(ResultSet rs) throws SQLException {
        Tarefa t = new Tarefa();
        t.setIdTarefa(rs.getInt("id_tarefa"));
        t.setTitulo(rs.getString("titulo"));
        t.setDescricao(rs.getString("descricao"));
        // Campos corrigidos de acordo com o SQL (VARCHAR)
        t.setPrazo(rs.getString("prazo"));
        t.setStatus(rs.getString("status"));
        // Chaves Estrangeiras corrigidas
        t.setIdDisciplina(rs.getInt("id_disciplina"));
        t.setIdAluno(rs.getInt("id_aluno"));
        return t;
    }

    /**
     * Retorna todas as tarefas registradas no banco de dados.
     */
    public ArrayList<Tarefa> listar() {
        ArrayList<Tarefa> lista = new ArrayList<>();
        // Tabela 'tarefas' e campos completos em snake_case
        String sql = "SELECT id_tarefa, titulo, descricao, prazo, status, id_disciplina, id_aluno FROM tarefas";

        try (Connection conexao = Conexao.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearTarefa(rs));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar tarefas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Busca uma tarefa específica por seu ID (chave primária).
     */
    public Tarefa buscarPorId(int id) {
        String sql = "SELECT id_tarefa, titulo, descricao, prazo, status, id_disciplina, id_aluno FROM tarefas WHERE id_tarefa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearTarefa(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar Tarefa por ID: " + e.getMessage());
        }
        return null;
    }

    /**
     * Adiciona uma nova tarefa ao banco de dados.
     */
    public Tarefa adicionar(Tarefa t) {
        // Colunas e placeholders ajustados
        String sql = "INSERT INTO tarefas (titulo, descricao, prazo, status, id_disciplina, id_aluno) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, t.getTitulo());
            stmt.setString(2, t.getDescricao());
            stmt.setString(3, t.getPrazo());
            stmt.setString(4, t.getStatus());
            stmt.setInt(5, t.getIdDisciplina());
            stmt.setInt(6, t.getIdAluno());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        t.setIdTarefa(rs.getInt(1));
                        return t;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir tarefa: " + e.getMessage());
        }
        return null;
    }

    /**
     * Atualiza os dados de uma tarefa existente no banco de dados.
     */
    public boolean atualizar(Tarefa t) {
        // Campos e colunas ajustados
        String sql = "UPDATE tarefas SET titulo = ?, descricao = ?, prazo = ?, status = ?, id_disciplina = ?, id_aluno = ? WHERE id_tarefa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, t.getTitulo());
            stmt.setString(2, t.getDescricao());
            stmt.setString(3, t.getPrazo());
            stmt.setString(4, t.getStatus());
            stmt.setInt(5, t.getIdDisciplina());
            stmt.setInt(6, t.getIdAluno());
            stmt.setInt(7, t.getIdTarefa());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar a tarefa: " + e.getMessage());
            return false;
        }
    }

    /**
     * Remove uma tarefa pelo ID.
     */
    public boolean remover(int id) {
        String sql = "DELETE FROM tarefas WHERE id_tarefa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover a tarefa: " + e.getMessage());
            return false;
        }
    }
}
