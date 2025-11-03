package lotto.domain.finance;

import java.math.BigDecimal;

import lotto.common.constant.LottoConstant;
import lotto.util.validator.BudgetValidator;

public class Budget {
    private final BigDecimal amount;

    private Budget(BigDecimal amount) {
        this.amount = amount;
    }

    public static Budget create(String amount) {
        BudgetValidator.validateBudget(new BigDecimal(amount));
        return new Budget(new BigDecimal(amount));
    }

    public int calculateLottoCount() {
        return amount.divide(BigDecimal.valueOf(LottoConstant.LOTTO_PRICE)).intValue();
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
