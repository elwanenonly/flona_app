package com.example.projeck_uas.data;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import com.example.projeck_uas.data.local.EndemikDao;
import com.example.projeck_uas.data.model.*;
import com.example.projeck_uas.data.remote.EndemikApi;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import retrofit2.Response;

public class EndemikRepository {
    private final EndemikApi api;
    private final EndemikDao dao;
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public EndemikRepository(EndemikApi api, EndemikDao dao) {
        this.api = api;
        this.dao = dao;
    }

    public void refreshEndemikData() {
        executor.execute(() -> {
            try {
                Response<List<EndemikResponse>> response = api.getEndemikData().execute();
                if (response.isSuccessful() && response.body() != null) {
                    List<EndemikEntity> entities = new ArrayList<>();
                    for (EndemikResponse res : response.body()) {
                        entities.add(DataMapper.responseToEntity(res));
                    }
                    dao.insertAllEndemik(entities);
                    Log.d("EndemikRepository", "Data refreshed successfully: " + entities.size());
                } else {
                    Log.e("EndemikRepository", "Failed to refresh data: " + response.code());
                }
            } catch (Exception e) {
                Log.e("EndemikRepository", "Error refreshing data", e);
            }
        });
    }

    public LiveData<List<EndemikItem>> getAllEndemik() {
        return Transformations.map(dao.getAllEndemik(), DataMapper::entitiesToDomain);
    }

    public LiveData<List<FavoriteItem>> getAllFavorites() {
        return Transformations.map(dao.getAllFavorites(), this::mapFavoriteEntities);
    }
    
    private List<FavoriteItem> mapFavoriteEntities(List<FavoriteEntity> entities) {
        List<FavoriteItem> items = new ArrayList<>();
        if (entities != null) {
            for (FavoriteEntity entity : entities) {
                items.add(new FavoriteItem(
                    entity.getId(),
                    entity.getNama(),
                    entity.getNamaLatin(),
                    entity.getFoto(),
                    entity.getStatus(),
                    entity.getTipe(),
                    entity.getSavedAt()
                ));
            }
        }
        return items;
    }

    public void toggleFavorite(EndemikItem item, boolean isFavorite) {
        executor.execute(() -> {
            if (isFavorite) {
                FavoriteEntity entity = new FavoriteEntity();
                entity.setId(item.getId());
                dao.deleteFavorite(entity);
            } else {
                dao.insertFavorite(DataMapper.domainToFavoriteEntity(item));
            }
        });
    }

    public LiveData<Boolean> isFavorite(String id) {
        return dao.isFavorite(id);
    }
}
