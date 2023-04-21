package com.example.nycschools.ui.main;

import com.example.nycschools.data.SatResults;

/**
 * Main contract with methods for the view to abide and the presenter to trigger.
 */
interface MainContract {
    void refreshAdapter();

    void triggerErrorMessage(Throwable throwable);

    void showSatResults(SatResults satResults);
}
