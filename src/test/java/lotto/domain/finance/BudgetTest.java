package lotto.domain.finance;

import lotto.common.message.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BudgetTest {

    @Nested
    @DisplayName("Budget 생성")
    class CreateBudgetTest {

        @DisplayName("정상적인 금액으로 Budget을 생성할 수 있다.")
        @ParameterizedTest
        @CsvSource({
                "1000, 1",
                "5000, 5",
                "10000, 10",
                "100000, 100"
        })
        void should_create_Budget_when_amount_is_valid(String amount, int expectedCount) {
            // when
            Budget budget = Budget.create(amount);

            // then
            assertThat(budget).isNotNull();
            assertThat(budget.calculateLottoCount()).isEqualTo(expectedCount);
        }

        @DisplayName("최소 구매 금액보다 작은 금액이면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"-1000", "-500", "-100", "-10"})
        void should_throw_exception_when_amount_is_less_than_minimum(String amount) {
            // when & then
            assertThatThrownBy(() -> Budget.create(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_PURCHASE_AMOUNT.message());
        }

        @DisplayName("로또 가격의 배수가 아닌 금액이면 예외가 발생한다.")
        @ParameterizedTest
        @ValueSource(strings = {"1500", "2500", "3300", "5500", "9999"})
        void should_throw_exception_when_amount_is_not_multiple_of_lotto_price(String amount) {
            // when & then
            assertThatThrownBy(() -> Budget.create(amount))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_PURCHASE_AMOUNT_UNIT.message());
        }
    }

    @Nested
    @DisplayName("로또 개수 계산")
    class CalculateLottoCountTest {

        @DisplayName("calculateLottoCount는 정확한 로또 개수를 계산한다.")
        @ParameterizedTest
        @CsvSource({
                "1000, 1",
                "5000, 5",
                "10000, 10",
                "100000, 100",
                "500000, 500"
        })
        void should_calculate_lotto_count_correctly(String amount, int expectedCount) {
            // given
            Budget budget = Budget.create(amount);

            // when
            int lottoCount = budget.calculateLottoCount();

            // then
            assertThat(lottoCount).isEqualTo(expectedCount);
        }
    }
}

