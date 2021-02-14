package com.example.mysharedprefui.model;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Objects;

public class SavedPref implements Serializable {
    private String name;
    private String value;
    private String type;

    public SavedPref(String name, String value, String type) {
        this.name = name;
        this.value = value;
        this.type = type;
    }

    public SavedPref() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SavedPref)) return false;
        SavedPref savedPref = (SavedPref) o;
        return getName().equals(savedPref.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value, type);
    }

    @NonNull
    @Override
    public String toString() {
        return "SavedPref{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}