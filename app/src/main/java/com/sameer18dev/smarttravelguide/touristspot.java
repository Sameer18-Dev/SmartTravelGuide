package com.sameer18dev.smarttravelguide;

public class touristspot {

    private String tsname, tsdesp, scity, tsratings;

    public touristspot(String tsname, String tsdesp, String scity, String tsratings) {
        this.tsname = tsname;
        this.tsdesp = tsdesp;
        this.scity = scity;
        this.tsratings = tsratings;
    }

    public String getTsname() {
        return tsname;
    }

    public String getTsdesp() {
        return tsdesp;
    }

    public String getScity() {
        return scity;
    }

    public String getTsratings() {
        return tsratings;
    }
}
