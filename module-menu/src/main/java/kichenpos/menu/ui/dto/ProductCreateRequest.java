package kichenpos.menu.ui.dto;

import kichenpos.common.domain.Price;
import kichenpos.menu.domain.Product;

import java.math.BigDecimal;

public class ProductCreateRequest {
    private String name;
    private BigDecimal price;

    private ProductCreateRequest() {
    }

    public ProductCreateRequest(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Product toEntity() {
        return new Product(name, new Price(price));
    }
}