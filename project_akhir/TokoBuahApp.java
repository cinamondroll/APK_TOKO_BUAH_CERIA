import javax.swing.*;


public class TokoBuahApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Tampilkan splash screen/welcome frame
            new WelcomeFrame().setVisible(true);
        });
    }
}