package lotto.domain.finance;

import java.math.BigDecimal;

import lotto.common.constant.LottoConstant;
import lotto.common.message.ErrorMessage;

public class Budget {
    private final BigDecimal amount;

    private Budget(BigDecimal amount) {
        this.amount = amount;
    }

    public static Budget create(String amount) {
        validate(new BigDecimal(amount));
        return new Budget(new BigDecimal(amount));
    }

    private static void validate(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(LottoConstant.MIN_PURCHASE_AMOUNT)) < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT.message());
        }

        if (amount.remainder(BigDecimal.valueOf(LottoConstant.LOTTO_PRICE)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT.message());
        }
    }

    public int calculateLottoCount() {
        return amount.divide(BigDecimal.valueOf(LottoConstant.LOTTO_PRICE)).intValue();
    }
}
