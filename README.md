# Step 1 - LottoApp
## 1단계
### Money class Unit Test
- [x] Monet 객체에 음수가 들어갈 수 있는지 테스트
- [x] Money 객체에 0이 들어갈 수 있는지 테스트
- [x] Money 객체끼리 합이 가능한지 테스트
- [x] Money 객체끼리 뺄셈이 가능한지 테스트
- [x] Money 객체는 작은 수에서 큰 수를 빼면 에러 발생하는지 테스트
- [x] Money객체의 amount가 같으면 같은 객체로 판단하는지 테스트

### LottoNumber class Unit Test
- [x] (1 .. 45) 범위의 수를 통해 LottoNumber 객체를 가져올 수 있다.
- [x] (1 .. 45) 범위를 벗어난 수를 통해 LottoNumber 객체를 가져올 수 없다.

### LottoTicket class Unit Test
- [x] LottoTicket은 6개의 숫자로 만들 수 있다.
- [x] LottoTicket은 크기가 6개의 LottoNumber Set으로 만들 수 있다.
- [x] LottoTicket은 크기가 6개의 LottoNumber List로 만들 수 있다.
- [x] LottoNumber가 6개가 아니면 LottoTicket을 만들 수 없다.
- [x] 중복된 LottoNumber가 포함된 크기가 6개의 LottoNumber 리스트로는 LottoTicket을 만들 수 없다.
- [x] countMatchingNumbers() 는 LottoTicket의 LottoNumber와 다른 LottoTicket의 LottoNumber를 비교하여 일치하는 숫자의 개수를 반환한다.
- [x] has() 는 LottoTicket에 특정 LottoNumber가 포함되어 있는지 확인한다.

## 2단계
### Prize class Unit Test
- [X] 로또 번호와 맞는 숫자 개수와 보너스 숫자의 여부에 따라 find()함수를 통해 Prize를 얻을 수 있다.

### WinningTicket class Unit Test
- [X] WinningTicket을 생성할 때, LottoTicket와 LottoNumber가 중복되면 에러가 발생한다.
- [X] compareWith() 을 통해 LottoTicket과 일치하는 Prize를 얻을 수 있다.
- [X] compareWith() 을 통해 List<LottoTicket>과 일치하는 Map<Prize, Int>를 얻을 수 있다.