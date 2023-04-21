package com.example.nycschools.ui.details;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nycschools.data.SatRepo;
import com.example.nycschools.data.SatResults;
import com.example.nycschools.databinding.FragmentSatDetailsBinding;

public class SatResultsFragment extends Fragment implements DetailsContract {

    private static final String TAG = "SatResultsFragment";
    private FragmentSatDetailsBinding binding;
    private DetailsPresenter _detailsPresenter;
    private String _dbn;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        /** Future improvement: if same dbn is sent, do not make another network call.
         * Cache the previous one */
        binding = FragmentSatDetailsBinding.inflate(inflater, container, false);
        _dbn = getArguments() != null ? getArguments().getString("dbn") : null;
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        _detailsPresenter = new DetailsPresenter(_dbn, new SatRepo(), this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void refreshUi(SatResults satResults) {
        fillFieldOrHide(binding.txtName, satResults.getSchoolName());
        fillFieldOrHide(binding.txtNumTestTakers, satResults.getNumOfSatTestTakers());
        fillFieldOrHide(binding.txtSatCriticalReadingAvgScore, satResults.getSatCriticalReadingAvgScore());
        fillFieldOrHide(binding.txtSatWritingAvgScore, satResults.getSatWritingAvgScore());
        fillFieldOrHide(binding.txtSatMathAvgScore, satResults.getSatMathAvgScore());
    }

    private void fillFieldOrHide(TextView textView, String text) {
        if (text == null || text.isEmpty()) {
            textView.setVisibility(View.GONE);
        } else {
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void triggerErrorMessage(Throwable throwable) {
        Toast.makeText(requireContext(), "Something went wrong, please try again later", Toast.LENGTH_LONG).show();
        Log.e(TAG, throwable.getMessage());
    }

    @Override
    public void toggleLoadingSpinner(boolean visible) {
        binding.spinnerName.setVisibility(visible ? View.VISIBLE : View.GONE);
        binding.spinnerTakers.setVisibility(visible ? View.VISIBLE : View.GONE);
        binding.spinnerReading.setVisibility(visible ? View.VISIBLE : View.GONE);
        binding.spinnerMath.setVisibility(visible ? View.VISIBLE : View.GONE);
        binding.spinnerWriting.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}