package kitchenpos.menu.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;

public class MenuResponse {

    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductResponse> menuProducts;


    public MenuResponse() {
        // empty
    }

    private MenuResponse(Long id, String name, BigDecimal price, Long menuGroupId,
                         List<MenuProduct> menuProducts) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProducts = menuProducts.stream()
                                        .map(MenuProductResponse::of)
                                        .collect(Collectors.toList());
    }

    public static MenuResponse of(final Menu menu) {
        return new MenuResponse(menu.getId(),
                                menu.getName(),
                                menu.getPrice(),
                                menu.getMenuGroupId(),
                                menu.getMenuProducts());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getMenuGroupId() {
        return menuGroupId;
    }

    public List<MenuProductResponse> getMenuProducts() {
        return menuProducts;
    }
}
