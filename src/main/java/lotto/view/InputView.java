package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.message.InputMessage;

public class InputView {
    public String readPurchaseAmount() {
        System.out.println(InputMessage.PURCHASE_AMOUNT.message());
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public String readWinningNumbers() {
        System.out.println(InputMessage.WINNING_NUMBERS.message());
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public String readBonusNumber() {
        System.out.println(InputMessage.BONUS_NUMBER.message());
        String input = Console.readLine();
        System.out.println();
        return input;
    }
}
