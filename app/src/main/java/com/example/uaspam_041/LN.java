package com.example.uaspam_041;

public class LN {

    String id_novel, judul, penulis, sinopsis;

    public LN() {

    }

    public LN(String id_novel, String judul, String penulis, String sinopsis) {
        this.id_novel = id_novel;
        this.judul = judul;
        this.penulis = penulis;
        this.sinopsis = sinopsis;
    }

    public String getId_novel() {
        return id_novel;
    }

    public void setId_novel(String id_novel) {
        this.id_novel = id_novel;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
