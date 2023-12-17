/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

/**
 *
 * @author Aharfi
 */
public class main {
    public static void main(String[]args){
        //meinisialisasi object
        Garasi mobil = new Garasi();
        
        System.out.println("==============================================");
        System.out.println("               GARASI MOBIL");
        System.out.println("==============================================");
        System.out.println("Type Mobil : " + mobil.cetakType());
        System.out.println("Merek Mobil : " + mobil.cetakMerk());
        System.out.println("Model Mobil : " + mobil.cetakModel());
        System.out.println("Tahun Keluaran Mobil : " + mobil.cetakKeluaran());
        System.out.println("Jumlah Mobil : " + mobil.cetakJumlah());
        
        mobil.SimpanMobil();
    }
}

