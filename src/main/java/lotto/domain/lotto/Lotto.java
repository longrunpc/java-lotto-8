package lotto.domain.lotto;

import java.util.Comparator;
import java.util.List;

import lotto.dto.PurchasedLotto;
import lotto.util.validator.LottoValidator;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        LottoValidator.validateLottoNumbers(numbers);
        this.numbers = numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public PurchasedLotto toPurchasedLotto() {
        return new PurchasedLotto(sorted());
    }

    private List<Integer> sorted() {
        return numbers.stream()
            .sorted(Comparator.naturalOrder())
            .toList();
    }
}
