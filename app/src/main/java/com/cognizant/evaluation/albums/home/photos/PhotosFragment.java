package com.cognizant.evaluation.albums.home.photos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.di.MainViewModelFactory;
import com.cognizant.evaluation.albums.home.BaseAdaptor;
import com.cognizant.evaluation.albums.home.BaseFragment;
import com.cognizant.evaluation.albums.home.MainViewModel;
import com.cognizant.evaluation.albums.home.albums.AlbumsAdaptor;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class PhotosFragment extends BaseFragment {


    @Override
    public void setAdaptor() {
        mAdaptor = new PhotosAdaptor();
        listView.setAdapter(mAdaptor);
    }


    @Override
    public void observeViewModel(MainViewModel viewModel) {
        viewModel.getPhotoslist().observe(this, photos -> {
            if (photos != null) {

                if (photos.data != null) {
                    Log.d("PhotosFragment", "onChanged.." + photos.data.size());
                    mAdaptor.submitList(photos.data);
                }
            }
        });

        viewModel.getError().observe(this, s -> {
            if (s != null) {
                showMessage();
            }
        });
    }
}
