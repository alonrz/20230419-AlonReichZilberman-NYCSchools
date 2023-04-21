package com.example.nycschools.data;

public class SatResults {
    private final String dbn;
    private final String school_name;
    private final String num_of_sat_test_takers;
    private final String sat_critical_reading_avg_score;
    private final String sat_math_avg_score;
    private final String sat_writing_avg_score;

    public SatResults(String _dbn, String _school_name, String _num_of_sat_test_takers, String sat_critical_reading_avg_score, String _sat_math_avg_score, String _sat_writing_avg_score) {
        this.dbn = _dbn;
        this.school_name = _school_name;
        this.num_of_sat_test_takers = _num_of_sat_test_takers;
        this.sat_critical_reading_avg_score = sat_critical_reading_avg_score;
        this.sat_math_avg_score = _sat_math_avg_score;
        this.sat_writing_avg_score = _sat_writing_avg_score;
    }

    public String getDbn() {
        return dbn;
    }

    public String getSchoolName() {
        return school_name;
    }

    public String getNumOfSatTestTakers() {
        return num_of_sat_test_takers;
    }

    public String getSatCriticalReadingAvgScore() {
        return sat_critical_reading_avg_score;
    }

    public String getSatMathAvgScore() {
        return sat_math_avg_score;
    }

    public String getSatWritingAvgScore() {
        return sat_writing_avg_score;
    }
}
