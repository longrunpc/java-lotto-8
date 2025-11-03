package lotto.util.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoValidatorTest {

    @DisplayName("로또 번호가 유효하면 통과한다")
    @Test
    void validateLottoNumbers_should_pass_when_valid() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        assertThatNoException().isThrownBy(() -> LottoValidator.validateLottoNumbers(numbers));
    }

    @DisplayName("로또 번호 개수가 6개가 아니면 예외")
    @Test
    void validateLottoNumbers_should_throw_when_invalid_size() {
        List<Integer> five = List.of(1, 2, 3, 4, 5);
        List<Integer> seven = List.of(1, 2, 3, 4, 5, 6, 7);
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(five))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(seven))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호가 범위를 벗어나면 예외")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -1, 100})
    void validateLottoNumbers_should_throw_when_out_of_range(int invalid) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalid);
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("로또 번호에 중복이 있으면 예외")
    @Test
    void validateLottoNumbers_should_throw_when_duplicate_exists() {
        List<Integer> numbers = List.of(1, 2, 3, 3, 4, 5);
        assertThatThrownBy(() -> LottoValidator.validateLottoNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("당첨 번호 유효하면 통과한다")
    @Test
    void validateWinningNumbers_should_pass_when_valid() {
        List<Integer> numbers = List.of(10, 11, 12, 13, 14, 15);
        assertThatNoException().isThrownBy(() -> LottoValidator.validateWinningNumbers(numbers));
    }

    @DisplayName("당첨 번호가 범위를 벗어나면 예외")
    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void validateWinningNumbers_should_throw_when_out_of_range(int invalid) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, invalid);
        assertThatThrownBy(() -> LottoValidator.validateWinningNumbers(numbers))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 유효하면 통과한다")
    @Test
    void validateBonusNumber_should_pass_when_valid() {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 45; // not in winning and within range
        assertThatNoException().isThrownBy(() -> LottoValidator.validateBonusNumber(winning, bonus));
    }

    @DisplayName("보너스 번호가 범위를 벗어나면 예외")
    @ParameterizedTest
    @ValueSource(ints = {0, 46, -10, 100})
    void validateBonusNumber_should_throw_when_out_of_range(int bonus) {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(winning, bonus))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복이면 예외")
    @Test
    void validateBonusNumber_should_throw_when_duplicate_with_winning() {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 6; // duplicate
        assertThatThrownBy(() -> LottoValidator.validateBonusNumber(winning, bonus))
            .isInstanceOf(IllegalArgumentException.class);
    }
}


