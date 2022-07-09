package kitchenpos.dto.menu;

import kitchenpos.domain.menu.Menu;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class MenuResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long menuGroupId;
    private List<MenuProductResponse> menuProductResponses;

    public MenuResponse(Long id, String name, BigDecimal price, Long menuGroupId, List<MenuProductResponse> menuProductResponses) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuGroupId = menuGroupId;
        this.menuProductResponses = menuProductResponses;
    }

    public static MenuResponse from(Menu menu) {
        return new MenuResponse(menu.getId(), menu.getName(), menu.getPrice(),
                            menu.getMenuGroupId(),
                            MenuProductResponse.ofMenuProductResponses(menu.getMenuProducts()));
    }

    public static List<MenuResponse> ofMenuResponses(List<Menu> menus) {
        return menus.stream()
                    .map(MenuResponse::from)
                    .collect(Collectors.toList());
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

    public List<MenuProductResponse> getMenuProductResponses() {
        return menuProductResponses;
    }
}