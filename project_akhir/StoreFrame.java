

import java.awt.*;
import java.util.*;
import javax.swing.*;


public class StoreFrame extends JFrame {

    private Map<String, ItemKeranjang> keranjang = new HashMap<>();
    private JLabel lblInfo = new JLabel("Keranjang: 0 item");

    // Daftar buah dan harga
    private final String[][] DAFTAR_BUAH = {
        {"Apel", "5000"},
        {"Jeruk", "6000"},
        {"Mangga", "8000"},
        {"Pisang", "4000"},
        {"Anggur", "15000"},
        {"Semangka", "10000"},
        {"Melon", "12000"},
        {"Strawberry", "20000"},
        {"Durian", "30000"},
        {"Nanas", "7000"}
    };

    public StoreFrame() {
        this.keranjang = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Toko Buah - Store");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel() {
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
        headerPanel.add(new JLabel("TOKO BUAH SEGAR"));
        add(headerPanel, BorderLayout.NORTH);

        // Panel buah
        JPanel buahPanel = new JPanel(new GridLayout(5, 2, 10, 10)) {
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
        buahPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (String[] buah : DAFTAR_BUAH) {
            JPanel card = createBuahCard(buah[0], Double.parseDouble(buah[1]));
            buahPanel.add(card);
        }

        JScrollPane scrollPane = new JScrollPane(buahPanel);
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
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
        };;
        footerPanel.add(lblInfo);

        JButton btnKeranjang = new JButton("Lihat Keranjang");
        btnKeranjang.addActionListener(e -> openCartFrame());
        btnKeranjang.setBackground(new Color(255, 248, 225));
        footerPanel.add(btnKeranjang);

        JButton btnBayar = new JButton("Lanjut Pembayaran");
        btnBayar.addActionListener(e -> openPaymentFrame());
        btnBayar.setBackground(new Color(76, 175, 80));
        footerPanel.add(btnBayar);

        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createBuahCard(String nama, double harga) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel lblNama = new JLabel(nama, SwingConstants.CENTER);
        JLabel lblHarga = new JLabel("Rp " + String.format("%,d", (int) harga), SwingConstants.CENTER);
        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBackground(new Color(255, 248, 225));
        btnTambah.addActionListener(e -> {
            tambahKeKeranjang(nama, harga);
            lblInfo.setText("Keranjang: " + keranjang.size() + " item");
        });

        JPanel centerPanel = new JPanel(new GridLayout(2, 1)){
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
        centerPanel.add(lblNama);
        centerPanel.add(lblHarga);

        card.add(centerPanel, BorderLayout.CENTER);
        card.add(btnTambah, BorderLayout.SOUTH);

        return card;
    }

    private void tambahKeKeranjang(String nama, double harga) {
        if (keranjang.containsKey(nama)) {
            ItemKeranjang item = keranjang.get(nama);
            item.tambahJumlah();
        } else {
            keranjang.put(nama, new ItemKeranjang(nama, 1, harga));
        }
    }

    private void openCartFrame() {
        CartFrame cartFrame = new CartFrame(keranjang, this);
        cartFrame.setVisible(true);
        this.setVisible(false);
    }

    private void openPaymentFrame() {
        if (keranjang.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Keranjang masih kosong!");
            return;
        }
        PaymentFrame paymentFrame = new PaymentFrame(keranjang, this);
        paymentFrame.setVisible(true);
        this.setVisible(false);
    }

    public void updateKeranjang(Map<String, ItemKeranjang> updatedKeranjang) {
        this.keranjang = updatedKeranjang;
        lblInfo.setText("Keranjang: " + keranjang.size() + " item");
    }

    public void resetKeranjang() {
        keranjang.clear();
        lblInfo.setText("Keranjang: 0 item");
    }
}
