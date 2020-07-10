package com.icandothisallday2020.ems;

public class OJItem {
    String q,a,date;

    public OJItem() {
    }

    public OJItem(String q, String date) {
        this.q = q;
        this.date = date;
    }

    public OJItem(String q, String a, String date) {
        this.q = q;
        this.a = a;
        this.date = date;
    }
}
