
import java.awt.*;
import java.text.NumberFormat;
import java.util.Map;
import javax.swing.*;



public class CashInputFrame extends JFrame {

    private double total;
    private Map<String, ItemKeranjang> keranjang;
    private StoreFrame storeFrame;
    private JFormattedTextField tfUang;
    private PaymentMethod paymentMethod;

    public CashInputFrame(double total, PaymentMethod paymentMethod,
            Map<String, ItemKeranjang> keranjang, StoreFrame storeFrame) {
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.keranjang = keranjang;
        this.storeFrame = storeFrame;

        setTitle("Pembayaran Cash");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new GridLayout(4, 1, 10, 10)) {
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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Total yang harus dibayar
        mainPanel.add(new JLabel("Total Pembayaran:"));
        mainPanel.add(new JLabel("Rp " + String.format("%,d", (int) total), SwingConstants.RIGHT));

        // Input uang
        mainPanel.add(new JLabel("Masukkan Uang:"));

        NumberFormat format = NumberFormat.getIntegerInstance();
        tfUang = new JFormattedTextField(format);
        tfUang.setValue((int) total); // Default nilai total
        mainPanel.add(tfUang);

        add(mainPanel, BorderLayout.CENTER);

        // Tombol bayar
        JButton btnBayar = new JButton("Bayar");
        btnBayar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnBayar.setBackground(new Color(0, 100, 0));
        btnBayar.setForeground(Color.WHITE);
        btnBayar.addActionListener(e -> prosesPembayaran());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnBayar);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void prosesPembayaran() {
        double uang = ((Number) tfUang.getValue()).doubleValue();
        if (uang < total) {
            JOptionPane.showMessageDialog(this, "Uang tidak cukup!");
            return;
        }

        paymentMethod.processPayment(total);
        ReceiptPrinter.printReceipt(total, uang, "Cash", keranjang); // Polimorfisme: perilaku berbeda
        new ResiFrame(total, uang, "Cash", keranjang, storeFrame).setVisible(true);
        this.dispose();
    }
}
