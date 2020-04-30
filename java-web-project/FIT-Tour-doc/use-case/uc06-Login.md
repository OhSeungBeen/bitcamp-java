# UC06 - 로그인(LogIn)
회원이 로그인을 하는 유스케이스

## 주 액터(Primary Actor)
- 회원
- 관리자
## 보조 액터(Secondary Actor)
- 없음

## 사전 조건(Preconditions)
- 회원 가입 되어 있는 상태이어야 한다.

## 종료 조건(Postconditions)
- 로그인 완료 하였다.

## 시나리오(Flow of Evnets)

### 기본 흐름(Basic Flows)

1. 액터가 '로그인' 버튼을 클릭할 때 이 유스케이스를 시작한다.
- 시스템은 로그인 폼을  출력한다.
2. 액터는 아이디, 비밀번호를 입력한 후,
     로그인 버튼을 클릭할 때
- 시스템은 회원을 식별후 '메인'유스케이스로 간다
 

### 대안 흐름(Alternative Flows)

- 2.1 액터가 구글 버튼을 클릭하면,
    - 시스템은 '구글 로그인 하기' 유스케이스로 간다.
- 2.2 액터가 인스타그램 버튼을 클릭하면,
    - 시스템은 '인스타그램 로그인 하기' 유스케이스로 간다.
- 2.3 액터가 트위터 버튼을 클릭하면,
    - 시스템은 '트위터 로그인 하기' 유스케이스로 간다.
- 2.4 액터가 네이버 버튼을 클릭하면,
    - 시스템은 '네이버 로그인 하기' 유스케이스로 간다.
- 2.5 액터가 비밀번호 찾기 버튼을 클릭하면,
    - 시스템은 '비밀번호 찾기' 유스케이스로 간다.


### 예외 흐름(Exception Flows)

- 1.1 아이디,비밀번호 중 한 개라도 값이 일치하지 않으면,
    - 시스템은 입력항목이 일치하지 않는 내용을 출력한다.



## UI 프로토타입


###