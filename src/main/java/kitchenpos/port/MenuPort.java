package kitchenpos.port;

import kitchenpos.domain.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuPort {
    Menu save(Menu entity);

    Menu findById(Long id);

    List<Menu> findAll();

    List<Menu> findAllByMenuId(List<Long> menuIds);
}