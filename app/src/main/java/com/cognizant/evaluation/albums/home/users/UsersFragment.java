package com.cognizant.evaluation.albums.home.users;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.di.MainViewModelFactory;
import com.cognizant.evaluation.albums.home.BaseFragment;
import com.cognizant.evaluation.albums.home.MainViewModel;
import com.cognizant.evaluation.albums.home.albums.AlbumsAdaptor;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public class UsersFragment extends BaseFragment {


    @Override
    public void setAdaptor() {
        mAdaptor = new UsersAdaptor();
        listView.setAdapter(mAdaptor);
    }

    public void observeViewModel(MainViewModel viewModel) {
        viewModel.getUserslist().observe(this, users -> {
            if (users != null) {

                if (users.data != null) {
                    Log.d("UsersFragment", "onChanged.." + users.data.size());

                    mAdaptor.submitList(users.data);
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
