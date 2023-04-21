package com.example.nycschools.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nycschools.data.School;
import com.example.nycschools.databinding.ListItemBinding;

import java.util.List;

public class SchoolItemAdapter extends RecyclerView.Adapter<SchoolItemAdapter.ItemViewHolder> {

    private List<School> _schools;
    private final SchoolClickListener schoolClickListener;

    /**
     * The adapter for the schools list.
     *
     * @param _schools            pass in the list of schools to show
     * @param schoolClickListener Pass in a click listener that can handle a school getting clicked.
     *                            Since it's not the adapter's responsibility to handle clicks, it
     *                            will send that back to the presenter to handle.
     */
    public SchoolItemAdapter(List<School> _schools, SchoolClickListener schoolClickListener) {
        this._schools = _schools;
        this.schoolClickListener = schoolClickListener;
    }

    public void updateSchoolsList(List<School> schools) {
        _schools = schools;
    }

    protected static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        public ItemViewHolder(@NonNull ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public SchoolItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(ListItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolItemAdapter.ItemViewHolder holder, int position) {
        School school = _schools.get(position);
        holder.binding.txtName.setText(school.getName());
        holder.binding.txtName.setOnClickListener(v -> schoolClickListener.onSchoolClick(school.getDbn()));
    }

    @Override
    public int getItemCount() {
        return _schools.size();
    }

    interface SchoolClickListener {
        void onSchoolClick(String dbn);
    }
}
