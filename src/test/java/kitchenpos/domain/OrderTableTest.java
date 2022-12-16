package kitchenpos.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

class OrderTableTest {
    @Test
    void 생성() {
        OrderTable orderTable = new OrderTable();
        orderTable.setId(1L);
        orderTable.setTableGroup(null);
        orderTable.setNumberOfGuests(1);
        orderTable.setEmpty(false);

        assertAll(
                () -> assertThat(orderTable.getId()).isEqualTo(1L),
                () -> assertThat(orderTable.getTableGroup()).isNull(),
                () -> assertThat(orderTable.getNumberOfGuests()).isEqualTo(1),
                () -> assertThat(orderTable.isEmpty()).isFalse()
        );
    }
}
