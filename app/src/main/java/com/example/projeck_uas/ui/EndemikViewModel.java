package com.example.projeck_uas.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.MediatorLiveData;
import com.example.projeck_uas.data.EndemikRepository;
import com.example.projeck_uas.data.model.EndemikItem;
import com.example.projeck_uas.data.model.FavoriteItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EndemikViewModel extends AndroidViewModel {
    private final EndemikRepository repository;
    private final MutableLiveData<String> searchQuery = new MutableLiveData<>("");
    private final MutableLiveData<String> selectedType = new MutableLiveData<>("Semua");
    
    private final MediatorLiveData<List<EndemikItem>> filteredData = new MediatorLiveData<>();
    private final MutableLiveData<Boolean> isDarkTheme = new MutableLiveData<>();
    private final SharedPreferences sharedPreferences;

    public EndemikViewModel(@NonNull Application application, EndemikRepository repository) {
        super(application);
        this.repository = repository;
        this.sharedPreferences = application.getSharedPreferences("settings", Context.MODE_PRIVATE);
        this.isDarkTheme.setValue(sharedPreferences.getBoolean("is_dark_theme", false));

        LiveData<List<EndemikItem>> allEndemik = repository.getAllEndemik();
        
        filteredData.addSource(allEndemik, items -> combineFilters(items, searchQuery.getValue(), selectedType.getValue()));
        filteredData.addSource(searchQuery, query -> combineFilters(allEndemik.getValue(), query, selectedType.getValue()));
        filteredData.addSource(selectedType, type -> combineFilters(allEndemik.getValue(), searchQuery.getValue(), type));
        
        repository.refreshEndemikData();
    }

    private void combineFilters(List<EndemikItem> items, String query, String type) {
        if (items == null) {
            filteredData.setValue(new ArrayList<>());
            return;
        }

        List<EndemikItem> result = items.stream()
            .filter(item -> (type.equals("Semua") || item.getTipe().equals(type)))
            .filter(item -> query.isEmpty() || 
                           item.getNama().toLowerCase().contains(query.toLowerCase()) ||
                           item.getAsal().toLowerCase().contains(query.toLowerCase()) ||
                           item.getGenus().toLowerCase().contains(query.toLowerCase()))
            .collect(Collectors.toList());
        
        filteredData.setValue(result);
    }

    public LiveData<List<EndemikItem>> getFilteredData() { return filteredData; }
    public LiveData<List<FavoriteItem>> getFavorites() { return repository.getAllFavorites(); }
    public LiveData<Boolean> getIsDarkTheme() { return isDarkTheme; }

    public void setSearchQuery(String query) { searchQuery.setValue(query); }
    public void setSelectedType(String type) { selectedType.setValue(type); }

    public void toggleTheme(boolean dark) {
        sharedPreferences.edit().putBoolean("is_dark_theme", dark).apply();
        isDarkTheme.setValue(dark);
    }

    public void toggleFavorite(EndemikItem item, boolean isFavorite) {
        repository.toggleFavorite(item, isFavorite);
    }

    public LiveData<Boolean> isFavorite(String id) {
        return repository.isFavorite(id);
    }
}
