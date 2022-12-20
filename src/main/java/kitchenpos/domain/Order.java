package kitchenpos.domain;

import kitchenpos.domain.type.OrderStatus;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static kitchenpos.constants.ErrorCodeType.*;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private OrderTable orderTable;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @CreatedDate
    private LocalDateTime orderedTime = LocalDateTime.now();

    @Embedded
    private OrderLineItems orderLineItems = new OrderLineItems();

    protected Order() {
    }

    public Order(OrderTable orderTable, OrderStatus orderStatus) {
        this.orderTable = orderTable;
        this.orderStatus = orderStatus;
    }

    public Order(OrderTable orderTable, OrderStatus orderStatus, OrderLineItems orderLineItems) {
        this.orderTable = orderTable;
        this.orderStatus = orderStatus;
        this.orderedTime = LocalDateTime.now();
        this.orderLineItems = orderLineItems;
    }

    public Order(Long id, OrderTable orderTable, OrderStatus orderStatus, OrderLineItems orderLineItems) {
        this.id = id;
        this.orderTable = orderTable;
        this.orderStatus = orderStatus;
        this.orderedTime = LocalDateTime.now();;
        this.orderLineItems = orderLineItems;
    }

    public void validUngroupable() {
        if (Arrays.asList(OrderStatus.COOKING, OrderStatus.MEAL).contains(this.orderStatus)) {
            throw new IllegalArgumentException(COOKING_MEAL_NOT_UNGROUP.getMessage());
        }
    }

    public void addOrderLineItems(List<OrderLineItem> orderLineItem, List<Menu> menu) {
        this.orderLineItems = new OrderLineItems(orderLineItem);
        orderLineItems.setOrderLineItem(this);
    }

    public Long getId() {
        return id;
    }

    public OrderTable getOrderTable() {
        return orderTable;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public LocalDateTime getOrderedTime() {
        return orderedTime;
    }

    public List<OrderLineItem> getOrderLineItems() {
        return orderLineItems.getOrderLineItems();
    }

    public void changeOrderStatus(OrderStatus orderStatus) {
        validCheckCompletion();
        this.orderStatus = orderStatus;
    }

    private void validCheckCompletion() {
        if (this.orderStatus == OrderStatus.COMPLETION) {
            throw new IllegalArgumentException(ORDER_STATUS_NOT_COMPLETION.getMessage());
        }
    }
}
