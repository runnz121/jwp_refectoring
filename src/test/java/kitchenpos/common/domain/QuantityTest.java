package kitchenpos.common.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class QuantityTest {

    @DisplayName("Quantity 생성")
    @ParameterizedTest
    @ValueSource(longs = { 0, 1000, 35000 })
    void quantity(Long value) {
        new Quantity(value);
    }

    @DisplayName("Quantity 예외 생성")
    @ParameterizedTest
    @ValueSource(longs = { -1000, -100, -1 })
    void quantityException(Long value) {
        assertThatThrownBy(() -> {
            new Quantity(value);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}
