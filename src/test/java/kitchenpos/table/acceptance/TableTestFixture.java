package kitchenpos.table.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import java.util.List;
import java.util.stream.Collectors;
import kitchenpos.TestFixture;
import kitchenpos.table.dto.OrderTableRequest;
import kitchenpos.table.dto.OrderTableResponse;

public class TableTestFixture extends TestFixture {

    public static final String ORDER_TABLE_BASE_URI = "/api/tables";

    public static ExtractableResponse<Response> 주문_테이블_생성_요청함(OrderTableRequest orderTable) {
        return post(ORDER_TABLE_BASE_URI, orderTable);
    }

    public static ExtractableResponse<Response> 주문_테이블_조회_요청함() {
        return get(ORDER_TABLE_BASE_URI);
    }

    public static ExtractableResponse<Response> 주문_테이블_상태_변경_요청함(Long orderTableId, OrderTableRequest orderTable) {
        return put(ORDER_TABLE_BASE_URI+"/"+orderTableId+"/empty", orderTable);
    }

    public static ExtractableResponse<Response> 주문_테이블_방문_손님_수_변경_요청함(Long orderTableId, OrderTableRequest orderTable) {
        return put(ORDER_TABLE_BASE_URI+"/"+orderTableId+"/number-of-guests", orderTable);
    }

    public static void 주문_테이블_요청_응답됨(ExtractableResponse<Response> response) {
        ok(response);
    }

    public static void 주문_테이블_생성됨(ExtractableResponse<Response> response) {
        created(response);
    }

    public static void 주문_테이블_조회_포함됨(ExtractableResponse<Response> response, List<ExtractableResponse<Response>> orderTableResponse) {
        List<Long> actualIds = response.jsonPath()
                .getList(".", OrderTableResponse.class)
                .stream()
                .map(OrderTableResponse::getId)
                .collect(Collectors.toList());

        List<Long> expectIds = orderTableResponse.stream()
                .map(r -> r.as(OrderTableResponse.class))
                .collect(Collectors.toList())
                .stream()
                .map(OrderTableResponse::getId)
                .collect(Collectors.toList());

        assertThat(actualIds).containsAll(expectIds);
    }

    public static void 주문_테이블_상태_변경됨(ExtractableResponse<Response> actual, OrderTableRequest expect) {
        OrderTableResponse request = actual.as(OrderTableResponse.class);
        assertThat(request.isEmpty()).isEqualTo(expect.getEmpty());
    }

    public static void 주문_테이블_손님_수_변경됨(ExtractableResponse<Response> actual, OrderTableRequest expect) {
        OrderTableResponse request = actual.as(OrderTableResponse.class);
        assertThat(request.getNumberOfGuests()).isEqualTo(expect.getNumberOfGuests());
    }
}