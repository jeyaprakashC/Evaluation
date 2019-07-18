package com.cognizant.evaluation.albums.home.users;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.cognizant.evaluation.albums.R;
import com.cognizant.evaluation.albums.home.BaseAdaptor;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Users;


public class UsersAdaptor extends BaseAdaptor {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.users_list_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(binding, BR.users);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = (ViewHolder) holder;
            vHolder.bind(getItem(position));
        }
    }
}
