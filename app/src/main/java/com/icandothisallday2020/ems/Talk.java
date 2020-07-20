package com.icandothisallday2020.ems;

public class Talk {
    String email,msg,profileUrl;

    public Talk() {
    }

    public Talk(String email, String msg, String profileUrl) {
        this.email = email;
        this.msg = msg;
        this.profileUrl = profileUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }
}
