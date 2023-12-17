/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BAB10;

/**
 *
 * @author Aharfi
 */
public class objPembelian extends Gudang implements statusBeli{
    String namaPembeli;
    private String alamat;
    int jumlahBrg;
    
    public void dataPembeli(String namaPembeli){
        this.namaPembeli = namaPembeli;
    }
    public void dataAlamat(String alamat){
        this.alamat = alamat;
    }
    public void dataJumlah(int jumlahBrg){
        this.jumlahBrg = jumlahBrg;
    }
    String cetakPembeli() {
        return namaPembeli;
    }

    String cetakAlamat() {
        return alamat;
    }
    int cetakJumlah() {
        return jumlahBrg;
    }
    void beliBarang(int jumlah) {
        if (jumlah <= stok) {
            stok -= jumlah;
            System.out.println(namaPembeli + " telah membeli " + jumlah + " " + nama + " seharga " + (jumlah * harga));
        } else {
            System.out.println("Stok " + nama + " tidak mencukupi.");
        }
    }
    void pemberianBarang(Gudang penjual, int jumlah) {
        if (jumlah > 0) {
            stok += jumlah;
            penjual.stok -= jumlah;
            System.out.println("Gudang telah memberikan " + jumlah + " " + nama + " kepada penjual.");
            System.out.println("Penjual telah memberikan " + jumlah + " " + nama + "kepada pembeli atas nama : "+ namaPembeli);
        } else {
            System.out.println("Jumlah barang yang diberikan harus lebih dari 0.");
        }
    }

    @Override
    double hitungPajak() {
        double pajak = 0.03 * (harga * jumlahBrg);
        return pajak;
    }
    
    objPembelian(){
        super();
        this.namaPembeli = "";
        this.alamat = "";
        this.jumlahBrg = 0;
    }
    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    @Override
    public void statusBeli() {
        System.out.println("barang " + nama +" dikirim dari gudang, tujuan pengiriman ke penjual/seller");
    }
}
