package kitchenpos.menu.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class MenuTest {

    @Test
    @DisplayName("Menu의 정상 생성을 확인한다.")
    void createMenu() {
        MenuProduct menuProduct = new MenuProduct(10L, 10);
        Menu menu = new Menu("메뉴", BigDecimal.TEN, 10L, Collections.singletonList(menuProduct));

        assertThat(menu.getName()).isEqualTo("메뉴");
        assertThat(menu.getPrice()).isEqualTo(BigDecimal.TEN);
        assertThat(menu.getMenuGroupId()).isEqualTo(10L);
        assertThat(menu.getMenuProducts()).hasSize(1);
    }

    @ParameterizedTest(name = "{0}, {1}, {3}, {4} -> {5}")
    @DisplayName("Menu 생성시 실페 케이스를 체크한다.")
    @MethodSource("providerCreateMenuFailCase")
    void createMenuFail(String name, BigDecimal price, Long menuGroupId, List<MenuProduct> menuProducts, Class<? extends Exception> exception) {
        assertThatExceptionOfType(exception)
            .isThrownBy(() -> new Menu(name, price, menuGroupId, menuProducts));
    }

    private static Stream<Arguments> providerCreateMenuFailCase() {
        MenuProduct menuProduct = new MenuProduct(10L, 1);

        return Stream.of(
            Arguments.of(null, BigDecimal.TEN, 10L, Collections.singletonList(menuProduct), NullPointerException.class),
            Arguments.of("상품", null, 10L, Collections.singletonList(menuProduct), NullPointerException.class),
            Arguments.of("상품", BigDecimal.TEN, null, Collections.singletonList(menuProduct), NullPointerException.class),
            Arguments.of("상품", BigDecimal.TEN, 10L, Collections.emptyList(), IllegalArgumentException.class)
        );
    }

}
