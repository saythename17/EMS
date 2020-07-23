package com.icandothisallday2020.ems;

class CItem {
    String name, profileUrl, email, comment, date, up, down;

    public CItem() {
    }

    public CItem(String name, String profileUrl, String email, String comment, String date, String up, String down) {
        this.name = name;
        this.profileUrl = profileUrl;
        this.email = email;
        this.comment = comment;
        this.date = date;
        this.up = up;
        this.down = down;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public String getDown() {
        return down;
    }

    public void setDown(String down) {
        this.down = down;
    }
}
