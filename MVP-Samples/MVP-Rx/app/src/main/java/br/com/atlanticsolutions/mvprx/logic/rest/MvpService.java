package br.com.atlanticsolutions.mvprx.logic.rest;

import java.util.concurrent.TimeUnit;

import br.com.atlanticsolutions.mvprx.application.MvpConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alessandro Valenza on 28/10/2016.
 */

public class MvpService {
    public static MvpApi createMvpService(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .writeTimeout(60,TimeUnit.SECONDS)
                .readTimeout(45, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();

        Retrofit.Builder retroBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(MvpConstants.API_BASE_URL)
                .client(client);

        return retroBuilder.build().create(MvpApi.class);
    }
}
