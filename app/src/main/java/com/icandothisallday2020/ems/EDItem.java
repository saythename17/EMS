package com.icandothisallday2020.ems;

public class EDItem {
    String situation,thought,emotion,action,result,date,time,email;
    int no;

    public EDItem() {
    }

    public EDItem(String situation, String thought, String emotion, String action, String result, String date, String time, String email,int no) {
        this.situation = situation;
        this.thought = thought;
        this.emotion = emotion;
        this.action = action;
        this.result = result;
        this.date = date;
        this.time = time;
        this.email = email;
        this.no = no;
    }
}
