package com.sam_chordas.android.stockhawk.model;

/**
 * Created by sumeet on 23/7/16.
 */
public class Quote {

    String Symbol;
    String Date;
    String Open;
    String High;
    String Low;
    String Close;
    String Volume;
    String Adj_Close;

    public String getSymbol() {
        return Symbol;
    }

    public void setSymbol(String symbol) {
        Symbol = symbol;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getOpen() {
        return Open;
    }

    public void setOpen(String open) {
        Open = open;
    }

    public String getHigh() {
        return High;
    }

    public void setHigh(String high) {
        High = high;
    }

    public String getLow() {
        return Low;
    }

    public void setLow(String low) {
        Low = low;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }

    public String getVolume() {
        return Volume;
    }

    public void setVolume(String volume) {
        Volume = volume;
    }

    public String getAdj_Close() {
        return Adj_Close;
    }

    public void setAdj_Close(String adj_Close) {
        Adj_Close = adj_Close;
    }
}
