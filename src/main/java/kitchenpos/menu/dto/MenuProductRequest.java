package kitchenpos.menu.dto;

import kitchenpos.menu.domain.MenuProduct;

public class MenuProductRequest {

    private Long productId;
    private long quantity;

    public MenuProductRequest() {
        // empty
    }

    public MenuProductRequest(Long productId, long quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public MenuProduct toMenuProduct(final long menuId) {
        return new MenuProduct(menuId, this.productId, this.quantity);
    }
}
