package com.example.projeck_uas.data.model;

import java.util.ArrayList;
import java.util.List;

public class DataMapper {
    public static EndemikItem entityToDomain(EndemikEntity entity) {
        EndemikItem item = new EndemikItem();
        item.setId(entity.getId());
        item.setTipe(entity.getTipe() != null ? entity.getTipe() : "");
        item.setNama(entity.getNama() != null ? entity.getNama() : "");
        item.setNamaLatin(entity.getNamaLatin() != null ? entity.getNamaLatin() : "");
        item.setFamili(entity.getFamili() != null ? entity.getFamili() : "");
        item.setGenus(entity.getGenus() != null ? entity.getGenus() : "");
        item.setDeskripsi(entity.getDeskripsi() != null ? entity.getDeskripsi() : "");
        item.setAsal(entity.getAsal() != null ? entity.getAsal() : "");
        item.setSebaran(entity.getSebaran() != null ? entity.getSebaran() : "");
        item.setFoto(entity.getFoto() != null ? entity.getFoto() : "");
        item.setSumberFoto(entity.getSumberFoto() != null ? entity.getSumberFoto() : "");
        item.setVidio(entity.getVidio() != null ? entity.getVidio() : "");
        item.setSumberVidio(entity.getSumberVidio() != null ? entity.getSumberVidio() : "");
        item.setStatus(entity.getStatus() != null ? entity.getStatus() : "Aman");
        return item;
    }

    public static List<EndemikItem> entitiesToDomain(List<EndemikEntity> entities) {
        List<EndemikItem> items = new ArrayList<>();
        if (entities != null) {
            for (EndemikEntity entity : entities) {
                items.add(entityToDomain(entity));
            }
        }
        return items;
    }

    public static EndemikEntity responseToEntity(EndemikResponse response) {
        EndemikEntity entity = new EndemikEntity();
        entity.setId(response.id != null ? response.id : "");
        entity.setTipe(response.tipe != null ? response.tipe : "");
        entity.setNama(response.nama != null ? response.nama : "");
        entity.setNamaLatin(response.nama_latin != null ? response.nama_latin : "");
        entity.setFamili(response.famili != null ? response.famili : "");
        entity.setGenus(response.genus != null ? response.genus : "");
        entity.setDeskripsi(response.deskripsi != null ? response.deskripsi : "");
        entity.setAsal(response.asal != null ? response.asal : "");
        entity.setSebaran(response.sebaran != null ? response.sebaran : "");
        entity.setFoto(response.foto != null ? response.foto : "");
        entity.setSumberFoto(response.sumber_foto != null ? response.sumber_foto : "");
        entity.setVidio(response.vidio != null ? response.vidio : "");
        entity.setSumberVidio(response.sumber_vidio != null ? response.sumber_vidio : "");
        entity.setStatus(response.status != null ? response.status : "Aman");
        return entity;
    }

    public static FavoriteEntity domainToFavoriteEntity(EndemikItem item) {
        FavoriteEntity entity = new FavoriteEntity();
        entity.setId(item.getId());
        entity.setNama(item.getNama());
        entity.setNamaLatin(item.getNamaLatin());
        entity.setFoto(item.getFoto());
        entity.setStatus(item.getStatus());
        entity.setTipe(item.getTipe());
        entity.setSavedAt(System.currentTimeMillis());
        return entity;
    }
}
