package DAO;

import Conexao.Conexao;
import Model.Disciplina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DisciplinaDAO {

    // Método auxiliar para mapear o ResultSet ao objeto Model
    private Disciplina mapearDisciplina(ResultSet rs) throws SQLException {
        Disciplina d = new Disciplina();
        // CORREÇÃO: Coluna 'id_disciplina'
        d.setIdDisciplina(rs.getInt("id_disciplina"));
        d.setNome(rs.getString("nome"));
        // CORREÇÃO: Coluna 'id_professor'
        d.setIdProfessor(rs.getInt("id_professor"));
        return d;
    }

    // LISTAR todas as disciplinas
    public ArrayList<Disciplina> listar() {
        ArrayList<Disciplina> lista = new ArrayList<>();
        // CORREÇÃO: Tabela 'disciplinas'
        String sql = "SELECT id_disciplina, nome, id_professor FROM disciplinas";

        try (Connection conexao = Conexao.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(mapearDisciplina(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar disciplinas: " + e.getMessage());
        }
        return lista;
    }

    // BUSCAR por ID de chave primária (id_disciplina)
    public Disciplina buscarPorId(int id) {
        // CORREÇÃO: Tabela 'disciplinas' e Coluna 'id_disciplina'
        String sql = "SELECT id_disciplina, nome, id_professor FROM disciplinas WHERE id_disciplina = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearDisciplina(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar disciplina por ID: " + e.getMessage());
        }
        return null;
    }

    // ADICIONAR uma nova disciplina
    public Disciplina adicionar(Disciplina d) {
        // CORREÇÃO: Tabela 'disciplinas' e Colunas 'id_professor'
        String sql = "INSERT INTO disciplinas (nome, id_professor) VALUES (?, ?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getIdProfessor());

            if (stmt.executeUpdate() > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        d.setIdDisciplina(rs.getInt(1));
                        return d;
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao inserir disciplina: " + e.getMessage());
        }
        return null;
    }

    // ATUALIZAR uma disciplina
    public boolean atualizar(Disciplina d) {
        // CORREÇÃO: Tabela 'disciplinas' e Colunas 'id_professor' e 'id_disciplina'
        String sql = "UPDATE disciplinas SET nome = ?, id_professor = ? WHERE id_disciplina = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, d.getNome());
            stmt.setInt(2, d.getIdProfessor());
            stmt.setInt(3, d.getIdDisciplina());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar disciplina: " + e.getMessage());
            return false;
        }
    }

    // REMOVER uma disciplina
    public boolean remover(int id) {
        // CORREÇÃO: Tabela 'disciplinas' e Coluna 'id_disciplina'
        String sql = "DELETE FROM disciplinas WHERE id_disciplina = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover disciplina: " + e.getMessage());
            return false;
        }
    }
}
