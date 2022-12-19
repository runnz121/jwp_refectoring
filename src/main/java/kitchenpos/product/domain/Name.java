package kitchenpos.product.domain;

import static org.springframework.util.StringUtils.*;

import javax.persistence.Embeddable;

import kitchenpos.product.exception.InvalidNameException;

@Embeddable
public class Name {
    private String name;

    public Name() {
    }

    public Name(String name) {
        if (!hasText(name)) {
            throw new InvalidNameException();
        }
        this.name = name;
    }

    public String value() {
        return name;
    }
}