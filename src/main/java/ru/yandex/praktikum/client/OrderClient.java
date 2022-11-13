package ru.yandex.praktikum.client;

import io.restassured.response.ValidatableResponse;
import ru.yandex.praktikum.pojo.CancelOrderRequest;
import ru.yandex.praktikum.pojo.OrderRequest;

import static io.restassured.RestAssured.given;

public class OrderClient extends RestClient{

    private static final String ORDERS = "/api/v1/orders";
    private static final String ORDER_CANCEL = "/api/v1/orders/cancel?track=";

    public ValidatableResponse create(OrderRequest orderRequest) {
        return given()
                .spec(getDefaultRequestSpec())
                .body(orderRequest)
                .post(ORDERS)
                .then();
    }

    public ValidatableResponse cancel(int track) {
        CancelOrderRequest cancelOrderRequest = new CancelOrderRequest();
        cancelOrderRequest.setTrack(String.valueOf(track));
        return given()
                .spec(getDefaultRequestSpec())
                .body(cancelOrderRequest)
                .put(ORDER_CANCEL + track)
                .then();
    }

    public ValidatableResponse getOrdersWithoutParameters() {
        return given()
                .spec(getDefaultRequestSpec())
                .get(ORDERS)
                .then();
    }
}
