package com.cognizant.evaluation.albums.home.photos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import androidx.databinding.library.baseAdapters.BR;
import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.databinding.PhotosListRowBinding;
import com.cognizant.evaluation.albums.home.BaseAdaptor;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;

public class PhotosAdaptor extends BaseAdaptor {


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        PhotosListRowBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.photos_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding,BR.photos);
        return viewHolder;

    }





}
