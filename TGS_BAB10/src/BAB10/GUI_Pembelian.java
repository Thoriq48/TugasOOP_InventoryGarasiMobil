/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package BAB10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Aharfi
 */
public class GUI_Pembelian extends javax.swing.JFrame {
String nama1, barang1, alamat1,qty1,harga1,pajak1,totalHarga1;
    /**
     * Creates new form GUI_Pembelian
     */
    public GUI_Pembelian() {
        initComponents();
        tampil();
        tampil_Barang();
    }
    public Connection conn;
    public void koneksi() throws SQLException {
        try {
            conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/oop_gudangpartpc?user=root&password=");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GUI_Pembelian.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            Logger.getLogger(GUI_Pembelian.class.getName()).log(Level.SEVERE, null, e);
        } catch (Exception es) {
            Logger.getLogger(GUI_Pembelian.class.getName()).log(Level.SEVERE, null, es);
        }
    }
    public void statusBeli(objPembelian pmbl) {
        pmbl.statusBeli();
    }
    public void tampil_Barang() {
            try {
                koneksi();
                String sql = "SELECT Nama FROM tb_gudang order by Nama asc";
                Statement stt = conn.createStatement();
                ResultSet res = stt.executeQuery(sql);
                while (res.next()) {
                    Object[] ob = new Object[3];
                    ob[0] = res.getString(1);
                    cmbBarang.addItem((String) ob[0]);
                }
                res.close();
                stt.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    public void tampil() {
        DefaultTableModel tabelhead = new DefaultTableModel();
        tabelhead.addColumn("Pembeli");
        tabelhead.addColumn("Barang");
        tabelhead.addColumn("Jumlah");
        tabelhead.addColumn("Alamat");
        tabelhead.addColumn("Harga");
        tabelhead.addColumn("Pajak");
        tabelhead.addColumn("TotalHarga");
        try {
            koneksi();
            String sql = "SELECT * FROM tb_pembelian";
            Statement stat = conn.createStatement();
            ResultSet res = stat.executeQuery(sql);
            while (res.next()) {
                tabelhead.addRow(new Object[]{res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getString(7),res.getString(8)});
            }
            TabelPembelian.setModel(tabelhead);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "BELUM TERKONEKSI");
        }
    }
     public void refresh() {
        new GUI_Pembelian().setVisible(true);
        this.setVisible(false);
    }
    public void insert() {
        String Pembeli = txtPembeli.getText();
        String Barang = (String) cmbBarang.getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());
        String Alamat = txtAlamat.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        double pajak = 0.03 * (harga * qty);
        double totalHarga = harga * qty + pajak;

            try {
        koneksi(); // Pastikan metode koneksi() didefinisikan dengan benar.
        PreparedStatement preparedStatement = conn.prepareStatement(
                "INSERT INTO tb_pembelian(Pembeli, Barang, Jumlah, Alamat, Harga, Pajak,TotalHarga ) VALUES (?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, Pembeli);
        preparedStatement.setString(2, Barang);
        preparedStatement.setInt(3, qty);
        preparedStatement.setString(4, Alamat);
        preparedStatement.setDouble(5, harga);
        preparedStatement.setDouble(6, pajak);
        preparedStatement.setDouble(7, totalHarga);

        preparedStatement.executeUpdate();
        preparedStatement.close();
        JOptionPane.showMessageDialog(null, "Berhasil Memasukkan Data Nilai!");
    } catch (SQLException e) {
        e.printStackTrace(); // Menampilkan detail kesalahan ke konsol untuk debugging.
        JOptionPane.showMessageDialog(null, "Terjadi Kesalahan Input!");
    }
    refresh(); // Pastikan metode refresh() didefinisikan dengan benar.
    }
    

    public void update() {
        String Pembeli = txtPembeli.getText();
        String Barang = (String) cmbBarang.getSelectedItem();
        int qty = Integer.parseInt(txtQty.getText());
        String Alamat = txtAlamat.getText();
        double harga = Double.parseDouble(txtHarga.getText());
        double pajak = 0.03 * (harga * qty);
        double totalHarga = harga*qty*pajak;
        String Status = "barang dengan merk " + cmbBarang.getSelectedItem() +" dikirim dari gudang, tujuan pengiriman ke penjual/seller";

        String Pembeli_lama = nama1;
        String Barang_lama = barang1;

        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("UPDATE tb_pembelian SET Pembeli='" + Pembeli + "'," + "Barang='" + Barang + "'"
                    + ",Jumlah='" + qty + "',Alamat='" + Alamat + "',Harga='" + harga + "',TotalHarga='" + totalHarga + "',Pajak='" + pajak + "',Status='" + Status + "' WHERE Pembeli ='" + Pembeli_lama + "' AND Barang='" + Barang_lama + "'");
            statement.close();
            conn.close();
            JOptionPane.showMessageDialog(null, "Update Data Pembelian!");
        } catch (Exception e) {
            System.out.println("Error : " + e);
        }
        refresh();
    }
    void itempilih() {
        txtPembeli.setText(nama1);
        cmbBarang.setSelectedItem(barang1);
        txtQty.setText(qty1);
        txtAlamat.setText(alamat1);
        txtHarga.setText(harga1);
        double pajak,totalHarga;
        pajak = Double.parseDouble(pajak1);
        totalHarga = Double.parseDouble(totalHarga1);
    }
    //masukkan method batal() 
    public void batal() {
        txtPembeli.setText("");
        txtQty.setText("");
        txtAlamat.setText("");
        txtHarga.setText("");
    }
    //masukkan method delete()
    public void delete() {
        int ok = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin akan menghapus data ?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (ok == 0) {
            try {
                String sql = "DELETE FROM tb_pembelian WHERE Pembeli='" + nama1 + "' AND Barang='" + cmbBarang.getSelectedItem() + "'";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil di hapus");
                batal();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data gagal di hapus");
            }
        }
        refresh();
    }
    //masukkan method cari()
    public void cari() {
        try {
            try ( Statement statement = conn.createStatement()) {
                String sql = "SELECT * FROM tb_pembelian WHERE `Pembeli` LIKE '%" + txtCari.getText() + "%'";
                ResultSet rs = statement.executeQuery(sql);
                //menampilkan data dari sql query
                if (rs.next()) // .next() = scanner method
                {
                    txtPembeli.setText(rs.getString(2));
                    cmbBarang.setSelectedItem(rs.getString(3));
                    txtQty.setText(rs.getString(4));
                    txtAlamat.setText(rs.getString(5));
                    txtHarga.setText(rs.getString(6));

                } else {
                    JOptionPane.showMessageDialog(null, "Data yang Anda cari tidak ada");
                }
            }
        } catch (Exception ex) {
            System.out.println("Error." + ex);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQty = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtAlamat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelPembelian = new javax.swing.JTable();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnBarang = new javax.swing.JButton();
        cmbBarang = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtPembeli = new javax.swing.JTextField();
        txtCari = new javax.swing.JTextField();
        btnClose = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Data Pembelian Part PC");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel4.setText("Qty Barang :");

        txtQty.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        btnSimpan.setText("SIMPAN");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnBatal.setText("BATAL");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel5.setText("Alamat :");

        txtAlamat.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel6.setText("Harga Barang / pcs :");

        txtHarga.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N

        TabelPembelian.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Pembeli", "Barang", "Jumlah", "Alamat", "Harga", "Pajak", "TotalHarga"
            }
        ));
        TabelPembelian.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelPembelianMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TabelPembelian);

        btnUpdate.setText("UPDATE");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("DELETE");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnBarang.setText("Nama Barang :");
        btnBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBarangActionPerformed(evt);
            }
        });

        cmbBarang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~Barang~" }));
        cmbBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbBarangActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        jLabel2.setText("Pembeli :");

        btnClose.setText("CLOSE");
        btnClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseActionPerformed(evt);
            }
        });

        btnSearch.setText("Search 🔍");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(124, 124, 124)
                                .addComponent(jLabel1)
                                .addGap(109, 109, 109))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel4)
                                                    .addComponent(jLabel5)
                                                    .addComponent(jLabel6)
                                                    .addComponent(jLabel2))
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtPembeli)
                                                    .addComponent(txtQty)
                                                    .addComponent(txtAlamat)
                                                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnBarang)
                                                .addGap(12, 12, 12)
                                                .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(45, 45, 45))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 795, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnClose)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnUpdate)
                                .addComponent(btnDelete)
                                .addComponent(btnBatal))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPembeli, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSimpan)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
          insert();
//        try {
//            // Menampilkan pesan dialog bahwa data telah ditambahkan ketabel
//            JOptionPane.showMessageDialog(null, "Data anda Ditambahkan Ke tabel");
//            // Mengambil model data dari tabel
//            DefaultTableModel dataModel = (DefaultTableModel) 
//            TabelPembelian.getModel();
//            // Inisialisasi sebuah ArrayList bernama 'list'
//            List list = new ArrayList<>();
//            // Mengatur tabel untuk membuat kolom dari model secara otomatis
//            TabelPembelian.setAutoCreateColumnsFromModel(true);
//            // Membuat instance dari kelas Mahasiswa
//            objPembelian pmbl = new objPembelian();
//            pmbl.dataPembeli(txtPembeli.getText());
//            pmbl.dataNama(txtBarang.getText());
//            pmbl.dataJumlah(Integer.parseInt(txtQty.getText()));
//            pmbl.dataAlamat(txtAlamat.getText());
//            pmbl.dataHarga(Integer.parseInt(txtHarga.getText()));
//            // Menambahkan data-data dari objek Mahasiswa ke dalam ArrayList 'list'
//            list.add(pmbl.cetakPembeli());
//            list.add(pmbl.cetakNama());
//            list.add( pmbl.cetakJumlah());
//            list.add( pmbl.cetakAlamat());
//            list.add(pmbl.cetakHarga());
//            double totalHarga = (pmbl.cetakHarga() * pmbl.cetakJumlah()) + pmbl.hitungPajak();
//            list.add(totalHarga);
//            list.add(pmbl.hitungPajak());
//            list.add("barang dengan merk " + pmbl.cetakNama() +" dikirim dari gudang, tujuan pengiriman ke penjual/seller");
//            pmbl.statusBeli();
//            // Menambahkan baris baru ke model tabel menggunakan data dari ArrayList 'list'
//            dataModel.addRow(list.toArray());
//            // Memanggil fungsi 'clear' untuk membersihkan nilai dari komponen
//            clear();
//        } catch (NumberFormatException e) {
//            System.out.println(e.getMessage());
//            JOptionPane.showMessageDialog(null, "Error: Masukkan jumlah dan harga yang valid!");
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
//        } finally {
//            // Fungsi untuk membersihkan input pada komponen setelah proses penambahan data selesai
//            clear();
//        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        update();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        batal();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBarangActionPerformed
        // TODO add your handling code here:
        new GUI_Gudang().setVisible(true);
    }//GEN-LAST:event_btnBarangActionPerformed

    private void cmbBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbBarangActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseActionPerformed
        // TODO add your handling code here:
        //exit
        dispose();
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_btnSearchActionPerformed

    private void TabelPembelianMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelPembelianMouseClicked
        // TODO add your handling code here:
        int tabel = TabelPembelian.getSelectedRow(); //0
        nama1 = TabelPembelian.getValueAt(tabel, 0).toString();
        barang1 = TabelPembelian.getValueAt(tabel, 1).toString();
        alamat1 = TabelPembelian.getValueAt(tabel, 2).toString();
        qty1 = TabelPembelian.getValueAt(tabel, 3).toString();
        harga1 = TabelPembelian.getValueAt(tabel, 4).toString();
        pajak1 = TabelPembelian.getValueAt(tabel, 5).toString();
        totalHarga1 = TabelPembelian.getValueAt(tabel, 6).toString();
        itempilih();
    }//GEN-LAST:event_TabelPembelianMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_Pembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_Pembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelPembelian;
    private javax.swing.JButton btnBarang;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnClose;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbBarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtPembeli;
    private javax.swing.JTextField txtQty;
    // End of variables declaration//GEN-END:variables
}
