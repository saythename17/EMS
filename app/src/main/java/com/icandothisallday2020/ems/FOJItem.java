package com.icandothisallday2020.ems;

public class FOJItem {
    String q,a,date;

    public FOJItem() {
    }

    public FOJItem(String q, String date) {
        this.q = q;
        this.date = date;
    }

    public FOJItem(String q, String a, String date) {
        this.q = q;
        this.a = a;
        this.date = date;
    }
}
