package com.cognizant.evaluation.albums.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cognizant.evaluation.albums.home.MainViewModel;
import com.cognizant.evaluation.albums.repository.AppDataRepository;

import javax.inject.Inject;

public class MainViewModelFactory implements ViewModelProvider.Factory {

    AppDataRepository mProvider;

    @Inject
    public MainViewModelFactory(AppDataRepository dataProvider) {
        mProvider = dataProvider;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass == MainViewModel.class) {
            return (T) new MainViewModel(mProvider);

        } else {
            throw new RuntimeException("unsupported view model class: " + modelClass);
        }


    }


}
