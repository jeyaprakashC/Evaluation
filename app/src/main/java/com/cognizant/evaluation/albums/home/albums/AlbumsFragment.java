package com.cognizant.evaluation.albums.home.albums;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.home.BaseFragment;
import com.cognizant.evaluation.albums.home.MainViewModel;
import com.cognizant.evaluation.albums.di.MainViewModelFactory;
import com.cognizant.evaluation.albums.home.photos.PhotosAdaptor;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class AlbumsFragment extends BaseFragment {

    @Override
    public void setAdaptor() {
        mAdaptor = new AlbumsAdaptor();
        listView.setAdapter(mAdaptor);
    }

    @Override
    public void observeViewModel(MainViewModel viewModel) {
        viewModel.getAlbumslist().observe(this, albums -> {
            if (albums != null) {

                if (albums.data != null) {
                    Log.d("observeViewModel", "onChanged.." + albums.data.size());
                    mAdaptor.submitList(albums.data);
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
