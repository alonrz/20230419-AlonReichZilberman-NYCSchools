package com.example.nycschools.data;

public class School {
    private final String dbn;
    private final String school_name;

    public School(String dbn, String school_name) {

        this.school_name = school_name;
        this.dbn = dbn;
    }

    public String getDbn() {
        return dbn;
    }
    public String getName() {
        return school_name;
    }
}
