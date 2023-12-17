/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

/**
 *
 * @author Aharfi
 */
public class catatan extends Garasi {
    String reseller;
    int jumlahbarang;
    void dataSeller(String reseller){
        this.reseller = reseller;
    }
    String cetakSeller(){
       return reseller;
    }
    catatan(){
        super();
        this.stokMobil = jumlahMobil;
    }
    void stok(){
        if(stokMobil < jumlahMobil){
            System.out.println("Stok Mobil Belum Tersedia");
        }else{
            System.out.println("Stok Mobil Tersedia");
        }
    }
    void cetakCatatan(){
        System.out.println("Merek Mobil : " + super.cetakMerk());
        System.out.println("Model Mobil : " + super.cetakModel());
        System.out.println("Tahun Keluaran : " + super.cetakKeluaran());
        stok();
        System.out.println("Jumlah Mobil : " + stokMobil);
    }
}
