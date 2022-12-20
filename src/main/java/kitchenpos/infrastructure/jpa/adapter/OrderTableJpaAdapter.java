package kitchenpos.infrastructure.jpa.adapter;


import kitchenpos.domain.OrderTable;
import kitchenpos.infrastructure.jpa.repository.OrderTableJpaRepository;
import kitchenpos.port.OrderTablePort;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static kitchenpos.constants.ErrorCodeType.ORDER_TABLE_NOT_FOUND;

@Service
@Transactional
public class OrderTableJpaAdapter implements OrderTablePort {

    private final OrderTableJpaRepository orderTableJpaRepository;

    public OrderTableJpaAdapter(OrderTableJpaRepository orderTableJpaRepository) {
        this.orderTableJpaRepository = orderTableJpaRepository;
    }

    @Override
    public OrderTable save(OrderTable entity) {
        return orderTableJpaRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderTable findById(Long id) {
        return orderTableJpaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(ORDER_TABLE_NOT_FOUND.getMessage()));
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderTable> findAll() {
        return orderTableJpaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderTable> findAllByIdIn(List<Long> ids) {
        return orderTableJpaRepository.findAllByIdIn(ids);
    }
}