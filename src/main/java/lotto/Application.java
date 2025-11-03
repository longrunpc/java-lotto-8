package lotto;

import lotto.config.DiFactory;
import lotto.controller.LottoController;

public class Application {
    public static void main(String[] args) {
        LottoController lottoController = DiFactory.createLottoController();
        lottoController.run();
    }
}
