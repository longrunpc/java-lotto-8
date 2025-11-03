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
        Budget budget = Budget.create(budgetParser.parseBudget(inputView.readPurchaseAmount()));

        Lottos lottos = Lottos.generate(budget.calculateLottoCount(), lottoGenerator);
        outputView.printLottos(lottos.toPurchasedLottos());

        WinningLotto winningLotto = WinningLotto.create(
            lottoParser.parseWinningNumbers(inputView.readWinningNumbers()),
            lottoParser.parseBonusNumber(inputView.readBonusNumber())
        );

        WinningResult winningResult = WinningResult.from(lottos, winningLotto);
        WinningReport report = winningResult.toReport();

        BigDecimal totalPrize = winningResult.calculateTotalPrize();
        Profit profit = Profit.of(totalPrize, budget.getAmount());

        outputView.printWinningStatistics(report.entries(), profit);
    }
}