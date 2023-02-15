package task1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ArcTgTest {

    private static double DELTA;

    @BeforeAll
    static void init() {
        DELTA = 0.01;
    }

    /**
     * Тестирование методом граничных значений.
     * @param param очередное значение функции
     */
    @ParameterizedTest
    @ValueSource(doubles = {-10.0, -1.01, -1, -0.99, -0.01, 0, 10.0, 1.01, 1, 0.99, 0.01, Double.NaN})
    public void test(double param) {
        Assertions.assertEquals(Math.atan(param), ArcTg.calculateArctg(param), DELTA);
    }
}
