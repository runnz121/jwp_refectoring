# 키친포스

## 요구 사항

### 상품
* Create : 다음 조건을 모두 만족 할때, 생성 가능하다
    * 가격이 0원 이상인 경우
    * 이름이 존재하는 경우
    * 이름은 중복 가능하다
* Get : 
    * 상품목록을 조회할 수 있다

### 메뉴그룹
* Create : 다음 조건을 모두 만족 할때, 생성 가능하다
    * 메뉴그룹 이름이 존재 하는 경우
* Get : 
    * 메뉴그룹 목록을 조회할 수 있다
* Constraint : 
    * 메뉴그룹은 메뉴 생성 이전에 존재해야 한다

### 메뉴
* Create : 다음 조건을 모두 만족 할때, 생성 가능하다
    * 메뉴상품이 존재 하는 경우
    * 가격이 존재하며, 0원 이상이며, 메뉴상품의 총금액보다 같거나 낮은 경우
    * 소속된 메뉴그룹이 존재 하는 경우
    * 등록하려는 메뉴의 메뉴상품이 이미 등록된 경우
* Get : 
    * 메뉴 목록을 조회할 수 있다
    * 메뉴 목록 조회시, 메뉴 상품도 함께 조회할 수 있다
* Constraint : 
    * 메뉴는, 하나의 메뉴그룹에 포함되어야 한다
    * 메뉴는, 여러개의 메뉴상품을 가질 수 있다

### 주문
* Create : 다음 조건을 모두 만족 할때, 생성 가능하다
    * 모든 메뉴가 등록되어 있을 경우
    * 테이블이 비어있지 않을 경우
    * 주문 생성 후 , 주문 상태는 `조리`상태가 된다
* Get : 
    * 주문 목록을 조회할 수 있다
* Update : 
    * `계산완료`상태가 아닐 경우에만, 주문 갱신이 가능하다
* Constraint : 
    * 주문은, 하나 이상의 주문 항목을 가진다

### 단체 지정
* 다음 조건을 모두 만족 할때, 단체 지정 설정이 가능하다
    * 주문테이블 수가  2이상인 경우
    * 모든 주문 테이블이 등록된 경우
    * 모든 주문테이블이 아직 채워지지 않은 경우
    * 모든 주문테이블이 아직 단체지정 설정 되지 않은 경우
* 다음 조건을 모두 만족 할때, 단체 지정 설정 해제가 가능하다
    * 해당 주문테이블의 상태가 `결제완료`상태인 경우

### 주문 테이블
* Create :
    * `빈 테이블`을 등록할 수 있다
    * `채워진 테이블`을 등록할 수 있다
* Get : 
    * 주문 테이블 목록을 조회할 수 있다
* Update :
    *  다음 조건을 모두 만족 할때, 테이블의 상태를 `빈 테이블`로 변경 가능하다
        * 주문 테이블에 속한 주문 상태가 `계산 완료` 상태인 경우
    *  다음 조건을 모두 만족 할때, 방문 손님 수 변경이 가능하다
        * 주문 테이블이 비어있지 않은 경우
        * 방문 손님수가 1명 이상인 경우

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
| 주문 항목 | order line product | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |


## 🚀 1단계 - 테스트를 통한 코드 보호

### Requirement
요구 사항 1
- kitchenpos 패키지의 코드를 보고 키친포스의 요구 사항을 README.md에 작성한다.

요구 사항 2
- 정리한 키친포스의 요구 사항을 토대로 테스트 코드를 작성한다. 
- 모든 Business Object에 대한 테스트 코드를 작성한다. 
- @SpringBootTest를 이용한 통합 테스트 코드 또는 @ExtendWith(MockitoExtension.class)를 이용한 단위 테스트 코드를 작성한다.
- 인수 테스트 코드 작성은 권장하지만 필수는 아니다. 미션을 진행함에 있어 아래 문서를 적극 활용한다.

### 프로그래밍 요구 사항
- 이번 과정에서는 Lombok 없이 미션을 진행해 본다.

### 힌트
- http 디렉터리의 .http 파일(HTTP client)을 보고 어떤 요청을 받는지 참고한다.

### TODO
- [x] 요구 사항을 README.md에 작성
- [x] 요구 사항을 토대로 통합 테스트 코드 또는 단위테스트 작성
    - [x] ProductService
    - [x] MenuGroupService
    - [x] MenuService
    - [x] TableService
    - [x] OrderService
    - [x] TableGroupService

### Note
- 각 도메인 모델들의 동등성 비교는 PK로 비교 
-> 현재 로직에서, 영속된 모델들로만 로직이 구성되어있는지 확인이 되지 않았으므로, 실제 리팩토링 들어갈때 수정 필요
