import java.awt.*;
import javax.swing.*;

public class WelcomeFrame extends JFrame {
    public WelcomeFrame() {
        setTitle("Toko Buah CERIA");
        setSize(600, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Panel utama dengan background gradient
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 223, 186); // Warna peach
                Color color2 = new Color(255, 248, 225); // Warna kuning muda
                GradientPaint gp = new GradientPaint(0, 0, color1, getWidth(), getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
        
        // Label selamat datang
        JLabel lblWelcome = new JLabel("Selamat datang di Toko Buah CERIA", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Serif", Font.BOLD, 28));
        lblWelcome.setForeground(new Color(139, 69, 19)); // Warna coklat tua
        lblWelcome.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
        mainPanel.add(lblWelcome, BorderLayout.CENTER);
        
        // Tombol mulai berbelanja
        JButton btnStart = new JButton("Mulai Berbelanja");
        btnStart.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnStart.setBackground(new Color(76, 175, 80)); // Warna hijau
        btnStart.setForeground(Color.WHITE);
        btnStart.setFocusPainted(false);
        btnStart.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnStart.setMargin(new Insets(0, 0, 30, 0));
        btnStart.addActionListener(e -> openStoreFrame());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnStart);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 0));
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        
        // Gambar buah (dekorasi)
        JLabel lblFruitIcon = new JLabel("🍎🍊🍇🍌", SwingConstants.CENTER);
        lblFruitIcon.setFont(new Font("Serif", Font.PLAIN, 48));
        lblFruitIcon.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        mainPanel.add(lblFruitIcon, BorderLayout.NORTH);
    }
    
    private void openStoreFrame() {
        StoreFrame storeFrame = new StoreFrame();
        storeFrame.setVisible(true);
        this.dispose();
    }
}