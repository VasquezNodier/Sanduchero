package co.vasquez.nodier.sanduchero.api;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestAdapter {

    public static  final String BASE_URL = "http://192.168.0.10/sanduchero/";
    Retrofit retrofit;

    public Retrofit getAdapter(){
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
