package com.sam_chordas.android.stockhawk.model;

import java.util.ArrayList;

/**
 * Created by sumeet on 23/7/16.
 */
public class Results {

    ArrayList<Quote> quote;

    public ArrayList<Quote> getQuote() {
        return quote;
    }

    public void setQuote(ArrayList<Quote> quote) {
        this.quote = quote;
    }
}
