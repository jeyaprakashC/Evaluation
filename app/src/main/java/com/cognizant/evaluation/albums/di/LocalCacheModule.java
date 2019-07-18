package com.cognizant.evaluation.albums.di;

import android.app.Application;

import androidx.room.Room;

import com.cognizant.evaluation.albums.repository.AppLocalCache;
import com.cognizant.evaluation.albums.repository.LocalCacheDao;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalCacheModule {


    AppLocalCache mAppDataBase;

    public LocalCacheModule(Application application) {
        mAppDataBase = Room.databaseBuilder(application, AppLocalCache.class, "App_db").build();
    }

    @Provides
    AppLocalCache provideDatabase() {
        return mAppDataBase;
    }


    @Provides
    LocalCacheDao provideDao(AppLocalCache database) {
        return database.getDao();
    }

}
