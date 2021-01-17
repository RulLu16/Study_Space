package com.example.core.lifecycle;

public class NetworkClient { // 임시 네트워크

    private String url;

    public NetworkClient(){
        System.out.println("call url");

        connect();
        call("init message");
    }

    public void setUrl(String url){
        this.url = url;
    }

    // call when service start
    public void connect(){
        System.out.println("connect");
    }

    public void call(String message){
        System.out.println("call and message: " + message);
    }

    // call when service end
    public void disconnect(){
        System.out.println("close");
    }
}
