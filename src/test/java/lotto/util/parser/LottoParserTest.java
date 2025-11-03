package lotto.util.parser;

import lotto.common.message.ErrorMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LottoParserTest {
    private LottoParser lottoParser;

    @BeforeEach
    void setUp() {
        lottoParser = new LottoParser();
    }

    static Stream<Arguments> winningNumberCases() {
        return Stream.of(
                Arguments.of("1,2,3,4,5,6", List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of(" 1 , 2 , 3 , 4 , 5 , 6 ", List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of("10,11,12,13,14,15", List.of(10, 11, 12, 13, 14, 15))
        );
    }

    static Stream<Arguments> bonusNumberCases() {
        return Stream.of(
                Arguments.of("0", 0),
                Arguments.of("1", 1),
                Arguments.of(" 45 ", 45)
        );
    }

    @Nested
    @DisplayName("당첨 번호 파싱")
    class ParseWinningNumbers {
        @DisplayName("쉼표로 구분된 숫자 문자열을 정수 리스트로 변환한다")
        @ParameterizedTest
        @MethodSource("lotto.util.parser.LottoParserTest#winningNumberCases")
        void should_parse_comma_separated_numbers_into_list(String input, List<Integer> expected) {
            List<Integer> result = lottoParser.parseWinningNumbers(input);
            assertThat(result).containsExactlyElementsOf(expected);
        }

        @DisplayName("숫자가 아닌 값이 있으면 예외가 발생한다")
        @ParameterizedTest
        @ValueSource(strings = {"1,2,3,4,5,a", "1,2, ,4,5,6", ",1,2,3,4,5,6", "1,,3,4,5,6"})
        void should_throw_exception_when_non_numeric_present(String input) {
            assertThatThrownBy(() -> lottoParser.parseWinningNumbers(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_WINNING_NUMBERS.message());
        }
    }

    @Nested
    @DisplayName("보너스 번호 파싱")
    class ParseBonusNumber {
        @DisplayName("숫자 문자열을 정수로 변환한다")
        @ParameterizedTest
        @MethodSource("lotto.util.parser.LottoParserTest#bonusNumberCases")
        void should_parse_numeric_string_to_int(String input, int expected) {
            int result = lottoParser.parseBonusNumber(input);
            assertThat(result).isEqualTo(expected);
        }

        @DisplayName("숫자가 아니면 예외가 발생한다")
        @ParameterizedTest
        @ValueSource(strings = {"", " ", "a", "1b", "+", "-", "1,000"})
        void should_throw_exception_when_not_numeric(String input) {
            assertThatThrownBy(() -> lottoParser.parseBonusNumber(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage(ErrorMessage.INVALID_BONUS_NUMBER.message());
        }
    }
}
