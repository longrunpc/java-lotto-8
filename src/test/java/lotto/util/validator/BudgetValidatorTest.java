package lotto.util.validator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BudgetValidatorTest {

    @DisplayName("1000원 단위 금액은 통과한다")
    @ParameterizedTest
    @ValueSource(strings = {"1000", "5000", "123000"})
    void should_pass_when_amount_is_valid_multiple_of_1000(String amount) {
        assertThatNoException()
            .isThrownBy(() -> BudgetValidator.validateBudget(new BigDecimal(amount)));
    }

    @DisplayName("0원 미만이면 예외")
    @ParameterizedTest
    @ValueSource(strings = {"-1", "-1000", "-999999"})
    void should_throw_when_amount_below_minimum(String amount) {
        assertThatThrownBy(() -> BudgetValidator.validateBudget(new BigDecimal(amount)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("1000원 단위가 아니면 예외")
    @ParameterizedTest
    @ValueSource(strings = {"1", "999", "1500", "1001", "1234567"})
    void should_throw_when_amount_not_multiple_of_1000(String amount) {
        assertThatThrownBy(() -> BudgetValidator.validateBudget(new BigDecimal(amount)))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("구입 개수가 Integer.MAX_VALUE를 초과하면 예외")
    @Test
    void should_throw_when_lotto_count_exceeds_integer_max() {
        BigDecimal lottoPrice = BigDecimal.valueOf(lotto.common.constant.LottoConstant.LOTTO_PRICE);
        BigDecimal overMaxCount = BigDecimal.valueOf((long) Integer.MAX_VALUE).add(BigDecimal.ONE);
        BigDecimal amount = lottoPrice.multiply(overMaxCount);

        assertThatThrownBy(() -> BudgetValidator.validateBudget(amount))
            .isInstanceOf(IllegalArgumentException.class);
    }
}


