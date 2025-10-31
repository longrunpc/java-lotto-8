package lotto.view;

import camp.nextstep.edu.missionutils.Console;
import lotto.common.InputMessage;

public class InputView {

    public static String readPurchaseAmount() {
        System.out.println(InputMessage.PURCHASE_AMOUNT.message());
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public static String readWinningNumbers() {
        System.out.println(InputMessage.WINNING_NUMBERS.message());
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public static String readBonusNumber() {
        System.out.println(InputMessage.BONUS_NUMBER.message());
        String input = Console.readLine();
        System.out.println();
        return input;
    }
}
