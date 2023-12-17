/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

/**
 *
 * @author Aharfi
 */
public class mainPenjualan extends Garasi{
    public static void main(String args[]) {
        // TODO code application logic here
        Penjualan pembeli = new Penjualan();
        
        System.out.println("***************************************");
        System.out.println("          Data Pembeli Barang");
        System.out.println("***************************************");
        pembeli.datapembeli("Thoriq");
        pembeli.dataalamat("jl.Nun");
        pembeli.dataJumlah(5);
        System.out.println("Nama Pembeli: " + pembeli.cetakpembeli());
        System.out.println("Alamat Pembeli: " + pembeli.cetakalamat());
        System.out.println("Nama Barang : " + pembeli.cetakMerk());
        System.out.println("Jumlah Barang yang Dibeli: " + pembeli.cetakJumlah());
        
        Garasi pnjln = new Garasi();
        
        System.out.println("***************************************");
        System.out.println("          Transaksi Pembelian");
        System.out.println("***************************************");
        pembeli.belibarang(5);
        
        System.out.println("***************************************");
        System.out.println("          Pemberian Barang");
        System.out.println("***************************************");
        pembeli.pemberianBarang(pnjln, 5);

    }
}
