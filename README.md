# java-lotto-precourse

### 기능 요구 사항
- 로또 번호의 범위는 1~45이다.
- 1개의 로또는 중복 없는 6개의 숫자로 구성된다.
- 당첨 번호는 중복 없는 6개 숫자와 보너스 번호 1개로 구성된다.
- 로또 1장의 가격은 1,000원이다.
- 구입 금액을 입력하면, 해당 금액에 맞는 개수의 로또를 발행한다.
- 당첨 번호와 보너스 번호를 입력받는다.
- 사용자가 구매한 모든 로또와 당첨 번호를 비교하여 당첨 내역과 수익률을 출력한 뒤 프로그램을 종료한다.

### 당첨 기준 및 상금
- 등수 및 상금은 다음과 같다.

| 등수 | 기준 | 상금 |
|---|---|---|
| 1등 | 6개 번호 일치 | 2,000,000,000원 |
| 2등 | 5개 번호 + 보너스 번호 일치 | 30,000,000원 |
| 3등 | 5개 번호 일치 | 1,500,000원 |
| 4등 | 4개 번호 일치 | 50,000원 |
| 5등 | 3개 번호 일치 | 5,000원 |

### 예외 및 에러 처리 규칙
- 잘못된 입력이 들어오면 `IllegalArgumentException`을 발생시킨다.
- 에러 메시지는 반드시 "[ERROR]"로 시작해야 한다.
- 동일 입력 단계에서 에러 발생 시, 해당 단계부터 입력을 다시 받는다.
- `Exception` 포괄 처리 금지. `IllegalArgumentException`, `IllegalStateException` 등 명확한 유형만 처리한다.

### 기능 구현 목록

#### 입력
-[x] 로또 구입 금액 입력 기능
-[x] 당첨 번호 입력 기능
-[x] 보너스 번호 입력 기능

#### 출력
-[] 구매한 로또 개수 출력 기능
-[] 구매한 로또 번호 목록 출력 기능
-[] 당첨 내역 출력 기능
-[] 수익률 출력 기능
-[] 에러 메시지 출력 기능

#### 파싱
-[x] 당첨 번호 파싱 기능

#### 검증
-[x] 로또 구입 금액 검증 기능
-[x] 당첨 번호 검증 기능
-[x] 보너스 번호 검증 기능

#### 로또 구매
-[x] 구입 금액으로 로또 개수 계산 기능
-[x] 로또 번호 생성 기능

#### 당첨 판단
-[x] 구매 번호와 당첨 번호 비교 기능
-[x] 보너스 번호 일치 여부 판정 기능
-[x] 등수 결정 기능

#### 수익 관리
-[x] 수익률 계산 기능

#### 출력 데이터 구성
-[x] 당첨 통계 포맷 생성 기능
-[x] 수익률 포맷 생성 기능

### 패키지 구조

```text
lotto
├─ Application
├─ common
│  ├─ constant
│  │  └─ LottoConstant
│  └─ message
│     ├─ ErrorMessage
│     ├─ InputMessage
│     └─ OutputMessage
├─ config
│  └─ DiFactory
├─ controller
│  └─ LottoController
├─ domain
│  ├─ finance
│  │  ├─ Budget
│  │  └─ Profit
│  ├─ lotto
│  │  ├─ Lotto
│  │  ├─ LottoGenerator
│  │  ├─ Lottos
│  │  └─ RandomLottoGenerator
│  └─ winning
│     ├─ Rank
│     ├─ WinningLotto
│     └─ WinningResult
├─ dto
│  ├─ PurchasedLotto
│  ├─ WinningReport
│  └─ WinningReportEntry
├─ util
│  ├─ parser
│  │  ├─ BudgetParser
│  │  └─ LottoParser
│  └─ validator
│     ├─ BudgetValidator
│     └─ LottoValidator
└─ view
   ├─ InputView
   └─ OutputView
```

### 클래스별 책임

- **root**
  - `Application`: 애플리케이션 시작점. DI 초기화 후 `LottoController` 실행.

- **common.constant**
  - `LottoConstant`: 로또 번호 범위, 개수, 가격 등 전역 상수 정의.

- **common.message**
  - `ErrorMessage`: 예외 상황별 에러 메시지 상수.
  - `InputMessage`: 입력 안내 메시지 상수.
  - `OutputMessage`: 출력 포맷/라벨 메시지 상수.

- **config**
  - `DiFactory`: controller, view, parser, validator, generator 등 객체 생성 및 의존성 주입 구성.

- **controller**
  - `LottoController`: 오케스트레이션. 입력 수집 → 로또 발행 → 당첨 비교 → 통계/수익률 계산 → 출력. 입력 단계별 예외 발생 시 해당 단계부터 재입력 흐름 제어.

- **domain.finance**
  - `Budget`: 구입 금액 값 객체. 금액 유효성 보장 및 구매 가능 매수 계산 제공.
  - `Profit`: 총 상금과 투자 대비 수익률 계산 로직 보유.

- **domain.lotto**
  - `Lotto`: 중복 없는 6개 숫자의 불변 컬렉션. 범위/중복/개수 검증 포함.
  - `LottoGenerator`: 로또 생성 전략 인터페이스.
  - `RandomLottoGenerator`: 난수 기반 로또 생성 구현.
  - `Lottos`: 구매한 복수의 `Lotto` 묶음. 순회/조회 및 크기 계산 제공.

- **domain.winning**
  - `Rank`: 등수와 상금 매핑, 일치 개수로 등수 조회 기능 제공.
  - `WinningLotto`: 당첨 번호 6개와 보너스 번호 보유. 주어진 `Lotto`와의 일치/보너스 판정 제공.
  - `WinningResult`: 여러 로또의 판정 결과 집계. 등수별 개수/총상금 합산 기능 제공.

- **dto**
  - `PurchasedLotto`: 구매 결과 전달용 DTO. 구매 매수와 번호 묶음 노출.
  - `WinningReport`: 출력용 당첨 통계 요약 DTO.
  - `WinningReportEntry`: 출력용 등수별 상세 항목 DTO.

- **util.parser**
  - `BudgetParser`: 문자열 입력을 `Budget`으로 파싱.
  - `LottoParser`: 문자열 입력을 로또 번호 `Lotto`로 파싱.

- **util.validator**
  - `BudgetValidator`: 금액 형식/음수/단위(1,000원) 등 검증.
  - `LottoValidator`: 숫자 개수, 범위(1~45), 중복 여부 등 검증.

- **view**
  - `InputView`: 입력 메시지 출력 및 사용자 입력 수집(문자열 단위).
  - `OutputView`: 구매 내역, 당첨 통계, 수익률 등 결과 포맷팅 출력.
