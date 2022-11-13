package ru.yandex.praktikum.client;

import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.pojo.CourierRequest;
import ru.yandex.praktikum.pojo.DeleteRequest;
import ru.yandex.praktikum.pojo.LoginRequest;

import static io.restassured.RestAssured.given;

public class CourierClient extends RestClient {

    private static final String COURIER_DELETE = "/api/v1/courier/";
    private static final String COURIER = "/api/v1/courier";
    private static final String COURIER_LOGIN = "/api/v1/courier/login";

    public ValidatableResponse create(CourierRequest courierRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(courierRequest)
                .post(COURIER)
                .then();
    }

    public ValidatableResponse login(LoginRequest loginRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(loginRequest)
                .post(COURIER_LOGIN)
                .then();
    }

    public ValidatableResponse delete(int id) {
        DeleteRequest deleteRequest = new DeleteRequest();
        deleteRequest.setId(String.valueOf(id));
        return given()
                .spec(getDefaultRequestSpec())
                .body(deleteRequest)
                .delete(COURIER_DELETE + id)
                .then();
    }
}
