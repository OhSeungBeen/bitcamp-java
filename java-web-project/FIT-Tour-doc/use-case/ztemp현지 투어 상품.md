### 현지 투어 상품
관리자가 게시물 등록,변경,조회,삭제 할수 있고,
회원, 비회원은 조회 하는 유스케이스 이다.

## 주 액터(Primary Actor)
관리자

## 보조 액터(Secondary Actor)
회원
비회원

## 사전 조건(Preconditions)

## 종료 조건(Postconditions)

## 시나리오(Flow of Evnets)

### 기본 흐름(Basic Flows)

- 1. 액터가 '국가별 투어' 클릭할 때 이 유스케이스를 시작한다.
- 2. 시스템은 리스트 목록을  출력한다.
- 3. 액터는 상품 목록을 클릭하면
- 4. 시스템은  '투어 상세 리스트' 유스케이스로 간다.
 

### 대안 흐름(Alternative Flows)

- 1.1 액터가 필터조건을 설정하면,
    - 시스템은 설정된 필터조건에 따라 카테고리를 출력한다.
- 1.2 액터가 체크박스 옵션을 선택하면,
    - 시스템은 상단 리스트에 출력한후,
    - 설정된 카테고리 목록을 출력한다.
- 1.3 관리자가 상품등록 버튼을 클릭하면,
    - 시스템은 '상품등록' 유스케이스로 간다.
- 1.4 액터가 페이지 버튼을 클릭하면,
    - 시스템은 해당 페이지의 리스트 목록을 출력한다.

### 예외 흐름(Exception Flows)

- 1.1 보조 액터가 상품 등록 버튼을 클릭하면,
    - 시스템은 권한이 없는 사용자임을 알리는 내용을 출력한다.



