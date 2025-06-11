import java.util.Map;
import javax.swing.JOptionPane;

public class DebitPayment implements PaymentMethod {
    @Override
    public void processPayment(double amount) {
        JOptionPane.showMessageDialog(null, "Pembayaran debit sebesar Rp " + (int)amount);
    }

    @Override
    public void printReceipt(double total, double amountPaid, String paymentMethod,
            Map<String, ItemKeranjang> items) {
        
        ReceiptPrinter.printReceipt(total, total, paymentMethod, items);;
        System.out.println("Cetak resi DEBIT - Dibayar lunas");
    }
}