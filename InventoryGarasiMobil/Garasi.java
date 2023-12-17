/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

/**
 *
 * @author Aharfi
 */
public class Garasi {
    String typeMobil;
    String merk;
    String model;
    private String tahunKeluaran;
    int jumlahMobil,stokMobil;
    double harga;
    
    public void dataType(String typeMobil){
        this.typeMobil = typeMobil;
    }
    public void dataMerk(String merkMobil){
        this.merk = merkMobil;
    }
   public void dataModel(String modelMobil){
        this.model = modelMobil;
    }
    public void dataKeluaran(String tahunKeluar){
        this.tahunKeluaran = tahunKeluar;
    }
    public void dataJumlah(int jmlMobil){
        this.jumlahMobil = jmlMobil;
    }
    String cetakType(){
        return typeMobil;
    }
    String cetakMerk(){
        return merk;
    }
    String cetakModel(){
       return model;
    }
    String cetakKeluaran(){
       return tahunKeluaran;
    }
    int cetakJumlah(){
       return jumlahMobil;
    }
    
    public void SimpanMobil(){
        System.out.println("Garasi Penyimpan Mobil : " + merk + " " + model + "\n");
    }
    public void SimpanMobil(String tahunKeluar) {
        System.out.println("Garasi Penyimpan Mobil : " + merk + " " + model + " " + tahunKeluar + "\n");
    }

    public String getTypeMobil() {
        return typeMobil;
    }

    public void setTypeMobil() {
        this.typeMobil = "JDM,Eropa,Classic,SuperCar";
    }
    void TypeMobil(){
        System.out.println("mobil merupakan jenis kendaraan umum & pribadi");
    }
    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTahunKeluaran() {
        return tahunKeluaran;
    }

    public void setTahunKeluaran(String tahunKeluaran) {
        this.tahunKeluaran = tahunKeluaran;
    }

    public int getJumlahMobil() {
        return jumlahMobil;
    }

    public void setJumlahMobil(int jumlahMobil) {
        this.jumlahMobil = jumlahMobil;
    }
    double hitungPajak() {
        double pajak = 0.1 * harga;
        return pajak;
    }
    public Garasi(){
        this.typeMobil = "Jerman";
        this.merk = "mercy";
        this.model = "MG7";
        this.tahunKeluaran = "2010";
        this.jumlahMobil = 10;
    }
}
