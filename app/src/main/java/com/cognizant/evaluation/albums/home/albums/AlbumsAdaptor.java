package com.cognizant.evaluation.albums.home.albums;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.databinding.library.baseAdapters.BR;
import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.databinding.AlbumListRowBinding;
import com.cognizant.evaluation.albums.home.BaseAdaptor;
import com.cognizant.evaluation.albums.models.Albums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AlbumsAdaptor extends BaseAdaptor {

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.album_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding,BR.album);
        return viewHolder;
    }


}
