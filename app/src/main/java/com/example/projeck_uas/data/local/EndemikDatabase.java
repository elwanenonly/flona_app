package com.example.projeck_uas.data.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.projeck_uas.data.model.EndemikEntity;
import com.example.projeck_uas.data.model.FavoriteEntity;

@Database(entities = {EndemikEntity.class, FavoriteEntity.class}, version = 2, exportSchema = false)
public abstract class EndemikDatabase extends RoomDatabase {
    public abstract EndemikDao endemikDao();

    private static volatile EndemikDatabase INSTANCE;

    public static EndemikDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EndemikDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    EndemikDatabase.class, "endemik_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
