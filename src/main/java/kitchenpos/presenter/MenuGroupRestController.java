package kitchenpos.presenter;

import kitchenpos.application.MenuGroupService;
import kitchenpos.domain.MenuGroup;
import kitchenpos.dto.MenuGroupRequest;
import kitchenpos.dto.MenuGroupResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class MenuGroupRestController {
    private final MenuGroupService menuGroupService;

    public MenuGroupRestController(final MenuGroupService menuGroupService) {
        this.menuGroupService = menuGroupService;
    }

    @PostMapping("/api/menu-groups")
    public ResponseEntity<MenuGroupResponse> create(@RequestBody final MenuGroupRequest request) {
        MenuGroupResponse result = menuGroupService.create(request);
        final URI uri = URI.create("/api/menu-groups/" + result.getId());
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("/api/menu-groups")
    public ResponseEntity<List<MenuGroup>> list() {
        return ResponseEntity.ok().body(menuGroupService.list());
    }
}