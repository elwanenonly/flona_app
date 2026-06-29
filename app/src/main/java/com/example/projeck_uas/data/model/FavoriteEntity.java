package com.example.projeck_uas.data.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorit")
public class FavoriteEntity {
    @PrimaryKey
    @NonNull
    private String id;
    private String nama;
    @ColumnInfo(name = "nama_latin")
    private String namaLatin;
    private String foto;
    private String status;
    private String tipe;
    @ColumnInfo(name = "saved_at")
    private long savedAt;

    public FavoriteEntity() {}

    // Getters and Setters
    @NonNull public String getId() { return id; }
    public void setId(@NonNull String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNamaLatin() { return namaLatin; }
    public void setNamaLatin(String namaLatin) { this.namaLatin = namaLatin; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getTipe() { return tipe; }
    public void setTipe(String tipe) { this.tipe = tipe; }

    public long getSavedAt() { return savedAt; }
    public void setSavedAt(long savedAt) { this.savedAt = savedAt; }
}
