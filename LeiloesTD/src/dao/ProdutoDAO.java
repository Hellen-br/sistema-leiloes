package dao;

import model.Produto;
import util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    /**
     * Insere um produto no banco de dados.
     * @return true se inserido com sucesso, false caso contrário.
     */
    public boolean salvar(Produto p) {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setInt   (2, p.getValor());
            ps.setString(3, p.getStatus());
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Retorna todos os produtos cadastrados.
     */
    public List<Produto> listarTodos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT * FROM produtos";

        try (Connection con = Conexao.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Produto p = new Produto();
                p.setId    (rs.getInt   ("id"));
                p.setNome  (rs.getString("nome"));
                p.setValor (rs.getInt   ("valor"));
                p.setStatus(rs.getString("status"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
