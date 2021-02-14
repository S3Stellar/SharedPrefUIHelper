package com.example.mysharedprefui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mysharedprefui.adapter.MyAdapter;
import com.example.mysharedprefui.model.SavedPref;
import com.example.mysharedprefui.viewModel.MyListViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import static com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG;

public class MySharedPrefActivity extends AppCompatActivity {
    private RecyclerView recyclerview;
    private MyListViewModel myListViewModel;
    private MyAdapter adapter;

    private Spinner spinner;
    private EditText key;
    private EditText value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lib);
        setTheme(R.style.AppTheme);
        spinner = findViewById(R.id.spinner);
        recyclerview = findViewById(R.id.recyclerview);
        key = findViewById(R.id.editTextName);
        value = findViewById(R.id.editTextValue);

        initSpinner();
        initRecycleView();

        myListViewModel = new ViewModelProvider(this).get(MyListViewModel.class);

        myListViewModel.getAllPref().observe(
                this, savedPrefs -> adapter.setAllSavedPref(savedPrefs));

        cardSwipeListener();
        clearButtListener();
        addButtListener();
        sortButtListener();
    }

    private void cardSwipeListener() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                SavedPref undoSave = adapter.getItem(viewHolder.getAdapterPosition());
                myListViewModel.delete(adapter.getItem(viewHolder.getAdapterPosition()));
                Snackbar.make(recyclerview, "Preference Deleted", LENGTH_LONG)
                        .setAction("Undo", new UndoListener(undoSave))
                        .show();
                adapter.notifyDataSetChanged();
            }
        }).attachToRecyclerView(recyclerview);
    }

    private class UndoListener implements View.OnClickListener {
        private final SavedPref undoSave;

        public UndoListener(SavedPref undoSave) {
            this.undoSave = undoSave;
        }

        @Override
        public void onClick(View view) {
            myListViewModel.addSavedPref(undoSave);
        }
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.mutableTypes, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
    }

    private void initRecycleView() {
        adapter = new MyAdapter(this);
        recyclerview.setAdapter(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
    }

    private void clearButtListener() {
        findViewById(R.id.clearButt).setOnClickListener(v -> {
            myListViewModel.clearSharedPref();
            adapter.setAllSavedPref(new ArrayList<>());
            hideKeyboard();
        });
    }

    private void sortButtListener() {
        findViewById(R.id.sortButt).setOnClickListener(v -> {
            adapter.getAllSavedPref().sort((lhs, rhs) -> lhs.getType().compareTo(rhs.getType()));
            adapter.notifyDataSetChanged();
        });
    }

    private void addButtListener() {
        findViewById(R.id.addButt).setOnClickListener(v -> {
            Log.i("TAG", "addButtListener: IM HERE!");
            if (!key.getText().toString().isEmpty() && !value.getText().toString().isEmpty()
                    && !spinner.getSelectedItem().toString().isEmpty()) {
                SavedPref newPref = new SavedPref(
                        key.getText().toString(),
                        value.getText().toString(),
                        spinner.getSelectedItem().toString());
                myListViewModel.addSavedPref(newPref);
                hideKeyboard();
            }
        });
    }

    public void hideKeyboard() {
        try {
            InputMethodManager inputmanager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (inputmanager != null) {
                inputmanager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
            }
        } catch (Exception var2) {
            Log.i("TAG", "hideKeyboard: ");
        }

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            hideKeyboard();
        }
        return super.dispatchTouchEvent(ev);
    }
}
