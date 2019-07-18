package com.cognizant.evaluation.albums.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.cognizant.evaluation.albums.di.JsonConverter;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;
import com.cognizant.evaluation.albums.models.Users;

@Database(entities = {Albums.class, Photos.class, Users.class}, version = 1,exportSchema = false)
@TypeConverters(JsonConverter.class)
public abstract class AppLocalCache extends RoomDatabase {

    public abstract LocalCacheDao getDao();

}
