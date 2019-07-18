package com.cognizant.evaluation.albums.di;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Date;

public class JsonConverter {


    @TypeConverter
    public JsonObject toJson(String value) {

        Gson gson = new GsonBuilder().create();

        JsonElement element = gson.fromJson(value, JsonElement.class);
        JsonObject jsonObj = element.getAsJsonObject();

        return jsonObj;
    }


    @TypeConverter
    public String fromJson(JsonObject value) {
        return value.toString();
    }

    @TypeConverter
public  Date toDate(Long dateLong) {
        return new Date(dateLong);
    }


    @TypeConverter
    public Long fromDate(Date date) {
        return date.getTime();
    }
}
