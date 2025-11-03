package lotto.domain.finance;

public class Budget {
    private final int amount;

    private Budget(int amount) {
        this.amount = amount;
    }

    public static Budget create(int amount) {
        validate(amount);
        return new Budget(amount);
    }

    private static void validate(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 0원 이상이어야 합니다.");
        }

        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위로 입력해야 합니다.");
        }
    }
}
