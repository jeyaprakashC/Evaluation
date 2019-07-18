package com.cognizant.evaluation.albums.di;



import androidx.lifecycle.LiveData;

import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;
import com.cognizant.evaluation.albums.models.Users;
import com.cognizant.evaluation.albums.utils.NetworkResponse;

import java.util.List;

import retrofit2.http.GET;

public interface NetworkInterface {


    @GET("/albums")
    LiveData<NetworkResponse<List<Albums>>> getAlbums();


    @GET("/photos")
    LiveData<NetworkResponse<List<Photos>>> getPhotos();


    @GET("/users")
    LiveData<NetworkResponse<List<Users>>> getUsers();


}
