package kitchenpos.application.menugroup;

import java.util.List;
import kitchenpos.domain.menugroup.MenuGroup;
import kitchenpos.domain.menugroup.MenuGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(final MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    public MenuGroup create(final MenuGroup menuGroup) {
        return this.menuGroupRepository.save(menuGroup);
    }

    @Transactional(readOnly = true)
    public List<MenuGroup> list() {
        return this.menuGroupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean notExistsMenuGroup(Long menuGroupId) {
        return !menuGroupRepository.existsById(menuGroupId);
    }

}