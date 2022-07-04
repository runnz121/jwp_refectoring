package kitchenpos.common.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Price {
    @Column(name = "price")
    private BigDecimal value;

    protected Price() {
    }

    public Price(final BigDecimal value) {
        if (Objects.isNull(value) || value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException();
        }

        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}