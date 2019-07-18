package com.cognizant.evaluation.albums.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.di.MainViewModelFactory;
import com.cognizant.evaluation.albums.home.albums.AlbumsAdaptor;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

public abstract class BaseFragment extends Fragment {


    public BaseAdaptor mAdaptor = null;
    @Inject
    MainViewModelFactory mViewModelFactory;

    private View layoutView;
    public MainViewModel model;
    public  RecyclerView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        layoutView = inflater.inflate(R.layout.fragment_albums, null);
         listView = layoutView.findViewById(R.id.albumslist);
        model = ViewModelProviders.of(this.getActivity(), mViewModelFactory).get(MainViewModel.class);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getActivity());
        listView.setLayoutManager(linearLayoutManager);
        listView.setHasFixedSize(true);
        setAdaptor();
        observeViewModel(model);
        return layoutView;
    }

    public void showMessage() {
        Snackbar mySnackbar = Snackbar.make(layoutView.findViewById(R.id.parent),
                "unable to refresh the data...", Snackbar.LENGTH_SHORT);
        mySnackbar.setAction("DISMISS", v -> mySnackbar.dismiss());

        mySnackbar.show();
    }

    public void setAdaptor(){

    }

    public void observeViewModel(MainViewModel viewModel){

    }
}
