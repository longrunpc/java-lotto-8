package lotto.config;

import lotto.common.message.ErrorMessage;
import lotto.controller.LottoController;
import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.RandomLottoGenerator;
import lotto.util.parser.BudgetParser;
import lotto.util.parser.LottoParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class DiFactory {

    private DiFactory() {
    }

    public static LottoController createLottoController() {
        try {
            InputView inputView = new InputView();
            OutputView outputView = new OutputView();
            LottoGenerator lottoGenerator = RandomLottoGenerator.getInstance();
            LottoParser lottoParser = new LottoParser();
            BudgetParser budgetParser = new BudgetParser();

            if (lottoGenerator == null || lottoParser == null || budgetParser == null) {
                throw new IllegalStateException(ErrorMessage.INVALID_LOTTO_CONTROLLER_DEPENDENCY.message());
            }

            return new LottoController(
                inputView,
                outputView,
                lottoGenerator,
                lottoParser,
                budgetParser
            );
        } catch (Exception e) {
            throw new IllegalStateException(ErrorMessage.INVALID_LOTTO_CONTROLLER_INJECTION.message());
        }
    }
    
}
