package ru.yandex.praktikum.generator;

import ru.yandex.praktikum.pojo.CourierRequest;
import ru.yandex.praktikum.pojo.LoginRequest;

public class CourierGenerator {
    public static CourierRequest getDefaultCourier() {
        return new CourierRequest("BabaYagaBoneLeg", "1k2jl23j", "Yaga");
    }

    public static LoginRequest getDefaultCourierLogin() {
        return new LoginRequest("BabaYagaBoneLeg", "1k2jl23j");
    }
}
