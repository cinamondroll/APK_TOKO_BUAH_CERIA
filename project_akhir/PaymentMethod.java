
import java.util.Map;

public interface PaymentMethod {

    void processPayment(double amount);

    void printReceipt(double total, double uangBayar, String paymentMethod,
            Map<String, ItemKeranjang> keranjang);
}
