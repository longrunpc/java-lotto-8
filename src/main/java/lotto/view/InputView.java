package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public static String readPurchaseAmount() {
        System.out.println("구입 금액을 입력해 주세요.");
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public static String readWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        System.out.println();
        return input;
    }

    public static String readBonusNumber() {
        System.out.println("보너스 번호를 입력해 주세요.");
        String input = Console.readLine();
        System.out.println();
        return input;
    }
}
