package com.cognizant.evaluation.albums.repository;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;
import com.cognizant.evaluation.albums.models.Users;

import java.util.List;

@Dao
public interface LocalCacheDao {


    @Query("SELECT * FROM albums order by title ASC")
    List<Albums> getAlbumsList();


    @Query("SELECT * FROM albums order by title ASC")
    LiveData<List<Albums>> getAlbumsasLiveData();


    @Query("SELECT * FROM albums order by title ASC")
    DataSource.Factory<Integer, Albums> getAlbums();


    @Query("SELECT * FROM albums order by title ASC")
    LiveData<List<Albums>> getAlbumsasLiveDataPaged();


    @Query("SELECT * FROM albums order by title ASC limit :start,:limit")
    LiveData<List<Albums>> getAlbumsasLiveData(int start, int limit);

    @Query("select * from albums where id = :id")
    Albums getAnAlbum(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addAlbums(List<Albums> album);

    @Update
    int updateAlbums(List<Albums> album);

    @Delete
    int removeAlbum(List<Albums> album);


    @Query("SELECT * FROM Photos")
    List<Photos> getPhotos();


    @Query("SELECT * FROM Photos")
    LiveData<List<Photos>> getPhotosAsLiveData();

    @Query("select * from Photos where id = :id")
    Photos getphotos(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPhots(List<Photos> photos);

    @Update
    int updatePhotos(List<Photos> photos);

    @Delete
    void removePhotos(List<Photos> photos);


    //Users
    @Query("SELECT * FROM Users")
    List<Users> getUsers();


    @Query("SELECT * FROM Users")
    LiveData<List<Users>> getUsersAsLiveData();

    @Query("select * from Users where id = :id")
    Users getUser(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUsers(List<Users> users);

    @Update
    int updateUsers(List<Users> users);

    @Delete
    int removeUsers(List<Users> users);
}
