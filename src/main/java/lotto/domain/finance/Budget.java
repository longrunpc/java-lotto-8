package lotto.domain.finance;

public class Budget {
    private final int amount;

    private Budget(int amount) {
        this.amount = amount;
    }

    public static Budget create(int amount) {
        return new Budget(amount);
    }
}
