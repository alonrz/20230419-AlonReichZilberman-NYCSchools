package com.example.nycschools.ui.main;

import com.example.nycschools.data.SatRepo;
import com.example.nycschools.data.SatResults;
import com.example.nycschools.data.School;
import com.example.nycschools.data.SchoolsRepo;

import java.util.Collections;
import java.util.List;

public class MainPresenter {
    private final SchoolsRepo _schoolRepo;
    private final SatRepo _satRepo;
    private final MainContract _viewContract;
    List<School> schools = Collections.emptyList();

    public MainPresenter(SchoolsRepo schoolsRepo, SatRepo satRepo, MainContract viewContract) {
        _schoolRepo = schoolsRepo;
        _satRepo = satRepo;
        _viewContract = viewContract;
    }

    public void requestSchoolsList() {
        _schoolRepo.fetchSchools(new SchoolsRepo.SchoolsFetchResult() {
            @Override
            public void onResponse(List<School> schools) {
                MainPresenter.this.schools = schools;
                _viewContract.refreshAdapter();
            }

            @Override
            public void onError(Throwable throwable) {
                _viewContract.triggerErrorMessage(throwable);
            }
        });
    }

    /**
     * The click handling is being handled in the presenter, and not the adapter.
     * Since the adapter is the one creating the views for each item, this method is passed in to be
     * attached to each row
     *
     * @param dbn the school's id. Aka dbn.
     */
    public void onSchoolClicked(String dbn) {
        _satRepo.fetchSatResults(new SatRepo.SatFetchResult() {
            @Override
            public void onResponse(SatResults satResults) {
                _viewContract.showSatResults(satResults);
            }

            @Override
            public void onError(Throwable throwable) {
                _viewContract.triggerErrorMessage(throwable);
            }
        }, dbn);
    }
}
