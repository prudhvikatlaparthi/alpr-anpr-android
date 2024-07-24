package com.glowingsoft.carplaterecognizer.api;

import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiService {
    @POST("v1/plate-reader")
    @Multipart
    Call<JsonObject> getData(
            @Part MultipartBody.Part upload,
            @Part("region") RequestBody countrycode,
            @Header("Authorization") String header
    );
}