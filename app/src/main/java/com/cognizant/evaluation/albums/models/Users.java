package com.cognizant.evaluation.albums.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.JsonObject;

@Entity(tableName = "users")
public class Users {


    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JsonObject getAddress() {
        return address;
    }

    public void setAddress(JsonObject address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public JsonObject getCompany() {
        return company;
    }

    public void setCompany(JsonObject company) {
        this.company = company;
    }

    @PrimaryKey
    @NonNull
    String id;
    String name;
    String username;
    String email;
    JsonObject address;
    String phone;
    String website;
    JsonObject company;
}
