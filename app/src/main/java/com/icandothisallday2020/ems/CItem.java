package com.icandothisallday2020.ems;

class CItem {
    String name, comment,time,profileUrl,up,down;

    public CItem() {
    }

    public CItem(String name, String comment, String time, String profileUrl, String up, String down) {
        this.name = name;
        this.comment = comment;
        this.time = time;
        this.profileUrl = profileUrl;
        this.up = up;
        this.down = down;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
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
