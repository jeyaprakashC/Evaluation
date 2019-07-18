package com.cognizant.evaluation.albums.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.cognizant.evaluation.albums.di.NetworkInterface;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;
import com.cognizant.evaluation.albums.models.Users;
import com.cognizant.evaluation.albums.utils.AppExecutorsProviders;
import com.cognizant.evaluation.albums.utils.NetworkResponse;
import com.cognizant.evaluation.albums.utils.NetworkResource;
import com.cognizant.evaluation.albums.utils.Resource;

import java.util.List;

import javax.inject.Inject;


public class AppDataRepository {

    private NetworkInterface mNetworkInterface = null;
    private LocalCacheDao mCacheDAO = null;
    private MutableLiveData<String> error = new MutableLiveData<>();
    private final AppExecutorsProviders mAppExecutorsProviders;

    @Inject
    public AppDataRepository(NetworkInterface networkInterface, LocalCacheDao localCacheDao, AppExecutorsProviders mAppExecutorsProviders) {
        this.mNetworkInterface = networkInterface;
        this.mCacheDAO = localCacheDao;
        this.mAppExecutorsProviders = mAppExecutorsProviders;
    }


    public LiveData<Resource<List<Albums>>> getAlbum() {

        return new NetworkResource<List<Albums>, List<Albums>>(mAppExecutorsProviders) {

            @Override
            protected void cacheToLocal(@NonNull List<Albums> item) {
                mCacheDAO.addAlbums(item);
            }

            @Override
            protected boolean isDataAvailable(@Nullable List<Albums> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Albums>> loadFromCache() {
                return mCacheDAO.getAlbumsasLiveData();
            }

            @NonNull
            @Override
            protected LiveData<NetworkResponse<List<Albums>>> refreshFromCloud() {
                return mNetworkInterface.getAlbums();
            }

            @Override
            protected void onRefreshFailed() {
                error.postValue("");
            }
        }.converToLiveData();


    }



    public LiveData<Resource<List<Photos>>> getPhotos() {

        return new NetworkResource<List<Photos>, List<Photos>>(mAppExecutorsProviders) {

            @Override
            protected void cacheToLocal(@NonNull List<Photos> item) {
                mCacheDAO.addPhots(item);
            }

            @Override
            protected boolean isDataAvailable(@Nullable List<Photos> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Photos>> loadFromCache() {
                return mCacheDAO.getPhotosAsLiveData();
            }

            @NonNull
            @Override
            protected LiveData<NetworkResponse<List<Photos>>> refreshFromCloud() {
                return mNetworkInterface.getPhotos();
            }

            @Override
            protected void onRefreshFailed() {
                error.postValue("");
            }
        }.converToLiveData();


    }



    public LiveData<Resource<List<Users>>> getUsers() {

        return new NetworkResource<List<Users>, List<Users>>(mAppExecutorsProviders) {

            @Override
            protected void cacheToLocal(@NonNull List<Users> item) {
                mCacheDAO.addUsers(item);
            }

            @Override
            protected boolean isDataAvailable(@Nullable List<Users> data) {
                return data == null || data.isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<List<Users>> loadFromCache() {
                return mCacheDAO.getUsersAsLiveData();
            }

            @NonNull
            @Override
            protected LiveData<NetworkResponse<List<Users>>> refreshFromCloud() {
                return mNetworkInterface.getUsers();
            }

            @Override
            protected void onRefreshFailed() {
                error.postValue("");
            }
        }.converToLiveData();


    }

    public LiveData<String> handleError() {
        return error;
    }



}


