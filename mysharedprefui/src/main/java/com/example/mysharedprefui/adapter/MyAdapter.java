package com.example.mysharedprefui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mysharedprefui.R;
import com.example.mysharedprefui.model.SavedPref;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<SavedPref> allSavedPref;
    private LayoutInflater layoutInflater;
    private final Context context;

    public MyAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SavedPref current = allSavedPref.get(position);
        holder.key.setText(String.format("Key: %s", current.getName()));
        holder.value.setText(String.format("Value: %s", current.getValue()));
        holder.type.setText(current.getType());
    }

    @Override
    public int getItemCount() {
        return allSavedPref != null ? allSavedPref.size() : 0;
    }

    public SavedPref getItem(int position) {
        return allSavedPref.get(position);
    }

    public List<SavedPref> getAllSavedPref() {
        return allSavedPref;
    }

    public void setAllSavedPref(List<SavedPref> allSavedPref) {
        this.allSavedPref = allSavedPref;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView key;
        private final TextView value;
        private final TextView type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            key = itemView.findViewById(R.id.textViewKey);
            value = itemView.findViewById(R.id.textViewValue);
            type = itemView.findViewById(R.id.textViewType);
        }
    }

}
