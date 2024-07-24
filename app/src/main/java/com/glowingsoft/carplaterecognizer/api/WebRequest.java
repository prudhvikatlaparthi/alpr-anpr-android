package com.glowingsoft.carplaterecognizer.api;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebRequest {

    static ApiService  service ;

    static {
        //create object of loopj client

        service  = new Retrofit.Builder()
                .baseUrl("https://api.platerecognizer.com").addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
                .build().create(ApiService.class);


    }

    //concatenation of base url and file name
//    private static String getAbsoluteUrl(String relativeUrl) {
//        Log.d("response URL: ", GlobleClass.BASE_URL + relativeUrl+" ");
//        return GlobleClass.BASE_URL + relativeUrl;
//    }
    public static void post(File file, String countrycode, String token, Callback<JsonObject> listener) {
        MultipartBody.Part filePart = MultipartBody.Part.createFormData("upload", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        service.getData(filePart, RequestBody.create(MediaType.parse("text/plain"), countrycode), token).enqueue(listener);
        Log.d("response", "post: request sent");
    }

}
