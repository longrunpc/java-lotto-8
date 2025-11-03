package lotto.domain.lotto;

import java.util.List;

import lotto.util.validator.LottoValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validateLottoNumbers(numbers);
        this.numbers = numbers;
    }
}
