package com.example.mysharedprefui.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mysharedprefui.model.SavedPref;
import com.example.mysharedprefui.repository.SavedPrefRepository;

import java.util.List;

public class MyListViewModel extends AndroidViewModel {
    private final MutableLiveData<List<SavedPref>> allPref;
    private final SavedPrefRepository savedPrefRepository;


    public MyListViewModel(@NonNull Application application) {
        super(application);
        savedPrefRepository = SavedPrefRepository.getInstance(application);
        allPref = savedPrefRepository.getAllSharedPref();
    }

    public LiveData<List<SavedPref>> getAllPref() {
        return allPref;
    }

    public void addSavedPref(SavedPref savedPref) {
        savedPrefRepository.addSavedPref(savedPref);
        List<SavedPref> currentPrefs = allPref.getValue();
        allPref.postValue(currentPrefs);
    }

    public void clearSharedPref() {
        savedPrefRepository.clearSharedPref();
    }

    public void delete(SavedPref item) {
        savedPrefRepository.delete(item);
    }
}
