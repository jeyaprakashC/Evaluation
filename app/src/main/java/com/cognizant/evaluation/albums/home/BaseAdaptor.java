package com.cognizant.evaluation.albums.home;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.cognizant.evaluation.albums.BR;
import com.cognizant.evaluation.albums.databinding.AlbumListRowBinding;
import com.cognizant.evaluation.albums.models.Albums;
import com.cognizant.evaluation.albums.models.Photos;
import com.cognizant.evaluation.albums.models.Users;

import java.util.List;

public abstract class BaseAdaptor<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<T> mListValues;


    public void submitList(List<T> mListValues) {
        this.mListValues = mListValues;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    abstract public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder vHolder = (ViewHolder) holder;
            vHolder.bind(getItem(position));
        }
    }

    @Override
    public int getItemCount() {

        if (mListValues != null) {
            return mListValues.size();
        }
        return 0;

    }

    public T getItem(int position) {
        if (mListValues != null) {
            return mListValues.get(position);
        }
        return null;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ViewDataBinding mBinding;
        int mId;
        public ViewHolder(ViewDataBinding binding,int Id) {
            super(binding.getRoot());
            this.mBinding = binding;
            this.mId=Id;

        }

        public void bind(Object obj) {
            mBinding.setVariable(mId, obj);
            mBinding.executePendingBindings();
        }
    }

}
