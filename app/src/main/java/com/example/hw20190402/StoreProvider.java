package com.example.hw20190402;

import android.content.Context;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StoreProvider {
    public static final String AUTH = "AUTH";
    public static final String TOKEN = "TOKEN";

    public Context context;
    public static final StoreProvider ourInstance = new StoreProvider();
    Gson json = new Gson();

    private StoreProvider() {
    }

    public static StoreProvider getInstance() {
        return ourInstance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    //*******************************************************************************
    public void login(String email, String password) {
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE).edit()
                .putString(TOKEN, email + "#" + password).apply();
    }

    public String getToken() {
        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE).getString(TOKEN, "");
    }

    public void logout() {
        context.getSharedPreferences(AUTH, context.MODE_PRIVATE).edit().remove(TOKEN).apply();
    }

    public Profile getProfile(int position) {
        return getListProfiles().get(position);
    }

    public void putProfile(Profile profile) {
        List<Profile> list = getListProfiles();
        list.add(profile);
        putListProfiles(list);
    }

    public void putProfile(int position, Profile profile) {
        List<Profile> list = getListProfiles();
        list.add(position, profile);
        list.remove(position + 1);
        putListProfiles(list);
    }

    public List<Profile> getListProfiles() {
        String str = context.getSharedPreferences(AUTH, Context.MODE_PRIVATE).getString(getToken(), "");
        if (str.equals("")) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(json.fromJson(str, Profile[].class)));
        }
    }

    public void putListProfiles(List<Profile> list) {
        Profile[] profiles = new Profile[0];
        profiles = list.toArray(profiles);
        String str = json.toJson(profiles);
        context.getSharedPreferences(AUTH, Context.MODE_PRIVATE).edit().putString(getToken(), str).apply();
    }

}
