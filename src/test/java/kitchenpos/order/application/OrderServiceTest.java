package kitchenpos.order.application;

import kitchenpos.menu.application.MenuService;
import kitchenpos.menu.domain.Menu;
import kitchenpos.menu.domain.MenuProduct;
import kitchenpos.menugroup.domain.MenuGroup;
import kitchenpos.order.domain.*;
import kitchenpos.order.dto.OrderLineItemRequest;
import kitchenpos.order.dto.OrderRequest;
import kitchenpos.order.dto.OrderResponse;
import kitchenpos.order.dto.OrderStatusRequest;
import kitchenpos.order.exception.IllegalOrderException;
import kitchenpos.order.exception.NoSuchOrderException;
import kitchenpos.order.validator.OrderValidator;
import kitchenpos.ordertable.application.TableService;
import kitchenpos.ordertable.domain.OrderTable;
import kitchenpos.product.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static kitchenpos.utils.fixture.MenuFixtureFactory.createMenu;
import static kitchenpos.utils.fixture.MenuGroupFixtureFactory.createMenuGroup;
import static kitchenpos.utils.fixture.MenuProductFixtureFactory.createMenuProduct;
import static kitchenpos.utils.fixture.OrderFixtureFactory.createOrder;
import static kitchenpos.utils.fixture.OrderLineItemFixtureFactory.createOrderLineItem;
import static kitchenpos.utils.fixture.OrderTableFixtureFactory.createOrderTable;
import static kitchenpos.utils.fixture.ProductFixtureFactory.createProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    MenuService menuService;
    @Mock
    OrderRepository orderRepository;
    @Mock
    TableService tableService;
    @Mock
    OrderValidator orderValidator;
    @InjectMocks
    OrderService orderService;


    private MenuGroup 메뉴그룹_한식;
    private Product 김치찌개;
    private Menu 메뉴_김치찌개세트;
    private MenuProduct 김치찌개세트_김치찌개;
    private OrderTable 테이블_1;
    private OrderTable 테이블_2;
    private OrderTable 테이블_Empty;
    private Order 접수된_주문;
    private Order 완료된_주문;
    private OrderLineItem 접수된주문_김치찌개세트;
    private OrderLineItem 완료된주문_김치찌개세트;

    @BeforeEach
    void setUp() {
        메뉴그룹_한식 = createMenuGroup(1L, "한식메뉴");
        김치찌개 = createProduct(1L, "김치찌개", 8000);
        김치찌개세트_김치찌개 = createMenuProduct(1L, 김치찌개.getId(), 2);
        메뉴_김치찌개세트 = createMenu(1L, "김치찌개세트", 15000, 메뉴그룹_한식.getId(),
                Arrays.asList(김치찌개세트_김치찌개));

        테이블_1 = createOrderTable(1L, null, 4, false);
        접수된주문_김치찌개세트 = createOrderLineItem(1L, 메뉴_김치찌개세트.getId(), 1);
        접수된_주문 = createOrder(1L, 테이블_1.getId(), Arrays.asList(접수된주문_김치찌개세트));

        테이블_2 = createOrderTable(2L, null, 4, false);
        완료된주문_김치찌개세트 = createOrderLineItem(2L, 메뉴_김치찌개세트.getId(), 1);
        완료된_주문 = createOrder(2L, 테이블_2.getId(), Arrays.asList(완료된주문_김치찌개세트));
        완료된_주문.changeStatus(OrderStatus.COMPLETION);

        테이블_Empty = createOrderTable(3L, null, 0, true);
    }

    @DisplayName("주문을 등록할 수 있다")
    @Test
    void 주문_등록(){
        //given
        given(orderRepository.save(any(Order.class))).willReturn(접수된_주문);

        //when
        OrderRequest orderRequest = OrderRequest.of(테이블_1.getId(), Arrays.asList(
                OrderLineItemRequest.of(메뉴_김치찌개세트.getId(), 1)
        ));
        OrderResponse savedOrder = orderService.create(orderRequest);

        //then
        assertThat(savedOrder).isEqualTo(OrderResponse.from(접수된_주문));
    }

    @DisplayName("주문의 목록을 조회할 수 있다")
    @Test
    void 주문_목록_조회(){
        //given
        given(orderRepository.findAll()).willReturn(Arrays.asList(접수된_주문));

        //when
        List<OrderResponse> list = orderService.list();

        //then
        assertThat(list).containsExactly(OrderResponse.from(접수된_주문));
    }

    @DisplayName("주문의 상태를 업데이트할 수 있다")
    @ParameterizedTest(name = "이전 주문상태: {0}, 새로운 주문상태: {1}")
    @MethodSource("provideParametersForOrderStateUpdate")
    void 주문_상태_업데이트(OrderStatus beforeStatus, OrderStatus afterStatus){
        //given
        given(orderRepository.findById(anyLong())).willReturn(Optional.of(접수된_주문));
        Order 주문_MEAL = createOrder(테이블_1.getId(), Arrays.asList(접수된주문_김치찌개세트));
        주문_MEAL.changeStatus(beforeStatus);

        //when
        OrderResponse orderResponse = orderService.changeOrderStatus(접수된_주문.getId(),
                OrderStatusRequest.from(afterStatus));

        //then
        assertThat(orderResponse.getOrderStatus()).isEqualTo(afterStatus);
    }

    private static Stream<Arguments> provideParametersForOrderStateUpdate() {
        return Stream.of(
                Arguments.of(OrderStatus.COOKING, OrderStatus.MEAL),
                Arguments.of(OrderStatus.MEAL, OrderStatus.COMPLETION)
        );
    }

    @DisplayName("주문이 존재해야 주문상태를 업데이트할 수 있다")
    @Test
    void 주문_상태_업데이트_주문_검증(){
        //given
        given(orderRepository.findById(anyLong())).willThrow(NoSuchOrderException.class);

        //when
        OrderStatusRequest orderStatusRequest = OrderStatusRequest.from(OrderStatus.MEAL);

        //then
        assertThrows(NoSuchOrderException.class,
                () -> orderService.changeOrderStatus(접수된_주문.getId(), orderStatusRequest));
    }

    @DisplayName("주문상태가 COMPLETION이면 주문상태를 업데이트할 수 없다")
    @Test
    void 주문_상태_업데이트_COMPLETION_검증(){
        //given
        given(orderRepository.findById(anyLong())).willReturn(Optional.of(완료된_주문));

        //when
        OrderStatusRequest orderStatusRequest = OrderStatusRequest.from(OrderStatus.MEAL);

        //then
        assertThrows(IllegalOrderException.class,
                () -> orderService.changeOrderStatus(완료된_주문.getId(), orderStatusRequest));
    }
}