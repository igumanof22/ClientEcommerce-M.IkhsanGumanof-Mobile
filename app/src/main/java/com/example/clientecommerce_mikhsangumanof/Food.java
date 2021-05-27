package com.example.clientecommerce_mikhsangumanof;

public class Food {
    private int id;
    private String nama;
    private String ket;
    private double harga;
    private double promo;
    private String gambar;

    public Food(int id, String nama, String ket, double harga, double promo, String gambar){
        this.id = id;
        this.nama = nama;
        this.ket = ket;
        this.harga = harga;
        this.promo = promo;
        this.gambar = gambar;
    }

    public int getId() {
        return id;
    }

    String getNama() {
        return nama;
    }

    String getKet() {
        return ket;
    }

    double getHarga() {
        return harga;
    }

    double getPromo() {
        return promo;
    }

    String getGambar() {
        return gambar;
    }
}
