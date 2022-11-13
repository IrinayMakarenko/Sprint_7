import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.CourierClient;
import ru.yandex.praktikum.generator.CourierGenerator;
import ru.yandex.praktikum.pojo.CourierRequest;
import ru.yandex.praktikum.pojo.LoginRequest;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginCourierTest {
    private CourierRequest courierRequest;
    private LoginRequest loginRequest;
    private CourierClient courierClient;
    private Integer id;

    @Before
    public void setUp() {

        courierRequest = CourierGenerator.getDefaultCourier();
        courierClient = new CourierClient();

        courierClient.create(courierRequest)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("ok", equalTo(true));
    }

    @After
    public void tearDown() {

        loginRequest = CourierGenerator.getDefaultCourierLogin();
        id = courierClient.login(loginRequest)
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .body("id", Matchers.notNullValue())
                .extract()
                .path("id");

        if (id != null) {
            courierClient.delete(id)
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("ok", equalTo(true));
        }
    }

    @Test
    public void courierShouldBeAuthorized() {
    }

    @Test
    public void courierShouldNotBeAuthorizedWithWrongLogin() {

        LoginRequest loginRequestIncorrect = new LoginRequest("СaptainJackSparrow", "1k2jl23j");
        courierClient.login(loginRequestIncorrect)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void courierShouldNotBeAuthorizedWithWrongPassword() {

        LoginRequest passwordRequestIncorrect = new LoginRequest("BabaYagaBoneLeg", "1234");
        courierClient.login(passwordRequestIncorrect)
                .assertThat()
                .statusCode(SC_NOT_FOUND)
                .and()
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    public void courierShouldNotBeAuthorizedWithoutLogin() {

        LoginRequest requestWithoutLogin = new LoginRequest("", "1k2jl23j");
        courierClient.login(requestWithoutLogin)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    public void courierShouldNotBeAuthorizedWithoutPassword() {

        LoginRequest requestWithoutPassword = new LoginRequest("BabaYagaBoneLeg", "");
        courierClient.login(requestWithoutPassword)
                .assertThat()
                .statusCode(SC_BAD_REQUEST)
                .and()
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}
