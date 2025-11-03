package lotto.controller;

import java.math.BigDecimal;

import lotto.domain.finance.Budget;
import lotto.domain.finance.Profit;
import lotto.domain.lotto.LottoGenerator;
import lotto.domain.lotto.Lottos;
import lotto.domain.winning.WinningLotto;
import lotto.domain.winning.WinningResult;
import lotto.dto.WinningReport;
import lotto.util.parser.BudgetParser;
import lotto.util.parser.LottoParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;
    private final OutputView outputView;
    private final LottoGenerator lottoGenerator;
    private final LottoParser lottoParser;
    private final BudgetParser budgetParser;

    public LottoController(
        InputView inputView,
        OutputView outputView,
        LottoGenerator lottoGenerator,
        LottoParser lottoParser,
        BudgetParser budgetParser
    ) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.lottoGenerator = lottoGenerator;
        this.lottoParser = lottoParser;
        this.budgetParser = budgetParser;
    }

    public void run() {
        Budget budget = requestBudget();
        Lottos lottos = purchaseLottos(budget);
        WinningLotto winningLotto = requestWinningLotto();
        WinningResult result = calculateWinningResult(lottos, winningLotto);
        printResult(result, budget);
    }

    private Budget requestBudget() {
        String rawAmount = inputView.readPurchaseAmount();
        BigDecimal parsedAmount = budgetParser.parseBudget(rawAmount);
        return Budget.create(parsedAmount);
    }

    private Lottos purchaseLottos(Budget budget) {
        Lottos lottos = Lottos.generate(budget.calculateLottoCount(), lottoGenerator);
        outputView.printLottos(lottos.toPurchasedLottos());
        return lottos;
    }

    private WinningLotto requestWinningLotto() {
        String rawWinningNumbers = inputView.readWinningNumbers();
        String rawBonusNumber = inputView.readBonusNumber();

        return WinningLotto.create(
            lottoParser.parseWinningNumbers(rawWinningNumbers),
            lottoParser.parseBonusNumber(rawBonusNumber)
        );
    }

    private WinningResult calculateWinningResult(Lottos lottos, WinningLotto winningLotto) {
        return WinningResult.from(lottos, winningLotto);
    }

    private void printResult(WinningResult result, Budget budget) {
        WinningReport report = result.toReport();
        BigDecimal totalPrize = result.calculateTotalPrize();
        Profit profit = Profit.of(totalPrize, budget.getAmount());
        outputView.printWinningStatistics(report.entries(), profit);
    }
}
