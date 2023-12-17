/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

/**
 *
 * @author Aharfi
 */
public class Penjualan extends Garasi{
    private String namapembeli, alamat, no;
    private int jumlahbarang;
    
    void datapembeli (String namapembeli){
        this.namapembeli = namapembeli;
    }
    void dataalamat (String alamat) {
        this.alamat = alamat;
    }
    void dataNo (String no){
        this.no = no;
    }
    public void dataJumlah (int jumlahbarang){
        this.jumlahbarang = jumlahbarang;
    }
    void dataHarga (double harga){
        this.harga = harga;
    }
    String cetakpembeli (){
        return namapembeli;
    }
    String cetakalamat (){
        return alamat;
    }
    int cetakJumlah(){
        return jumlahbarang;
    }
    double cetakHarga(){
        return harga;
    }
    void belibarang(int jumlah) {
        if (jumlah <= jumlahMobil) {
            jumlahMobil -= jumlah;
            System.out.println(namapembeli + " telah memberi mobil dengan merek " + merk + "sejumlah : " + jumlahMobil);
        } else {
            System.out.println("Stok " + merk + " tidak mencukupi.");
        }
    }
    void pemberianBarang(Garasi penjual, int jumlah) {
        if (jumlah > 0) {
            jumlahMobil += jumlah;
            penjual.jumlahMobil -= jumlah;
            System.out.println("Gudang telah memberikan " + jumlah + " " + merk + " kepada penjual.");
            System.out.println("Penjual telah memberikan " + jumlah + " " + merk + "kepada pembeli atas nama : "+ namapembeli);
        } else {
            System.out.println("Jumlah barang yang diberikan harus lebih dari 0.");
        }
    }

    @Override
    double hitungPajak() {
        double pajak = 0.1 * (harga * jumlahbarang);
        return pajak;
    }
    
    Penjualan(){
        super();
        this.namapembeli = namapembeli;
        this.alamat = alamat;
        this.jumlahbarang = jumlahbarang;
    }
}
