package lotto.view;

import java.util.List;

import lotto.dto.PurchasedLotto;

public class OutputView {
    public static void printLottos(List<PurchasedLotto> purchasedLottos) {
        printPurchaseCount(purchasedLottos.size());
        printPurchasedLottos(purchasedLottos);
    }

    private static void printPurchaseCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }
    
    private static void printPurchasedLottos(List<PurchasedLotto> purchasedLottos) {
        for (PurchasedLotto purchasedLotto : purchasedLottos) {
            System.out.println(purchasedLotto.lottos());
        }
    }
}
