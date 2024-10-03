package com.example.vs_application;

import androidx.annotation.NonNull;

public class chatMessage {

    private String userid;


    private String crt_dt;
    private String content;

    public chatMessage(){
    }

    public chatMessage(String userid, String crt_dt, String content){
        this.userid = userid;
        this.content = content;
        this.crt_dt = crt_dt;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCrt_dt() {
        return crt_dt;
    }

    public void setCrt_dt(String crt_dt) {
        this.crt_dt = crt_dt;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @NonNull
    @Override
    public String toString() {
        return "chatMessage{" + "userid='" + userid + '\'' + ", crt_dts='" + crt_dt + '\'' + "content='" + content + '\'' + '}';
    }

}
