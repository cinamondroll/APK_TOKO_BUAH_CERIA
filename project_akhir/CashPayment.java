import java.util.Map;
import javax.swing.JOptionPane;

public class CashPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        JOptionPane.showMessageDialog(null, "Pembayaran cash sebesar Rp " + (int)amount);
    }

    @Override
    public void printReceipt(Map<String, ItemKeranjang> items, double amountPaid) {
        double total = items.values().stream()
            .mapToDouble(ItemKeranjang::getTotalHarga)
            .sum();
        double change = amountPaid - total;
        
        ReceiptPrinter.printReceipt(total, amountPaid, "Cash", items);
        System.out.println("Cetak resi CASH - Kembalian: Rp " + (int)change);
    }
}