

public class ItemKeranjang {
    private String nama;
    private int jumlah;
    private double hargaSatuan;

    public ItemKeranjang(String nama, int jumlah, double hargaSatuan) {
        this.nama = nama;
        this.jumlah = jumlah;
        this.hargaSatuan = hargaSatuan;
    }

    public String getNama() { return nama; }
    public int getJumlah() { return jumlah; }
    public double getHargaSatuan() { return hargaSatuan; }
    public double getTotalHarga() { return jumlah * hargaSatuan; }
    
    public void tambahJumlah() { jumlah++; }
    public void setJumlah(int jumlah) { this.jumlah = jumlah; }
}