package lotto.util.parser;

import java.math.BigDecimal;

import lotto.common.message.ErrorMessage;

public class BudgetParser {
    private BudgetParser() {}  

    public static BigDecimal parseBudget(String input) {
        try {
            return new BigDecimal(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BUDGET.message());
        }
    }
}

