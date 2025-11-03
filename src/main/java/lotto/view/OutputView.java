package lotto.view;

import java.util.List;

import lotto.common.message.OutputMessage;
import lotto.dto.PurchasedLotto;

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
}
