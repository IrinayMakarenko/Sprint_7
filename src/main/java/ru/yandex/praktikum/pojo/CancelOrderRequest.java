package ru.yandex.praktikum.pojo;

public class CancelOrderRequest {
    private String track;
    public CancelOrderRequest(String track){
        this.track = track;
    }
    public CancelOrderRequest() {

    }

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }
}
