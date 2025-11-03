package lotto.util.validator;

import java.math.BigDecimal;
import java.util.List;

import lotto.common.constant.LottoConstant;
import lotto.common.message.ErrorMessage;

public final class LottoValidator {
    private LottoValidator() {}

    public static void validateLottoNumbers(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    public static void validateBonusNumber(int bonusNumber) {
        if (bonusNumber < LottoConstant.LOTTO_START_NUMBER || bonusNumber > LottoConstant.LOTTO_END_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NUMBER.message());
        }
    }

    public static void validateWinningNumbers(List<Integer> numbers) {
        validateSize(numbers);
        validateRange(numbers);
        validateDuplicate(numbers);
    }

    private static void validateSize(List<Integer> numbers) {
        if (numbers.size() != LottoConstant.LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_SIZE.message());
        }
    }

    private static void validateRange(List<Integer> numbers) {
        if (numbers.stream().anyMatch(number -> number < LottoConstant.LOTTO_START_NUMBER || number > LottoConstant.LOTTO_END_NUMBER)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_RANGE.message());
        }
    }

    private static void validateDuplicate(List<Integer> numbers) {
        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_LOTTO_NUMBER_DUPLICATE.message());
        }
    }

    public static void validateBudget(BigDecimal amount) {
        validateBudgetAmount(amount);
        validateBudgetAmountUnit(amount);
    }

    private static void validateBudgetAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(LottoConstant.MIN_PURCHASE_AMOUNT)) < 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT.message());
        }
    }

    private static void validateBudgetAmountUnit(BigDecimal amount) {
        if (amount.remainder(BigDecimal.valueOf(LottoConstant.LOTTO_PRICE)).compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT.message());
        }
    }
}
