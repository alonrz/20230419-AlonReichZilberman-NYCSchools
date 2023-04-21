package com.example.nycschools.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.nycschools.R;
import com.example.nycschools.data.SatRepo;
import com.example.nycschools.data.SatResults;
import com.example.nycschools.data.SchoolsRepo;
import com.example.nycschools.databinding.FragmentMainBinding;

import java.util.Collections;

public class MainFragment extends Fragment implements MainContract {

    private static final String TAG = "MainFragment";
    private FragmentMainBinding _binding;
    private SchoolItemAdapter _schoolsAdapter;
    private MainPresenter _mainPresenter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        _binding = FragmentMainBinding.inflate(inflater, container, false);
        _binding.txtEmptyState.setVisibility(View.GONE); //hide empty state
        if (_mainPresenter == null) {
            _binding.spinnerMain.setVisibility(View.VISIBLE); //Show spinner while presenter is fetching
            _mainPresenter = new MainPresenter(new SchoolsRepo(), new SatRepo(), this);
            _mainPresenter.requestSchoolsList();
        } else {
            _binding.spinnerMain.setVisibility(View.GONE);
        }
        if (_schoolsAdapter == null) {
            _schoolsAdapter = new SchoolItemAdapter(Collections.emptyList(), _mainPresenter::onSchoolClicked);
        }
        return _binding.getRoot();
    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _binding.listOfSchools.setLayoutManager(new LinearLayoutManager(view.getContext()));
        _binding.listOfSchools.setAdapter(_schoolsAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        _binding = null;
    }

    @Override
    public void refreshAdapter() {
        _binding.spinnerMain.setVisibility(View.GONE);
        _schoolsAdapter.updateSchoolsList(_mainPresenter.schools);
        _schoolsAdapter.notifyDataSetChanged(); //Not using more specific method here due to nature of the one-time fetch.
    }

    @Override
    public void triggerErrorMessage(Throwable throwable) {
        Toast.makeText(requireContext(), "Something went wrong, please try again later", Toast.LENGTH_LONG).show();
        //Ideally our empty state will have a pretty illustration
        _binding.txtEmptyState.setVisibility(View.VISIBLE);
        /** Given more time I would add a pull to refresh, or button/menu to allow manual reload */
        Log.e(TAG, throwable.getMessage());
    }

    /**
     * Navigate to show the SAT results page. Send dbn value as key
     *
     * @param satResults
     */
    @Override
    public void showSatResults(SatResults satResults) {
        Bundle bundle = new Bundle();
        bundle.putString("dbn", satResults.getDbn());
        NavHostFragment.findNavController(MainFragment.this)
                .navigate(R.id.action_MainFragment_to_DetailsFragment, bundle);
    }
}