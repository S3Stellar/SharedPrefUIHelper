package com.example.mysharedprefui.repository;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import com.example.mysharedprefui.model.SavedPref;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SavedPrefRepository {

    private static SavedPrefRepository instance;
    private static SharedPreferences sharedPreferences;
    private final List<SavedPref> dataSet = new ArrayList<>();

    public static SavedPrefRepository getInstance(Application application) {
        if (instance == null) {
            instance = new SavedPrefRepository();
            sharedPreferences = application.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        }
        return instance;
    }


    public MutableLiveData<List<SavedPref>> getAllSharedPref() {
        MutableLiveData<List<SavedPref>> allPref = new MutableLiveData<>();

        for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
            SavedPref mySavedPref = new SavedPref(entry.getKey(), entry.getValue().toString(), getValueType(entry.getValue()));
            dataSet.add(mySavedPref);
        }

        allPref.setValue(dataSet);
        return allPref;
    }

    private String getValueType(Object s) {
        if (s.getClass().equals(Integer.class))
            return "Int";
        else if (s.getClass().equals(Long.class))
            return "Long";
        else if (s.getClass().equals(Float.class))
            return "Float";
        else if (s.getClass().equals(Boolean.class))
            return "Boolean";
        else
            return "String";
    }

    public void clearSharedPref() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        dataSet.clear();
        editor.apply();
    }

    public void addSavedPref(SavedPref savedPref) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        switch (savedPref.getType()) {
            case "Int":
                try {
                    editor.putInt(savedPref.getName(), Integer.parseInt(savedPref.getValue()));
                } catch (Exception e) {
                    return;
                }
                break;
            case "Float":
                try {
                    editor.putFloat(savedPref.getName(), Float.parseFloat(savedPref.getValue()));
                } catch (Exception e) {
                    return;
                }
                break;
            case "Long":
                try {
                    editor.putLong(savedPref.getName(), Long.parseLong(savedPref.getValue()));
                } catch (Exception e) {
                    return;
                }
                break;
            case "Boolean":
                try {
                    if (!savedPref.getValue().equalsIgnoreCase("true") &&
                            !savedPref.getValue().equalsIgnoreCase("false"))
                        throw new ParseException("Please enter true/false", 0);
                    editor.putBoolean(savedPref.getName(), Boolean.parseBoolean(savedPref.getValue()));
                } catch (Exception e) {
                    return;
                }
                break;
            case "String":
                editor.putString(savedPref.getName(), savedPref.getValue());
                break;
            default:
                return;
        }
        if (!dataSet.contains(savedPref))
            dataSet.add(savedPref);
        editor.apply();
    }

    public void delete(SavedPref item) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(item.getName());
        dataSet.remove(item);
        editor.apply();
    }
}