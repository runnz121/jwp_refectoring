package kitchenpos.tableGroup.repository;

import kitchenpos.tableGroup.domain.TableGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableGroupRepository extends JpaRepository<TableGroup, Long> {
}