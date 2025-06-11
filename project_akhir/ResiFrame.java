
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;



public class ResiFrame extends JFrame {

    private double total;
    private double uangBayar;
    private String paymentMethod;
    private Map<String, ItemKeranjang> keranjang;
    private StoreFrame storeFrame;

    public ResiFrame(double total, double uangBayar, String paymentMethod,
            Map<String, ItemKeranjang> keranjang, StoreFrame storeFrame) {
        this.total = total;
        this.uangBayar = uangBayar;
        this.paymentMethod = paymentMethod;
        this.keranjang = keranjang;
        this.storeFrame = storeFrame;

        setTitle("Resi Pembayaran");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel headerPanel = new JPanel(new BorderLayout()) {
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
        headerPanel.add(new JLabel("TOKO BUAH CERIA", SwingConstants.CENTER), BorderLayout.NORTH);
        headerPanel.add(new JLabel("Resi Pembayaran", SwingConstants.CENTER), BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Tabel resi
        JTable table = new JTable(new ReceiptTableModel(new ArrayList<>(keranjang.values())));
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

        // Panel detail pembayaran
        JPanel detailPanel = new JPanel(new GridLayout(5, 2, 5, 5)) {
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
        detailPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        detailPanel.add(new JLabel("Total Pembayaran:"));
        detailPanel.add(new JLabel("Rp " + String.format("%,d", (int) total), SwingConstants.RIGHT));

        detailPanel.add(new JLabel("Metode Pembayaran:"));
        detailPanel.add(new JLabel(paymentMethod, SwingConstants.RIGHT));

        detailPanel.add(new JLabel("Jumlah Dibayar:"));
        detailPanel.add(new JLabel("Rp " + String.format("%,d", (int) uangBayar), SwingConstants.RIGHT));

        if (paymentMethod.equals("Cash")) {
            double kembalian = uangBayar - total;
            detailPanel.add(new JLabel("Kembalian:"));
            detailPanel.add(new JLabel("Rp " + String.format("%,d", (int) kembalian), SwingConstants.RIGHT));
        } else {
            detailPanel.add(new JLabel(""));
            detailPanel.add(new JLabel(""));
        }

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
        footerPanel.add(detailPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)) {
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

        JButton btnSelesai = new JButton("Selesai");
        btnSelesai.setBackground(Color.RED);
        btnSelesai.setForeground(Color.WHITE);
        btnSelesai.addActionListener(e -> System.exit(0));

        JButton btnBelanjaLagi = new JButton("Belanja Lagi");
        btnBelanjaLagi.setBackground(new Color(0, 100, 0));
        btnBelanjaLagi.setForeground(Color.WHITE);
        btnBelanjaLagi.addActionListener(e -> {
            storeFrame.resetKeranjang();
            storeFrame.setVisible(true);
            this.dispose();
        });

        buttonPanel.add(btnSelesai);
        buttonPanel.add(btnBelanjaLagi);

        footerPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(footerPanel, BorderLayout.SOUTH);

        JOptionPane.showMessageDialog(this,
                "Resi telah dicetak ke file text",
                "Cetak Resi", JOptionPane.INFORMATION_MESSAGE);
    }

    // Custom Table Model untuk resi
    static class ReceiptTableModel extends AbstractTableModel {

        private List<ItemKeranjang> items;
        private String[] columns = {"Nama Buah", "Jumlah", "Harga Satuan", "Total"};

        public ReceiptTableModel(List<ItemKeranjang> items) {
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
                case 0 ->
                    item.getNama();
                case 1 ->
                    item.getJumlah();
                case 2 ->
                    "Rp " + String.format("%,d", (int) item.getHargaSatuan());
                case 3 ->
                    "Rp " + String.format("%,d", (int) item.getTotalHarga());
                default ->
                    null;
            };
        }
    }
}
