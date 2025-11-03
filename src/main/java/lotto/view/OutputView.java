package lotto.view;

import java.util.List;

import lotto.common.message.OutputMessage;
import lotto.domain.finance.Profit;
import lotto.dto.PurchasedLotto;
import lotto.dto.WinningReportEntry;

public class OutputView {
    public static void printLottos(List<PurchasedLotto> purchasedLottos) {
        printPurchaseCount(purchasedLottos.size());
        printPurchasedLottos(purchasedLottos);
    }

    private static void printPurchaseCount(int count) {
        System.out.println(OutputMessage.PURCHASE_COUNT.message().formatted(count));
    }

    private static void printPurchasedLottos(List<PurchasedLotto> purchasedLottos) {
        for (PurchasedLotto purchasedLotto : purchasedLottos) {
            System.out.println(purchasedLotto.lottos());
        }
    }

    public static void printWinningStatistics(List<WinningReportEntry> entries, Profit totalProfitRate) {
        printWinningHeader();
        printWinningEntries(entries);
        printTotalProfitRate(totalProfitRate);
    }

    private static void printWinningHeader() {
        System.out.println(OutputMessage.WINNING_STATISTICS.message());
        System.out.println(OutputMessage.WINNING_STATISTICS_SEPARATOR.message());
    }

    private static void printWinningEntries(List<WinningReportEntry> entries) {
        for (WinningReportEntry entry : entries) {
            if (entry.hasBonus()) {
                String message = OutputMessage.WINNING_REPORT_ENTRY_WITH_BONUS.message()
                    .formatted(entry.matchCount(), entry.prize(), entry.count());
                System.out.println(message);
                continue;
            }
    
            String message = OutputMessage.WINNING_REPORT_ENTRY.message()
                .formatted(entry.matchCount(), entry.prize(), entry.count());
            System.out.println(message);
        }
    }
    
    private static void printTotalProfitRate(Profit totalProfitRate) {
        System.out.println(OutputMessage.TOTAL_PROFIT_RATE.message().formatted(totalProfitRate));
    }
}
