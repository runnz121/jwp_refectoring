# 키친포스

## 요구 사항

### To Do List
- 정상적으로 동작중인 프로그램 리팩토링
    - [x] properties 분리
    - [x] 로그 설정 하기
    - [x] UI 인수 테스트 작성
    - [x] SERVICE 단위 테스트 작성
    

- 상품 테스트
    - [ ] 상품을 등록 한다.
    - [ ] 상품 가격은 0원 이상 이여야 한다.
    - [ ] 상품 목록을 조회 한다.


- 메뉴 그룹 테스트
    - [ ] 메뉴 그룹을 생 성한다.
    - [ ] 그룹 명은 필수 입력 사항 이다.
    - [ ] 메뉴 그룹 목록을 조회 한다.


- 메뉴 테스트
    - [ ] 메뉴를 등록 한다.
    - [ ] 메뉴 가격이 0 이상 이어야 한다.
    - [ ] 메뉴 그룹 지정이 지정 되어 있어야 한다.
    - [ ] 메뉴의 상품이 모두 등록 되어 있어야 한다.
    - [ ] 상품들 금액의 합이 메뉴 가격 보다 작거나 같아야 한다.
    - [ ] 메뉴 목록을 조회 한다.


- 테이블 테스트
    - [ ] 주문 테이블 을 생성 한다.
    - [ ] 주문 테이블 목록을 조회 한다.
    - [ ] 주문 테이블 을 빈 테이블 로 변경 한다.
    - [ ] 테이블 그룹에 속해 있지 않아야 한다.
    - [ ] 주문 상태가 (조리 중,식사 중)인 테이블 이면 안된다.
    - [ ] 주문 테이틀 손님 수를 변경 한다.
    - [ ] 손님 수는 0명 이상 이어야 한다.
    - [ ] 빈 테이블 상태가 아니 어야 한다.


- 테이블 그룹 테스트
    - [ ] 테이블 그룹을 생성 한다.
    - [ ] 주문 테이블 들의 수가 2개 이상 이어야 한다.
    - [ ] 주문 테이블 들이 존재 해야 한다.
    - [ ] 주문 테이블 들이 모두 빈 테이블 이어야 한다.
    - [ ] 테이블 그룹을 삭제 한다.
    - [ ] 주문 상태가 (조리 중,식사 중)인 테이블 은 그룹은 삭제가 안된다.


- 주문 테스트
    - [ ] 주문을 생성 한다.
    - [ ] 주문 테이블 이 존재 해야 한다.
    - [ ] 주문 테이블 이 빈 테이블 상태가 아니 어야 한다.
    - [ ] 주문 항목이 1개 이상 이어야 한다.
    - [ ] 모든 주문 항목 메뉴가 존재 해야 한다.
    - [ ] 주문 목록을 조회 한다.
    - [ ] 주문 상태를 변경 한다.
    - [ ] 주문 상태가 완료가 아니 어야 한다.

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
 
