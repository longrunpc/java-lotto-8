package lotto.common.message;

public enum OutputMessage {
    PURCHASE_COUNT("%d개를 구매했습니다."),
    WINNING_STATISTICS("당첨 통계"),
    WINNING_STATISTICS_SEPARATOR("---"),
    WINNING_REPORT_ENTRY("%d개 일치 (%,d원) - %d개"),
    WINNING_REPORT_ENTRY_WITH_BONUS("%d개 일치, 보너스 볼 일치 (%,d원) - %d개"),
    TOTAL_PROFIT_RATE("총 수익률은 %s입니다.");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
