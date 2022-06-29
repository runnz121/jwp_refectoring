# 키친포스

## 요구 사항
### 메뉴 그룹
* 메뉴 그룹을 등록한다.
* 메뉴 그룹 전체를 조회한다.

### 메뉴
* 메뉴를 등록한다
  * 메뉴의 가격은 0원보다 커야 한다.
  * 메뉴 그룹이 이미 등록되어 있어야 한다.
  * 메뉴 상품이 모두 등록 되어 있어야 한다.
  * 메뉴 가격은 메뉴에 속한 메뉴 상품 가격의 합보다 작아야 한다. 
* 메뉴 전체를 조회 한다.
### 주문
* 주문을 등록한다.
  * 주문 항목이 존재해야 한다.
  * 주문 항목에 중복된 것이 존재하면 안된다.
  * 주문 테이블이 이미 등록되어 있어야 한다.
  * 주문 테이블은 빈 값일 수 없다.
* 주문 전체를 조회 한다.
* 주문 상태를 변경한다.
  * 주문이 존재해야 한다.
  * 주문 상태가 완료이면 변경할 수 없다.
### 상품
* 상품을 등록한다.
  * 상품의 가격이 0보다 작을 수 없다.
* 상품 전체를 조회한다
### 단체 지정
* 단체 지정을 생성한다.
  * 주문 테이블이 2개 이상이여야 한다
  * 주문 테이블이 모두 등록되어 있어야 한다.
  * 주문 테이블이 빈값이거나 이미 단체 지정이 되어 있으면 안된다
* 단체 지정을 해제한다.
  * 조리, 식사 중인 상태인 경우에는 해제할 수 없다.

### 주문 테이블
* 주문 테이블을 생성 한다.
* 주문 테이블 전체를 조회 한다.
* 주문 테이블을 빈 테이블로 수정한다.
  * 단체 지정이 되어있으면 안된다
  * 조리, 식사 중인 경우 안된다.
* 주문 테이블의 손님 수를 변경한다.
  * 손님의 수는 0보다 작을 수 없다.
  * 주문 테이블이 이미 등록되어 있어야 한다.
  * 주문 테이블이 빈 값이면 안된다.
## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |
