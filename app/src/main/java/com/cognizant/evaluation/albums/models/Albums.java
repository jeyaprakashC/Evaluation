package com.cognizant.evaluation.albums.models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "albums")
public class Albums {

    public Albums() {
    }
    @Ignore
    public Albums(int id,String title,int userId) {
        this.id=id;
        this.title=title;
        this.userId=userId;
    }
    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey
    int id;

    public int getId() {
        return id;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    int userId;
    String title;


    public static DiffUtil.ItemCallback<Albums> DIFF_CALLBACK = new DiffUtil.ItemCallback<Albums>() {
        @Override
        public boolean areItemsTheSame(@NonNull Albums oldItem, @NonNull Albums newItem) {
            return oldItem.userId == newItem.userId;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Albums oldItem, @NonNull Albums newItem) {
            return oldItem.equals(newItem);
        }
    };

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        Albums user = (Albums) obj;
        return user.id == this.id && user.title == this.title;
    }
}
