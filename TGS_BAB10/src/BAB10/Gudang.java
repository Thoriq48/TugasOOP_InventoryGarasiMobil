/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BAB10;

/**
 *
 * @author Aharfi
 */
public class Gudang {
    String nama,merk,jenis;
    double harga;
    int stok,restok;
    
    void dataNama(String nama){
        this.nama = nama;
    }
    void dataMerk(String merek){
        this.merk = merek;
    }
    void dataJenis(String jenis){
        this.jenis = jenis;
    }
    void dataHarga(double harga){
        this.harga = harga;
    }
    void dataStok(int stok){
        this.stok = stok;
    }
    void dataRestok(int restok){
        this.restok = restok;
    }
    String cetakNama(){
        return nama;
    }
    String cetakMerk(){
        return merk;
    }
    String cetakJenis(){
        return jenis;
    }
    double cetakHarga(){
        return harga;
    }
    int cetakStok(){
        return stok;
    }
    int cetakRestok(){
        return restok;
    }
    //polimorfisme
    void setNamaBarang(){
        this.nama = "VGA,Processor,PSU,RAM,ROM,Motherboard,Case";
    }
    void cekBarang(){
        System.out.println("Pengecekan Barang Secara Umum");
    }
    //done
    void Pengecekan(){
        if(stok > 0){
            System.out.println("Barang " + nama + "Tersedia.");
        }else{
            System.out.println("Barang " + nama + "Tidak tersedia.");
        }
    }
    //overriding perhitungan pajak barang
    double hitungPajak() {
        double pajak = 0.03 * harga;
        return pajak;
    }
    //overloading kelola untuk mengelola stok
    int kelola(){
        stok = cetakStok();
        if (stok == 0) {
            System.out.println("Stok Part pc : Barang tidak tersedia");
        } else {
            System.out.println("Stok Part pc : " + stok );
        }
        return stok;
    }
    //overloading kelola untuk mengelola restok
    int kelola(int tambah){
        tambah = cetakStok() + cetakRestok();
        return tambah;
    }
    Gudang(){
        this.nama = "Ryzen 5 4500";
        this.merk = "AMD";
        this.jenis = "Processor";
        this.harga = 1250000;
        this.stok = 5;
    }
}
