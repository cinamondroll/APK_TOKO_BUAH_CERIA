
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReceiptPrinter {

    public static void printReceipt(double total, double uangBayar, String paymentMethod,
            Map<String, ItemKeranjang> keranjang) {
        try {
            // Buat direktori receipts jika belum ada
            new File("receipts").mkdir();

            // Format nama file dengan timestamp
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = "receipts/Receipt_" + timestamp + ".txt";

            FileWriter writer = new FileWriter(fileName);

            // Header
            writer.write("==========================================\n");
            writer.write("             TOKO BUAH CERIA              \n");
            writer.write("==========================================\n");
            writer.write("Tanggal: " + new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()) + "\n");
            writer.write("------------------------------------------\n");

            // Daftar belanjaan
            writer.write(String.format("%-15s %5s %10s %10s\n", "Nama Buah", "Qty", "Harga", "Total"));
            writer.write("------------------------------------------\n");

            for (ItemKeranjang item : keranjang.values()) {
                writer.write(String.format("%-15s %5d %10s %10s\n",
                        item.getNama(),
                        item.getJumlah(),
                        "Rp" + (int) item.getHargaSatuan(),
                        "Rp" + (int) item.getTotalHarga()));
            }

            writer.write("------------------------------------------\n");

            // Total pembayaran
            writer.write(String.format("%-32s %10s\n", "Total:", "Rp" + (int) total));

            // Detail pembayaran
            writer.write(String.format("%-32s %10s\n", "Metode Pembayaran:", paymentMethod));
            writer.write(String.format("%-32s %10s\n", "Jumlah Dibayar:", "Rp" + (int) uangBayar));

            if (paymentMethod.equals("Cash")) {
                double kembalian = uangBayar - total;
                writer.write(String.format("%-32s %10s\n", "Kembalian:", "Rp" + (int) kembalian));
            }

            writer.write("==========================================\n");
            writer.write("           TERIMA KASIH TELAH             \n");
            writer.write("            BERBELANJA DISINI             \n");
            writer.write("==========================================\n");

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
