import java.util.Map;
import javax.swing.JOptionPane;

public class DebitPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        JOptionPane.showMessageDialog(null, "Pembayaran debit sebesar Rp " + (int)amount);
    }

    @Override
    public void printReceipt(Map<String, ItemKeranjang> items, double amountPaid) {
        
        ReceiptPrinter.printReceipt(amountPaid, amountPaid, "Debit", items);
        System.out.println("Cetak resi DEBIT - Dibayar lunas");
    }
}