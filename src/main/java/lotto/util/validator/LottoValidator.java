package lotto.util.validator;

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

    public static void validateBonusNumber(List<Integer> winningNumbers, int bonusNumber) {
        validateBonusNumberRange(bonusNumber);
        validateBonusNumberDuplicate(winningNumbers, bonusNumber);
    }
    
    private static void validateBonusNumberRange(int bonusNumber) {
        if (bonusNumber < LottoConstant.LOTTO_START_NUMBER || bonusNumber > LottoConstant.LOTTO_END_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NUMBER.message());
        }
    }
    
    private static void validateBonusNumberDuplicate(List<Integer> winningNumbers, int bonusNumber) {
        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BONUS_NUMBER_DUPLICATE.message());
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
}
