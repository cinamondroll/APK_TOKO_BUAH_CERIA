
import java.util.Map;

public interface PaymentMethod {

    void processPayment(double amount);

    void printReceipt(Map<String, ItemKeranjang> items, double amountPaid);
}
