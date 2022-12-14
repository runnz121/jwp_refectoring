package kitchenpos.ui;

import java.util.stream.Collectors;
import kitchenpos.application.MenuService;
import kitchenpos.domain.Menu;
import kitchenpos.dto.MenuRequest;
import kitchenpos.dto.MenuResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MenuRestController {
    private final MenuService menuService;

    public MenuRestController(final MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/api/menus")
    public ResponseEntity<MenuResponse> create(@RequestBody final MenuRequest menuRequest) {
        final Menu created = menuService.create(menuRequest);
        MenuResponse menuResponse = MenuResponse.of(created);
        final URI uri = URI.create("/api/menus/" + menuResponse.getId());
        return ResponseEntity.created(uri)
                .body(menuResponse)
                ;
    }

    @GetMapping("/api/menus")
    public ResponseEntity<List<MenuResponse>> list() {
        List<MenuResponse> menuResponses = menuService.list().stream()
                .map(
                        MenuResponse::of
                ).collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(menuResponses)
                ;
    }
}
