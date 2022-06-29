package kitchenpos.order.domain;

import static org.assertj.core.api.Assertions.assertThat;

import kitchenpos.table.domain.GuestNumber;
import kitchenpos.table.domain.OrderTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @Test
    @DisplayName("주문 객체가 같은지 검증")
    void verifyEqualsOrder() {
        final OrderTable orderTable = new OrderTable.Builder()
                .setId(1L)
                .setGuestNumber(GuestNumber.of(5))
                .setEmpty(false)
                .build();
        final Orders order = new Orders.Builder(orderTable)
                .setId(1L)
                .setOrderStatus(OrderStatus.COOKING)
                .build();

        assertThat(order).isEqualTo(new Orders.Builder(orderTable)
                .setId(1L)
                .setOrderStatus(OrderStatus.COOKING)
                .build());
    }
}
