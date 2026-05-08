package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Tela de listagem de todos os produtos cadastrados.
 */
public class listagemVIEW extends javax.swing.JFrame {

    private JTable          tabelaProdutos;
    private JScrollPane     scrollPane;
    private JButton         btnAtualizar;
    private JButton         btnVoltar;
    private JLabel          lblTitulo;

    public listagemVIEW() {
        initComponents();
        carregarTabela();          // carrega dados ao abrir a tela
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setTitle("Casa de Leilões — Listagem de Produtos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 380);
        setLayout(null);
        setResizable(false);

        // Título
        lblTitulo = new JLabel("Produtos Cadastrados");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(160, 15, 230, 25);
        add(lblTitulo);

        // Tabela
        tabelaProdutos = new JTable();
        scrollPane     = new JScrollPane(tabelaProdutos);
        scrollPane.setBounds(20, 50, 510, 240);
        add(scrollPane);

        // Botão Atualizar
        btnAtualizar = new JButton("Atualizar");
        btnAtualizar.setBounds(100, 305, 120, 35);
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);
        add(btnAtualizar);

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(320, 305, 120, 35);
        btnVoltar.addActionListener(this::btnVoltarActionPerformed);
        add(btnVoltar);
    }

    /**
     * Busca todos os produtos no banco e popula a JTable.
     */
    private void carregarTabela() {
        String[] colunas = {"ID", "Nome", "Valor (R$)", "Status"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int col) { return false; }
        };

        ProdutoDAO dao       = new ProdutoDAO();
        List<Produto> lista  = dao.listarTodos();

        for (Produto p : lista) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getValor(),
                p.getStatus()
            });
        }

        tabelaProdutos.setModel(model);
    }

    private void btnAtualizarActionPerformed(ActionEvent evt) {
        carregarTabela();
    }

    private void btnVoltarActionPerformed(ActionEvent evt) {
        dispose();
    }
}
