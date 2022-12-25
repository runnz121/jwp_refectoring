package kitchenpos.order.domain;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import kitchenpos.menu.domain.Menu;

@Entity
public class OrderLineItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;
    private long quantity;

    protected OrderLineItem() {
    }

    public OrderLineItem(Long seq, Order order, Menu menu, long quantity) {
        if (Objects.isNull(menu)) {
            throw new IllegalArgumentException("주문 항목에 메뉴가 없습니다.");
        }
        this.seq = seq;
        this.order = order;
        this.menu = menu;
        this.quantity = quantity;
    }

    public Long getSeq() {
        return seq;
    }

    public Long orderId() {
        if (Objects.isNull(order)) {
            return null;
        }
        return order.getId();
    }

    public void setOrder(final Order order) {
        this.order = order;
    }

    public Long menuId() {
        if (Objects.isNull(menu)) {
            return null;
        }
        return menu.getId();
    }

    public long getQuantity() {
        return quantity;
    }
}
