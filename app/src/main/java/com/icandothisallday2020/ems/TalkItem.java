package com.icandothisallday2020.ems;

public class TalkItem {
    String msg,type;

    public TalkItem() {
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TalkItem(String msg, String type) {
        this.msg = msg;
        this.type = type;
    }
}
