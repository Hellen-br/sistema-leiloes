package view;

import dao.ProdutoDAO;
import model.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Tela de cadastro de produtos — tela principal do sistema.
 */
public class cadastroVIEW extends javax.swing.JFrame {

    // ---- Componentes ----
    private JLabel      lblTitulo;
    private JLabel      lblNome;
    private JLabel      lblValor;
    private JLabel      lblStatus;
    private JTextField  txtNome;
    private JTextField  txtValor;
    private JComboBox<String> cmbStatus;
    private JButton     btnSalvar;
    private JButton     btnListar;

    public cadastroVIEW() {
        initComponents();
        setLocationRelativeTo(null);   // centraliza na tela
    }

    private void initComponents() {
        setTitle("Casa de Leilões — Cadastro de Produto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(null);
        setResizable(false);

        // Título
        lblTitulo = new JLabel("Cadastro de Produto");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitulo.setBounds(110, 15, 220, 25);
        add(lblTitulo);

        // Nome
        lblNome = new JLabel("Nome:");
        lblNome.setBounds(30, 60, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(120, 60, 230, 25);
        add(txtNome);

        // Valor
        lblValor = new JLabel("Valor (R$):");
        lblValor.setBounds(30, 100, 80, 25);
        add(lblValor);

        txtValor = new JTextField();
        txtValor.setBounds(120, 100, 230, 25);
        add(txtValor);

        // Status
        lblStatus = new JLabel("Status:");
        lblStatus.setBounds(30, 140, 80, 25);
        add(lblStatus);

        cmbStatus = new JComboBox<>(new String[]{"A Venda", "Vendido"});
        cmbStatus.setBounds(120, 140, 230, 25);
        add(cmbStatus);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(80, 200, 100, 35);
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);
        add(btnSalvar);

        // Botão Listagem
        btnListar = new JButton("Ver Listagem");
        btnListar.setBounds(210, 200, 120, 35);
        btnListar.addActionListener(this::btnListarActionPerformed);
        add(btnListar);
    }

    /**
     * Evento do botão Salvar — valida campos, persiste no banco e exibe feedback.
     */
    private void btnSalvarActionPerformed(ActionEvent evt) {
        String nome   = txtNome.getText().trim();
        String valorStr = txtValor.getText().trim();
        String status = (String) cmbStatus.getSelectedItem();

        // Validação dos campos obrigatórios
        if (nome.isEmpty() || valorStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Preencha todos os campos antes de salvar.",
                    "Campos obrigatórios",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Validação do valor numérico — correção de bug em validação do campo valor
        int valor;
        try {
            valor = Integer.parseInt(valorStr);
            if (valor < 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Valor inválido. Informe um número inteiro positivo.",
                    "Erro de Validação",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Persiste no banco
        Produto produto = new Produto(nome, valor, status);
        ProdutoDAO dao  = new ProdutoDAO();
        boolean sucesso = dao.salvar(produto);

        if (sucesso) {
            JOptionPane.showMessageDialog(this,
                    "Produto cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
            // Limpa os campos após salvar
            txtNome.setText("");
            txtValor.setText("");
            cmbStatus.setSelectedIndex(0);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Erro ao realizar o cadastro. Verifique a conexão com o banco de dados.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Abre a tela de listagem de produtos.
     */
    private void btnListarActionPerformed(ActionEvent evt) {
        listagemVIEW lista = new listagemVIEW();
        lista.setVisible(true);
    }

    // Ponto de entrada para testes isolados da tela
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> new cadastroVIEW().setVisible(true));
    }
}
