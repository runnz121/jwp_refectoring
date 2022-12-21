package kitchenpos.table.application;

import java.util.Arrays;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import kitchenpos.order.domain.OrderRepository;
import kitchenpos.order.domain.OrderStatus;
import kitchenpos.table.domain.OrderTableRepository;
import kitchenpos.table.domain.OrderTables;
import kitchenpos.tablegroup.dto.TableGroupUngroupedEvent;

@Component
public class TableGroupUngroupedEventHandler {
    private final OrderRepository orderRepository;
    private final OrderTableRepository orderTableRepository;

    public TableGroupUngroupedEventHandler(OrderRepository orderRepository,
        OrderTableRepository orderTableRepository) {
        this.orderRepository = orderRepository;
        this.orderTableRepository = orderTableRepository;
    }

    @EventListener
    @Transactional
    public void handle(final TableGroupUngroupedEvent event) {
        final Long tableGroupId = event.getTableGroupId();
        final OrderTables orderTables = findOrderTables(tableGroupId);

        validate(orderTables);
        orderTables.ungroup();
    }

    private OrderTables findOrderTables(Long tableGroupId) {
        return OrderTables.from(orderTableRepository.findAllByTableGroupId(tableGroupId));
    }

    private void validate(OrderTables orderTables) {
        if (orderRepository.existsByOrderTableIdInAndOrderStatusIn(orderTables.toIds(),
            Arrays.asList(OrderStatus.COOKING, OrderStatus.MEAL))) {
            throw new IllegalArgumentException("변경할 수 없는 주문 상태입니다.");
        }
    }
}