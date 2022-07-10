package kichenpos.menu.domain;

import java.util.List;
import java.util.Optional;

public interface MenuProductRepository {
    MenuProduct save(MenuProduct entity);

    Optional<MenuProduct> findById(Long id);

    List<MenuProduct> findAll();
}