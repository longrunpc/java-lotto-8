package lotto.domain.finance;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Profit {
    private static final int SCALE = 1;
    private final BigDecimal rate;

    private Profit(BigDecimal totalReward, BigDecimal totalPurchaseAmount) {
        this.rate = calculateRate(totalReward, totalPurchaseAmount);
    }

    public static Profit of(BigDecimal totalReward, BigDecimal totalPurchaseAmount) {
        return new Profit(totalReward, totalPurchaseAmount);
    }

    private BigDecimal calculateRate(BigDecimal totalReward, BigDecimal totalPurchaseAmount) {
        totalPurchaseAmount = validateTotalPurchaseAmount(totalPurchaseAmount);
        return totalReward
                .divide(totalPurchaseAmount, SCALE + 2, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100))
                .setScale(SCALE, RoundingMode.HALF_UP);
    }

    private BigDecimal validateTotalPurchaseAmount(BigDecimal totalPurchaseAmount) {
        if (totalPurchaseAmount.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        return totalPurchaseAmount;
    }

    public BigDecimal value() {
        return rate;
    }

    public String formatted() {
        return rate + "%";
    }

    @Override
    public String toString() {
        return formatted();
    }
}
