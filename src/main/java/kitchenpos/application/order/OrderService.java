package kitchenpos.application.order;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.domain.order.Order;
import kitchenpos.domain.order.OrderLineItem;
import kitchenpos.domain.order.OrderRepository;
import kitchenpos.domain.order.OrderStatus;
import kitchenpos.domain.table.OrderTable;
import kitchenpos.dto.order.OrderLineItemRequest;
import kitchenpos.dto.order.OrderRequest;
import kitchenpos.dto.order.OrderResponse;
import kitchenpos.exception.NotFoundOrderException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderService(
            final OrderRepository orderRepository,
            final OrderValidator orderValidator
    ) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    @Transactional
    public OrderResponse create(final OrderRequest orderRequest) {
        Order order = Order.from(orderRequest.getOrderTableId());
        List<OrderLineItem> orderLineItems = this.findOrderLineItems(orderRequest.getOrderLineItems());
        order.addAllOrderLineItems(orderLineItems);

        orderValidator.validate(order);

        return OrderResponse.from(orderRepository.save(order));
    }

    public List<OrderResponse> list() {
        return OrderResponse.fromList(orderRepository.findAll());
    }

    @Transactional
    public OrderResponse changeOrderStatus(final Long orderId, final OrderRequest orderRequest) {
        Order order = this.findOrder(orderId);

        orderValidator.validateChangeOrderStatus(order);

        order.changeOrderStatus(orderRequest.getOrderStatus());
        return OrderResponse.from(order);
    }

    private List<OrderLineItem> findOrderLineItems(List<OrderLineItemRequest> orderLineItems) {
        List<OrderLineItem> result = Lists.newArrayList();
        for (OrderLineItemRequest orderLineItem : orderLineItems) {
            result.add(OrderLineItem.of(orderLineItem.getMenuId(), orderLineItem.getQuantity()));
        }
        return result;
    }

    private Order findOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new NotFoundOrderException(orderId));
    }

    public boolean isExistDontUnGroupState(List<OrderTable> orderTables) {
        return orderRepository.existsByOrderTableIdInAndOrderStatusIn(
                orderTables.stream().map(OrderTable::getId).collect(Collectors.toList()),
                OrderStatus.dontUngroupStatus()
        );
    }

    public boolean isExistDontUnGroupState(OrderTable orderTable) {
        return orderRepository.existsByOrderTableIdAndOrderStatusIn(
                orderTable.getId(),
                OrderStatus.dontUngroupStatus()
        );
    }
}