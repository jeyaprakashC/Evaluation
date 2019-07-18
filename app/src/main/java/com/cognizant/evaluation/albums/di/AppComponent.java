package com.cognizant.evaluation.albums.di;

import com.cognizant.evaluation.albums.home.MainActivity;

import dagger.Component;

@Component(modules = {NetworkModule.class,ApplicationModule.class, LocalCacheModule.class, ViewModelModule.class})
public interface AppComponent {
    void  generateDependencies(MainActivity activity);
}
