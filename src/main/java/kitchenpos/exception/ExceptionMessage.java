package kitchenpos.exception;

public class ExceptionMessage {
    public static final String INVALID_MENU_GROUP_NAME_SIZE = "메뉴 그룹 이름은 1자 이상이어야 합니다.";
    public static final String INVALID_PRODUCT_NAME_SIZE = "상품 이름은 1자 이상이어야 합니다.";
    public static final String INVALID_MENU_NAME_SIZE = "메뉴 이름은 1자 이상이어야 합니다.";
    public static final String INVALID_MENU_PRICE = "유효하지 않은 메뉴 가격입니다.";
    public static final String MENU_PRICE_GREATER_THAN_AMOUNT = "메뉴 가격이 메뉴 상품 금액(상품가격 * 상품수량) 보다 작거나 같아야합니다.";

    private ExceptionMessage() {
    }
}
