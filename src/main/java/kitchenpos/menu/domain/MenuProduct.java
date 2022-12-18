package kitchenpos.menu.domain;

import kitchenpos.common.Quantity;
import kitchenpos.product.domain.Product;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class MenuProduct {
    public static final String MENU_NULL_EXCEPTION_MESSAGE = "메뉴는 필수입니다.";
    public static final String PRODUCT_NULL_EXCEPTION_MESSAGE = "메뉴는 필수입니다.";
    public static final String QUANTITY_NULL_EXCEPTION_MESSAGE = "갯수는 필수입니다.";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu menu;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Product product;
    @Embedded
    private Quantity quantity;

    protected MenuProduct() {
    }

    public MenuProduct(Menu menu, Product product, Quantity quantity) {
        validate(menu, product, quantity);
        this.menu = menu;
        this.product = product;
        this.quantity = quantity;
    }

    private static void validate(Menu menu, Product product, Quantity quantity) {
//        if (Objects.isNull(menu)) {
//            throw new IllegalArgumentException(MENU_NULL_EXCEPTION_MESSAGE);
//        }
        if (Objects.isNull(product)) {
            throw new IllegalArgumentException(PRODUCT_NULL_EXCEPTION_MESSAGE);
        }
        if (Objects.isNull(quantity)) {
            throw new IllegalArgumentException(QUANTITY_NULL_EXCEPTION_MESSAGE);
        }
    }

    public Long getId() {
        return id;
    }

    public void setSeq(final Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return this.product;
    }

    public long getQuantity() {
        return this.quantity.getQuantity();
    }
}
