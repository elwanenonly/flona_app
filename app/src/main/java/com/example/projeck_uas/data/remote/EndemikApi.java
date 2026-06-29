package com.example.projeck_uas.data.remote;

import com.example.projeck_uas.data.model.EndemikResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EndemikApi {
    String BASE_URL = "https://raw.githubusercontent.com/hendroprwk08/data_endemik/main/";

    @GET("endemik.json")
    Call<List<EndemikResponse>> getEndemikData();
}
