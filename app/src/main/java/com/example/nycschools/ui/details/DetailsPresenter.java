package com.example.nycschools.ui.details;

import com.example.nycschools.data.SatRepo;
import com.example.nycschools.data.SatResults;

public class DetailsPresenter {
    private final SatRepo _satRepo;
    private final DetailsContract _viewContract;

    public DetailsPresenter(String dbn, SatRepo satRepo, DetailsContract viewContract) {
        _satRepo = satRepo;
        _viewContract = viewContract;
        fetchSatResults(dbn);
    }

    private void fetchSatResults(String dbn) {
        _viewContract.toggleLoadingSpinner(true);
        _satRepo.fetchSatResults(new SatRepo.SatFetchResult() {
            @Override
            public void onResponse(SatResults satResults) {
                _viewContract.toggleLoadingSpinner(false);
                _viewContract.refreshUi(satResults);
            }

            @Override
            public void onError(Throwable throwable) {
                _viewContract.toggleLoadingSpinner(false);
                _viewContract.triggerErrorMessage(throwable);
            }
        }, dbn);
    }
}
