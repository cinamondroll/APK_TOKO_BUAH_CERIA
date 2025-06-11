

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.*;


public class CartFrame extends JFrame {

    private Map<String, ItemKeranjang> keranjang;
    private JTable table;
    private KeranjangTableModel tableModel;
    private StoreFrame storeFrame;

    public CartFrame(Map<String, ItemKeranjang> keranjang, StoreFrame storeFrame) {
        this.keranjang = new HashMap<>(keranjang);
        this.storeFrame = storeFrame;

        setTitle("Toko Buah - Keranjang");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        add(new JLabel("Keranjang Belanja", SwingConstants.CENTER), BorderLayout.NORTH);

        // Tabel keranjang
        tableModel = new KeranjangTableModel(new ArrayList<>(this.keranjang.values()));
        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setBackground(new Color(255, 248, 225));

        // Tombol hapus
        TableColumn hapusColumn = table.getColumnModel().getColumn(2);
        hapusColumn.setCellRenderer(new ButtonRenderer());
        hapusColumn.setCellEditor(new ButtonEditor(new JCheckBox(), this));

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
        };
        JButton btnKembali = new JButton("Kembali ke Toko");
        btnKembali.addActionListener(e -> kembaliKeStore());
        btnKembali.setBackground(new Color(255, 248, 225));
        footerPanel.add(btnKembali);
        add(footerPanel, BorderLayout.SOUTH);
    }

    public void hapusItem(String namaBuah) {
        keranjang.remove(namaBuah);
        refreshTable();
    }

    public void refreshTable() {
        tableModel.setItems(new ArrayList<>(keranjang.values()));
        tableModel.fireTableDataChanged();
    }

    private void kembaliKeStore() {
        storeFrame.updateKeranjang(keranjang);
        storeFrame.setVisible(true);
        this.dispose();
    }

    // Custom Table Model
    class KeranjangTableModel extends AbstractTableModel {

        private List<ItemKeranjang> items;
        private String[] columns = {"Nama Buah", "Jumlah", "Aksi"};

        public KeranjangTableModel(List<ItemKeranjang> items) {
            this.items = items;
        }

        public void setItems(List<ItemKeranjang> items) {
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
                    "Hapus";
                default ->
                    null;
            };
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return column == 2; // Hanya kolom aksi yang bisa di-edit
        }
    }

    // Renderer untuk tombol
    static class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor untuk tombol hapus
    static class ButtonEditor extends AbstractCellEditor implements TableCellEditor, ActionListener {

        private JButton button;
        private String namaBuah;
        private CartFrame cartFrame;
        private int currentRow;

        public ButtonEditor(JCheckBox checkBox, CartFrame cartFrame) {
            this.cartFrame = cartFrame;

            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(this);
            button.setBackground(new Color(255, 248, 225));
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            namaBuah = (String) table.getValueAt(row, 0);
            currentRow = row;
            button.setText(value == null ? "" : value.toString());
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return button.getText();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            fireEditingStopped();
            cartFrame.hapusItem(namaBuah);
        }
    }
}
