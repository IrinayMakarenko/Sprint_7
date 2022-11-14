import org.junit.Before;
import org.junit.Test;
import ru.yandex.praktikum.client.OrderClient;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class GetOrdersTest {
    private OrderClient orderClient;

    @Before
    public void setUp() {
        orderClient = new OrderClient();
    }

    @Test
    public void ordersShouldBeGet() {
        orderClient.getOrdersWithoutParameters()
                .assertThat()
                .statusCode(SC_OK)
                .and()
                .assertThat().body("orders", hasSize(greaterThan(0)));
    }
}
