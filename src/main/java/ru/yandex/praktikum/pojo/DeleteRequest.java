package ru.yandex.praktikum.pojo;

public class DeleteRequest {
    private String id;

    public DeleteRequest(String id) {
        this.id = id;
    }
    public DeleteRequest() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
