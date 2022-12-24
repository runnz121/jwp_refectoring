package kitchenpos.table.infrastructure.repository;

import kitchenpos.table.domain.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTableJpaRepository extends JpaRepository<OrderTable, Long> {

    List<OrderTable> findAllByIdIn(List<Long> id);

    List<OrderTable> findAllByTableGroupId(Long tableGroupId);

    List<OrderTable> findAllByTableGroupIdIn(List<Long> tableGroupIds);
}