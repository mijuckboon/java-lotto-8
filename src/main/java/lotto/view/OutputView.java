package lotto.view;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoRank;
import lotto.domain.Payment;
import lotto.domain.TotalResult;

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
            messageBuilder.append(lotto.getNumbers())
                    .append(System.lineSeparator());
        }
        println(LOTTOS_OUTPUT_MESSAGE.formatted(lottos.size(), messageBuilder.toString()));
    }

    public static void printWinningNumbersInputMessage() {
        println(WINNING_NUMBERS_INPUT_MESSAGE);
    }

    public static void printBonusNumberInputMessage() {
        println(BONUS_NUMBER_INPUT_MESSAGE);
    }

    public static void printFinalResultMessage(TotalResult totalResult, Payment payment) {
        Map<LottoRank, Integer> ranks = totalResult.getRanks();
        double rateOfReturn = totalResult.getRateOfReturn(payment);

        String rankStatisticsMessage = getRankStatisticsMessage(ranks);
        String finalResultMessage = getFinalResultMessage(rankStatisticsMessage, rateOfReturn);
        println(finalResultMessage);
    }

    private static String getRankStatisticsMessage(Map<LottoRank, Integer> ranks) {
        StringBuilder messageBuilder = new StringBuilder();
        final List<LottoRank> ranksInDescOrder = Arrays.stream(LottoRank.values()).toList().reversed();
        for (LottoRank rank : ranksInDescOrder) {
            String rankResultMessage = getRankResultMessage(ranks, rank);
            messageBuilder.append(rankResultMessage)
                    .append(System.lineSeparator());
        }
        return messageBuilder.toString();
    }

    private static String getRankResultMessage(Map<LottoRank, Integer> lottoRanks, LottoRank lottoRank) {
        int matchCount = lottoRank.getMatchCount();
        String bonusNumberPart = getBonusNumberPartMessage(lottoRank);
        String formattedPrize = getFormattedPrize(lottoRank);
        int countOfLottoRank = getCountOfLottoRank(lottoRanks, lottoRank);
        return RANK_RESULT_MESSAGE.formatted(
                matchCount, bonusNumberPart, formattedPrize, countOfLottoRank
        );
    }

    private static String getBonusNumberPartMessage(LottoRank lottoRank) {
        if (lottoRank.getMatchBonusCount() != 0) {
            return BONUS_NUMBER_MATCHES_MESSAGE;
        }
        return "";
    }

    private static String getFormattedPrize(LottoRank lottoRank) {
        return String.format("%,d", lottoRank.getPrize()); // 세 자리마다 , 포시
    }

    private static int getCountOfLottoRank(Map<LottoRank, Integer> lottoRanks, LottoRank lottoRank) {
        return lottoRanks.getOrDefault(lottoRank, 0);
    }

    private static String getFinalResultMessage(String rankResultMessage, double rateOfReturn) {
        return FINAL_RESULT_MESSAGE.formatted(rankResultMessage, rateOfReturn);
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
