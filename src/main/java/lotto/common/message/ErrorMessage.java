package lotto.common.message;

public enum ErrorMessage {
    INVALID_PURCHASE_AMOUNT("[ERROR] 구입 금액은 1000원 이상이어야 합니다."),
    INVALID_PURCHASE_AMOUNT_UNIT("[ERROR] 구입 금액은 1000원 단위로 입력해야 합니다."),
    EXCEEDED_PURCHASE_LIMIT("[ERROR] 구입 가능한 로또 개수는 2,147,483,647개 입니다."),

    INVALID_LOTTO_SIZE("[ERROR] 로또 번호는 6개여야 합니다."),

    INVALID_WINNING_NUMBERS("[ERROR] 당첨번호 입력 형식이 올바르지 않습니다."),
    INVALID_BONUS_NUMBER("[ERROR] 보너스 번호 입력 형식이 올바르지 않습니다."),
    INVALID_BUDGET("[ERROR] 구입 금액 입력 형식이 올바르지 않습니다."),

    INVALID_LOTTO_NUMBER_RANGE("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다."),
    INVALID_LOTTO_NUMBER_DUPLICATE("[ERROR] 로또 번호는 중복되지 않아야 합니다."),
    INVALID_BONUS_NUMBER_DUPLICATE("[ERROR] 보너스 번호는 당첨번호와 중복되지 않아야 합니다.");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
