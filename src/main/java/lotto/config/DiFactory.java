package lotto.config;

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
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        LottoGenerator lottoGenerator = RandomLottoGenerator.getInstance();
        LottoParser lottoParser = new LottoParser();
        BudgetParser budgetParser = new BudgetParser();
    
        return new LottoController(
            inputView,
            outputView,
            lottoGenerator,
            lottoParser,
            budgetParser
        );
    }
    
}
