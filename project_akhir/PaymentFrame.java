
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;



public class PaymentFrame extends JFrame {
    private Map<String, ItemKeranjang> keranjang;
    private StoreFrame storeFrame;
    private ButtonGroup paymentGroup;
    private JRadioButton cashRadio, debitRadio;
    private PaymentMethod paymentMethod; // Interface sebagai tipe data

    public PaymentFrame(Map<String, ItemKeranjang> keranjang, StoreFrame storeFrame) {
        this.keranjang = keranjang;
        this.storeFrame = storeFrame;
        
        setTitle("Toko Buah - Pembayaran");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        // Header
        add(new JLabel("Pembayaran", SwingConstants.CENTER){
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
        }, BorderLayout.NORTH);
        
        // Tabel pembayaran
        JTable table = new JTable(new PaymentTableModel(new ArrayList<>(keranjang.values())));
        table.setRowHeight(30);
        table.setBackground(new Color(255, 248, 225));
        JScrollPane scrollPane = new JScrollPane(table) {
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
        add(scrollPane, BorderLayout.CENTER);
        
        // Total pembayaran
        double total = keranjang.values().stream()
                .mapToDouble(ItemKeranjang::getTotalHarga)
                .sum();
        
        JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
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
        totalPanel.add(new JLabel("Total Pembayaran: Rp " + String.format("%,d", (int) total)));
        
        // Panel metode pembayaran
        JPanel paymentPanel = new JPanel(new GridLayout(2, 1)) {
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
        paymentPanel.setBorder(BorderFactory.createTitledBorder("Metode Pembayaran"));
        
        cashRadio = new JRadioButton("Cash");
        debitRadio = new JRadioButton("Debit");
        paymentGroup = new ButtonGroup();
        cashRadio.setBackground(new Color(255, 248, 225));
        debitRadio.setBackground(new Color(255, 248, 225));
        paymentGroup.add(cashRadio);
        paymentGroup.add(debitRadio);
        debitRadio.setSelected(true); // Default debit
        
        paymentPanel.add(cashRadio);
        paymentPanel.add(debitRadio);
        
        // Footer
        JPanel footerPanel = new JPanel(new BorderLayout()) {
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
        
        JPanel topFooter = new JPanel(new BorderLayout()) {
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
        topFooter.add(totalPanel, BorderLayout.CENTER);
        topFooter.add(paymentPanel, BorderLayout.SOUTH);
        footerPanel.add(topFooter, BorderLayout.CENTER);
        
        JButton btnLanjut = new JButton("Lanjutkan Pembayaran");
        btnLanjut.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnLanjut.setBackground(new Color(0, 150, 0));
        btnLanjut.setForeground(Color.WHITE);
        btnLanjut.addActionListener(e -> lanjutkanPembayaran(total));
        
        JPanel buttonPanel = new JPanel() {
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
        buttonPanel.add(btnLanjut);
        footerPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(footerPanel, BorderLayout.SOUTH);
    }
    
    private void lanjutkanPembayaran(double total) {
        if (cashRadio.isSelected()) {
            paymentMethod = new CashPayment(); // Polimorfisme: CashPayment
            new CashInputFrame(total, paymentMethod, keranjang, storeFrame).setVisible(true);
        } else {
            paymentMethod = new DebitPayment(); // Polimorfisme: DebitPayment
            paymentMethod.processPayment(total);
            ReceiptPrinter.printReceipt(total, total, "Debit", keranjang);
            new ResiFrame(total, total, "Debit", keranjang, storeFrame).setVisible(true);
        }
        this.dispose();
    }
    
    // Custom Table Model
    static class PaymentTableModel extends AbstractTableModel {
        private List<ItemKeranjang> items;
        private String[] columns = {"Nama Buah", "Jumlah", "Total Harga"};
        
        public PaymentTableModel(List<ItemKeranjang> items) {
            this.items = items;
        }
        
        @Override
        public int getRowCount() {
            return items.size();
        }
        
        @Override
        public int getColumnCount() {
            return columns.length;
        }
        
        @Override
        public String getColumnName(int column) {
            return columns[column];
        }
        
        @Override
        public Object getValueAt(int row, int column) {
            ItemKeranjang item = items.get(row);
            return switch (column) {
                case 0 -> item.getNama();
                case 1 -> item.getJumlah();
                case 2 -> "Rp " + String.format("%,d", (int) item.getTotalHarga());
                default -> null;
            };
        }
    }
}