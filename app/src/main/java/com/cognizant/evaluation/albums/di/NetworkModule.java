package com.cognizant.evaluation.albums.di;

import com.cognizant.evaluation.albums.repository.AppDataRepository;
import com.cognizant.evaluation.albums.repository.LocalCacheDao;
import com.cognizant.evaluation.albums.utils.AppExecutorsProviders;
import com.cognizant.evaluation.albums.utils.LiveDataCallAdapterFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {
    private long TIMEOUT_REQUEST = 30;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";


    public NetworkModule() {

    }

    @Provides
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new LiveDataCallAdapterFactory())
                .client(client)
                .build();
    }

    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
                .build();
    }

    @Provides
    NetworkInterface providesNetworkInterface(Retrofit retrofit) {
        return retrofit.create(NetworkInterface.class);

    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    AppDataRepository getRepository(NetworkInterface apiCallInterface, LocalCacheDao dao,  AppExecutorsProviders appExecutorsProviders) {
        return new AppDataRepository(apiCallInterface, dao, appExecutorsProviders);
    }


}
