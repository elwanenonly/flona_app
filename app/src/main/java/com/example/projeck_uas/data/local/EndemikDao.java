package com.example.projeck_uas.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import com.example.projeck_uas.data.model.EndemikEntity;
import com.example.projeck_uas.data.model.FavoriteEntity;
import java.util.List;

@Dao
public interface EndemikDao {
    // Endemik Cache
    @Query("SELECT * FROM endemik")
    LiveData<List<EndemikEntity>> getAllEndemik();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEndemik(List<EndemikEntity> items);

    @Query("SELECT * FROM endemik WHERE id = :id")
    EndemikEntity getEndemikById(String id);

    // Favorites
    @Query("SELECT * FROM favorit ORDER BY saved_at DESC")
    LiveData<List<FavoriteEntity>> getAllFavorites();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(FavoriteEntity item);

    @Delete
    void deleteFavorite(FavoriteEntity item);

    @Query("SELECT EXISTS(SELECT 1 FROM favorit WHERE id = :id)")
    LiveData<Boolean> isFavorite(String id);
}
