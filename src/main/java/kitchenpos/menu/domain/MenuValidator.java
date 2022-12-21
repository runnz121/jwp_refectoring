package kitchenpos.menu.domain;

import static java.math.BigDecimal.ZERO;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.common.domain.Price;
import kitchenpos.menu.dto.MenuProductRequest;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.product.domain.Product;
import kitchenpos.product.domain.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class MenuValidator {
    private final ProductRepository productRepository;

    public MenuValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validCreate(MenuRequest menuRequest) {
        List<Long> productIds = mapToProductIds(menuRequest);
        List<Product> products = mapToProducts(productIds);
        Price total = totalSumPrice(products);
        validMenuPrice(menuRequest.getPrice(), total);
    }

    private static void validMenuPrice(BigDecimal menuPrice, Price total) {
        if (menuPrice.compareTo(total.value()) > 0) {
            throw new IllegalArgumentException("메뉴의 가격은 구성 상품의 총 합보다 클 수 없습니다.");
        }
    }

    private Price totalSumPrice(List<Product> products) {
        Price total = Price.from(ZERO);
        for (Product product : products) {
            total = total.sum(product.getPrice());
        }
        return total;
    }

    private List<Long> mapToProductIds(MenuRequest menuRequest) {
        return menuRequest.getMenuProducts()
                .stream()
                .map(MenuProductRequest::getProductId)
                .collect(Collectors.toList());
    }

    private List<Product> mapToProducts(List<Long> productsIds) {
        return productRepository.findAllByIdIn(productsIds);
    }

}