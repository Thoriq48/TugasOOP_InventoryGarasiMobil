/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

/**
 *
 * @author Aharfi
 */
public class Mobil extends Garasi implements Berkendara{
    public Mobil(){
        this.merk = "Lamborghini";
        this.typeMobil = "SuperCar";
        this.model = "Racing";
        this.jumlahMobil = 5;
    }
    void TypeMobil(){
        System.out.println(typeMobil + " merupakan mobil sport dan terkencang");
    }

    @Override
    double hitungPajak() {
        double pajak = 0.3 * harga;
        return pajak;
    }

    @Override
    public void Berkendara() {
        System.out.println("Mobil dengan merk " + merk + " telah dikendarai oleh pembeli");
    }
    
}
