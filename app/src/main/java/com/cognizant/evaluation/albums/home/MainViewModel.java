package com.cognizant.evaluation.albums.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;
import com.cognizant.evaluation.albums.models.Users;
import com.cognizant.evaluation.albums.repository.AppDataRepository;
import com.cognizant.evaluation.albums.utils.Resource;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private AppDataRepository mDataProvider;
    private LiveData<String> error;
    private LiveData<Resource<List<Albums>>> albumsLiveData;
    private LiveData<Resource<List<Photos>>> photosLiveData;
    private LiveData<Resource<List<Users>>> usersLiveData;

    @Inject
    public MainViewModel(AppDataRepository dataProvider) {
        mDataProvider = dataProvider;
        albumsLiveData = mDataProvider.getAlbum();
        photosLiveData = mDataProvider.getPhotos();
        usersLiveData = mDataProvider.getUsers();
        error = mDataProvider.handleError();
    }

    public LiveData<Resource<List<Albums>>> getAlbumslist() {
        return albumsLiveData;
    }

    public LiveData<Resource<List<Photos>>> getPhotoslist() {
        return photosLiveData;
    }

    public LiveData<Resource<List<Users>>> getUserslist() {
        return usersLiveData;
    }

    public LiveData<String> getError() {
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


}
