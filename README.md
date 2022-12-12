# 키친포스

## 요구 사항

### 상품
- 메뉴를 관리하는 기준이 되는 데이터이다. 
- 이름과 가격정보를 관리한다.
- 상품 목록 조회 기능을 제공한다.
- 상품등록 기능을 제공한다.
  - 이름은 중복될 수 없다
  - 가격은 0원이상이여야 한다.

### 메뉴
- 상품을 주문하는 실제 주문 가능 단위이다.
- 이름, 가격, 주문하는 상품목록, 메뉴 분류 정보를 관리한다.
- 메뉴 목록 조회 기능을 제공한다.
- 메뉴를 생성하는 기능을 제공한다..
  - 이름은 중복될 수 없다
  - 가격은 0원이상이여야 한다.
  - 주문하는 상품목록은 비어있을 수 없다.
  - 상품의 총합이 메뉴가격보다 커야 한다.

### 메뉴 그룹
- 메뉴의 묶음으로 메뉴의 분류단위이다.
- 이름 정보를 관리한다.
- 메뉴 그룹 목록 조회 기능을 제공한다.
- 메뉴 그룹을 생성한다.
  - 메뉴그룹 이름은 중복될 수 없다.
  
### 주문
- 매장에서 발생하는 주문 데이터이다.
- 주문한 테이블 정보, 주문상태, 주문시간, 주문한메뉴와 수량정보를 관리한다.
- 주문목록을 조회하는 기능을 제공한다.
- 주문을 생성하는 기능을 제공한다.
  - 비어있는 주문항목으로는 주문을 생성할 수 없다.
  - 존재하지 않는 메뉴로 구성된 주문항목 목록으로는 주문을 생성할 수 없다.
  - 존재하지 않는 주문테이블로 주문을 생성할 수 없다.
  - 빈 상태인 주문테이블로는 주문을 생성할 수 없다.
- 주문상태를 수정하는 기능을 제공한다.
  - 이미 주문한 정보만 상태를 수정할 수 있다.
  - 완료상태인 주문은 상태를 변경할 수 없다.

### 주문 테이블
- 매장에서 주문이 발생하는 영역
- 테이블그룹 정보, 손님 수, 테이블이 비어있는지에 대한 상태 정보를 관리한다.
- 테이블 목록을 조회하는 기능을 제공한다.
- 테이블 생성하는 기능을 제공한다.
- 테이블을 빈 상태로 변경하는 기능을 제공한다.
  - 존재하지 않는 테이블을 빈 상태로 변경 할 수 없다
  - 조리중이거나 식사중인 테이블은 변경할 수 없다
- 테이블의 게스트 숫자를 변경한다.
  - 0보다 작은 숫자로 변경할 수 없다.
  - 존재하지 않는 테이블의 게스트 숫자를 변경할 수 없다
  - 빈 상태 테이블의 게스트 숫자를 변경할 수 없다

### 테이블그룹
- 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능
- 그룹화 되어있는 테이블 목록, 생성시간에 대한 정보를 관리한다.
- 테이블그룹을 생성하는 기능을 제공한다.
  - 비어있는 테이블 목록으로 테이블그룹을 생성할 수 없다.
  - 테이블 목록이 하나인 경우 테이블그룹을 생성할 수 없다.
  - 존재하지 않는 테이블로 테이블그룹을 생성할 수 없다.
  - 비어있는 테이블로만 테이블그룹을 생성할 수 있다.
  - 테이블그룹이 이미 존재하는 테이블로는 테이블그룹을 새로 생성할 수 없다.
- 테이블그룹을 해제하는 기능을 제공한다.
  - 이미 존재하는 테이블그룹만 해제할 수 있다.
  - 조리중이거나 식사중인 테이블은 그룹해제할 수 없다.

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

## 프로그래밍 요구사항
- [x] 키친포스의 요구사항을 README.md에 작성
- [] 요구사항을 토대로 모든 Business Object에 대한 테스트를 작성
