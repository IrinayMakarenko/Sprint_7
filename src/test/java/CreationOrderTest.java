import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.client.OrderClient;
import ru.yandex.praktikum.pojo.OrderRequest;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CreationOrderTest {
    private OrderRequest orderRequest;
    private OrderClient orderClient;
    private String[] colour;
    private Integer track;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @After
    public void tearDown() {
        if (track != null) {
            orderClient.cancel(track)
                    .assertThat()
                    .statusCode(SC_OK)
                    .and()
                    .body("ok", equalTo(true));
        }
    }

    public CreationOrderTest(String[] colour) {
        this.colour = colour;
    }

    @Parameterized.Parameters
    public static Object[][] getColour() {
        return new Object[][]{
                {new String[]{""}},
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"GREY", "BLACK"}},
        };
    }

    @Test
    public void orderShouldBeCreated() {
        orderRequest = new OrderRequest("CaptainJack", "Sparrow", "Сaribbean sea, 7", "7", "+7 777 777 77 77", 1, "2022-11-15", "Where does rum always disappear to?", colour);
        track = orderClient.create(orderRequest)
                .assertThat()
                .statusCode(SC_CREATED)
                .and()
                .body("track", Matchers.notNullValue()).extract().path("track");
    }
}
