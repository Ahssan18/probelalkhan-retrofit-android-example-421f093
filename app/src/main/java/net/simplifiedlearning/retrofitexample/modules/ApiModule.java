package net.simplifiedlearning.retrofitexample.modules;

import net.simplifiedlearning.retrofitexample.Api;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {
    String BASE_URL;
    public ApiModule(String url) {
        this.BASE_URL = url;
    }

    @Singleton
    @Provides
    public Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}

