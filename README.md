### 1단계 기능목록

#### Money
1. 입력 금액이 음수일 때 잘 걸러내는지?
2. 입력 금액이 0 혹은 Null은 아닌지? 
   -> Money의 코드에서 0은 합격인데 걸러야 하지 않는지?, 입력값 null 체크는 어떻게 해야하는지?
3. 입력 금액에 문자가 들어올 때 잘 걸러내는지?
4. plus, minus operator 함수는 잘 동작하는지? -> 연산에 에러가 없는지?


#### LottoNumber
1. 입력금액이 정상일 때 잘 동작하는지?
2. 입력금액이 비정상일 때 (범위보다 큰/작은 수)일때 예외처리를 잘 하는지?


#### LottoTicket
1. 로또번호가 중복값이 있을 때 예외처리를 잘 하는지?
2. 로또번호가 6개가 아닐 때 (작을 때 / 많을 때) 예외처리를 잘 하는지?
3. 