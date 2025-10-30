package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.Lotto;
import lotto.LottoRank;
import lotto.TotalResult;

public class OutputView {
    private static final String PAYMENT_INPUT_MESSAGE = "구입금액을 입력해 주세요.";
    private static final String LOTTOS_OUTPUT_MESSAGE = """
            %d개를 구매했습니다.
            %s""";
    private static final String WINNING_NUMBERS_INPUT_MESSAGE = "당첨 번호를 입력해 주세요.";
    private static final String BONUS_NUMBER_INPUT_MESSAGE = "보너스 번호를 입력해 주세요.";
    private static final String FINAL_RESULT_MESSAGE = """
            당첨 통계
            ---
            %s
            총 수익률은 %.1f%%입니다.""";
    private static final String RANK_RESULT_MESSAGE = "%d개 일치%s (%s원) - %d개";
    private static final String BONUS_NUMBER_MATCHES_MESSAGE = ", 보너스 볼 일치";

    public static void printPaymentInputMessage() {
        println(PAYMENT_INPUT_MESSAGE);
    }

    public static void printLottosOutputMessage(List<Lotto> lottos) {
        StringBuilder messageBuilder = new StringBuilder();
        for (Lotto lotto : lottos) {
            messageBuilder.append(lotto.getNumbers()).append(System.lineSeparator());
        }
        println(LOTTOS_OUTPUT_MESSAGE.formatted(lottos.size(), messageBuilder.toString()));
    }

    public static void printWinningNumbersInputMessage() {
        println(WINNING_NUMBERS_INPUT_MESSAGE);
    }

    public static void printBonusNumberInputMessage() {
        println(BONUS_NUMBER_INPUT_MESSAGE);
    }

    public static void printFinalResultMessage(TotalResult totalResult, long payment) {
        Map<LottoRank, Integer> ranks = totalResult.getRanks();
        double rateOfReturn = totalResult.getRoundedRateOfReturn(payment);

        String rankResultMessage = getRankResultMessage(ranks);
        String finalResultMessage = FINAL_RESULT_MESSAGE.formatted(rankResultMessage, rateOfReturn);
        println(finalResultMessage);
    }

    private static String getRankResultMessage(Map<LottoRank, Integer> ranks) {
        StringBuilder messageBuilder = new StringBuilder();
        for (LottoRank rank : Arrays.stream(LottoRank.values()).toList().reversed()) {
            String bonusNumberPart = "";
            if (rank.getMatchBonusCount() != 0) {
                bonusNumberPart = BONUS_NUMBER_MATCHES_MESSAGE;
            }
            messageBuilder.append(RANK_RESULT_MESSAGE.formatted(rank.getMatchCount(), bonusNumberPart, String.format("%,d", rank.getPrize()), ranks.getOrDefault(rank, 0))).append(System.lineSeparator());
        }
        return messageBuilder.toString();

    }

    private static void println(String message) {
        System.out.println(message);
    }
}
