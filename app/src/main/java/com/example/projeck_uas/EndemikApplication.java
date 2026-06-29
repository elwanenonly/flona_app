package com.example.projeck_uas;

import android.app.Application;
import com.example.projeck_uas.data.EndemikRepository;
import com.example.projeck_uas.data.local.EndemikDatabase;
import com.example.projeck_uas.data.remote.EndemikApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EndemikApplication extends Application {
    private EndemikRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        EndemikApi api = new Retrofit.Builder()
                .baseUrl(EndemikApi.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(EndemikApi.class);

        EndemikDatabase database = EndemikDatabase.getDatabase(this);
        repository = new EndemikRepository(api, database.endemikDao());
    }

    public EndemikRepository getRepository() {
        return repository;
    }
}
