package com.example.nycschools.ui.details;

import com.example.nycschools.data.SatResults;

interface DetailsContract {
    void refreshUi(SatResults satResults);

    void triggerErrorMessage(Throwable throwable);

    void toggleLoadingSpinner(boolean visible);
}
