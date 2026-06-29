package com.example.projeck_uas.data.model;

import java.io.Serializable;

public class FavoriteItem implements Serializable {
    private String id;
    private String nama;
    private String namaLatin;
    private String foto;
    private String status;
    private String tipe;
    private long savedAt;

    public FavoriteItem(String id, String nama, String namaLatin, String foto, String status, String tipe, long savedAt) {
        this.id = id;
        this.nama = nama;
        this.namaLatin = namaLatin;
        this.foto = foto;
        this.status = status;
        this.tipe = tipe;
        this.savedAt = savedAt;
    }

    // Getters
    public String getId() { return id; }
    public String getNama() { return nama; }
    public String getNamaLatin() { return namaLatin; }
    public String getFoto() { return foto; }
    public String getStatus() { return status; }
    public String getTipe() { return tipe; }
    public long getSavedAt() { return savedAt; }
}
