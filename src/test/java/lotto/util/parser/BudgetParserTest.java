package lotto.util.parser;

import lotto.common.message.ErrorMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class BudgetParserTest {
    private BudgetParser budgetParser;

    @BeforeEach
    void setUp() {
        budgetParser = new BudgetParser();
    }

    @Nested
    @DisplayName("예산 파싱")
    class ParseSuccess {
        @DisplayName("숫자 문자열을 BigDecimal로 변환한다")
        @ParameterizedTest
        @CsvSource({
                "0, 0",
                "1000, 1000",
                "5000, 5000",
                "123456, 123456"
        })
        void should_parse_numeric_string_to_bigdecimal(String input, String expected) {
            BigDecimal result = budgetParser.parseBudget(input);
            assertThat(result).isEqualByComparingTo(new BigDecimal(expected));
        }

        @DisplayName("숫자가 아닌 입력이면 예외가 발생한다")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "abc", "1000a", "-", "+", "1,000"})
        void should_throw_exception_when_input_is_not_numeric(String input) {
            assertThatThrownBy(() -> budgetParser.parseBudget(input))
                    .isInstanceOf(IllegalArgumentException.class);
        }
    }
}
