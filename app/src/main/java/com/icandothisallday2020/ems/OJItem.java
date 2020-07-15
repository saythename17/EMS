package com.icandothisallday2020.ems;

public class OJItem {
    String q,a,date,year,month,day,email;

    public OJItem() {
    }

    public OJItem(String q, String date) {
        this.q = q;
        this.date = date;
    }

    public OJItem(String q, String a, String date, String year, String month, String day, String email) {
        this.q = q;
        this.a = a;
        this.date = date;
        this.year = year;
        this.month = month;
        this.day = day;
        this.email = email;
    }
}
