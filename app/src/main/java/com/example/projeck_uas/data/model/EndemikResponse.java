package com.example.projeck_uas.data.model;

import com.google.gson.annotations.SerializedName;

public class EndemikResponse {
    @SerializedName("id")
    public String id;
    
    @SerializedName("tipe")
    public String tipe;
    
    @SerializedName("nama")
    public String nama;
    
    @SerializedName("nama_latin")
    public String nama_latin;
    
    @SerializedName("famili")
    public String famili;
    
    @SerializedName("genus")
    public String genus;
    
    @SerializedName("deskripsi")
    public String deskripsi;
    
    @SerializedName("asal")
    public String asal;
    
    @SerializedName("sebaran")
    public String sebaran;
    
    @SerializedName("foto")
    public String foto;
    
    @SerializedName("sumber_foto")
    public String sumber_foto;
    
    @SerializedName("vidio")
    public String vidio;
    
    @SerializedName("sumber_vidio")
    public String sumber_vidio;
    
    @SerializedName("status")
    public String status;
}
