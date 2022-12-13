package kitchenpos.menu.ui;

import kitchenpos.ControllerTest;
import kitchenpos.menu.application.MenuService;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuGroup;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menu.dto.MenuRequest;
import kitchenpos.menu.dto.MenuResponse;
import net.jqwik.api.Arbitraries;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(MenuRestController.class)
public class MenuRestControllerTest extends ControllerTest {
    @MockBean
    private MenuService menuService;

    @DisplayName("메뉴생성을 요청하면 생성된 메뉴를 응답")
    @Test
    public void returnMenu() throws Exception {
        MenuResponse menu =  MenuResponse.of(Menu.builder()
                .id(Arbitraries.longs().between(1, 100).sample())
                .name(Arbitraries.strings().ofMinLength(5).ofMaxLength(15).sample())
                .menuGroup(MenuGroup.builder().build())
                .menuProducts(Collections.EMPTY_LIST)
                .price(BigDecimal.valueOf(15000))
                .build());
        doReturn(menu).when(menuService).create(any(MenuRequest.class));

        webMvc.perform(post("/api/menus")
                .content(mapper.writeValueAsString(Menu.builder().price(BigDecimal.valueOf(1000)).build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(menu.getId().intValue())))
                .andExpect(jsonPath("$.name", is(menu.getName())))
                .andExpect(jsonPath("$.price", is(menu.getPrice().intValue())))
                .andExpect(status().isCreated());
    }

    @DisplayName("메뉴생성을 요청하면 메뉴생성 실패응답")
    @Test
    public void throwsExceptionWhenMenuCreate() throws Exception {
        doThrow(new IllegalArgumentException()).when(menuService).create(any(MenuRequest.class));

        webMvc.perform(post("/api/menus")
                .content(mapper.writeValueAsString(Menu.builder().price(BigDecimal.valueOf(1000)).build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("메뉴목록을 요청하면 메뉴목록을 응답")
    @Test
    public void returnMenus() throws Exception {
        List<MenuResponse> menus = getMenus();
        doReturn(menus).when(menuService).list();

        webMvc.perform(get("/api/menus"))
                .andExpect(status().isOk());
    }

    private MenuResponse getMenu() {
        return MenuResponse.of(Menu.builder()
                .id(Arbitraries.longs().between(1, 100).sample())
                .name(Arbitraries.strings().ofMinLength(5).ofMaxLength(15).sample())
                .price(BigDecimal.valueOf(15000))
                .menuGroup(MenuGroup.builder().id(Arbitraries.longs().between(1, 50).sample()).build())
                .menuProducts(getMenuProducts())
                .build());
    }

    private List<MenuResponse> getMenus() {
        return IntStream.rangeClosed(1, 5)
                .mapToObj(value -> getMenu())
                .collect(Collectors.toList());
    }

    private List<MenuProduct> getMenuProducts() {
        return IntStream.rangeClosed(1, 5)
                .mapToObj(value -> MenuProduct.builder()
                        .menu(Menu.builder().price(BigDecimal.valueOf(1500)).build())
                        .seq(Arbitraries.longs().between(1, 20).sample()).build())
                .collect(Collectors.toList());
    }
}
