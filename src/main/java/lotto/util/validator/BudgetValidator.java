package lotto.util.validator;

import java.math.BigDecimal;

import lotto.common.constant.LottoConstant;
import lotto.common.message.ErrorMessage;

public class BudgetValidator {
    private BudgetValidator() {}

    public static void validateBudget(BigDecimal amount) {
        validateBudgetAmount(amount);
        validateBudgetAmountUnit(amount);
        validateLottoCountOverflow(amount);
    }

    private static void validateBudgetAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(LottoConstant.MIN_PURCHASE_AMOUNT)) <= 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT.message());
        }
    }
    
    private static void validateBudgetAmountUnit(BigDecimal amount) {
        if (amount.remainder(BigDecimal.valueOf(LottoConstant.LOTTO_PRICE)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT.message());
        }
    }

    private static void validateLottoCountOverflow(BigDecimal amount) {
        BigDecimal lottoPrice = BigDecimal.valueOf(LottoConstant.LOTTO_PRICE);
        BigDecimal count = amount.divide(lottoPrice);

        if (count.compareTo(BigDecimal.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException(ErrorMessage.EXCEEDED_PURCHASE_LIMIT.message());
        }
    }
}
