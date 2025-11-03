package lotto.domain.finance;

import lotto.common.constant.LottoConstant;
import lotto.common.message.ErrorMessage;

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
        if (amount < LottoConstant.MIN_PURCHASE_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT.message());
        }

        if (amount % LottoConstant.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT.message());
        }
    }

    public int calculateLottoCount() {
        return amount / LottoConstant.LOTTO_PRICE;
    }
}
