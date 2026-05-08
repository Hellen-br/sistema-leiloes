import view.cadastroVIEW;

/**
 * Ponto de entrada do sistema de leilões.
 * A tela cadastroVIEW é definida como tela principal.
 */
public class Main {

    public static void main(String[] args) {
        // Garante que a UI seja criada na Event Dispatch Thread
        java.awt.EventQueue.invokeLater(() -> {
            new cadastroVIEW().setVisible(true);
        });
    }
}
