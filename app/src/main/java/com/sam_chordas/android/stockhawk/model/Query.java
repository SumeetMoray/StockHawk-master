package com.sam_chordas.android.stockhawk.model;

/**
 * Created by sumeet on 23/7/16.
 */
public class Query {

    int count;
    String created;
    Results results;



    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
