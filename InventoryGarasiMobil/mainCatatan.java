/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tugas1;

import java.util.Scanner;

/**
 *
 * @author Aharfi
 */
public class mainCatatan {
    public static void main(String[]args){
        catatan ctt = new catatan();
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan nama reseller : ");
        String namaReseller = input.nextLine();
        System.out.println("Halo " + namaReseller + ", berikut catatan mobil di gudang :");
        ctt.cetakCatatan();
        
    }
}
