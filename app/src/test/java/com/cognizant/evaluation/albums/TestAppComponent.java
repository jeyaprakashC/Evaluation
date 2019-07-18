package com.cognizant.evaluation.albums;

import com.cognizant.evaluation.albums.di.LocalCacheModule;
import com.cognizant.evaluation.albums.di.NetworkModule;

import dagger.Component;

@Component(modules = {NetworkModule.class, LocalCacheModule.class})
interface TestAppComponent {
    void inject(DataBaseTest albumsTest);
}
