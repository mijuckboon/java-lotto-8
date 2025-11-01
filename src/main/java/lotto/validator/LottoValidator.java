package lotto.validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lotto.domain.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

/**
 * 로또 유효성 검증 클래스
 */
public class LottoValidator {
    /**
     * Lotto 생성을 위한 문자열 파싱 검증 메서드
     * @param input 입력 문자열
     */
    public static void validateNumbersInput(String input) {
        try {
            String[] splittedWinningNumbers = input.split(",");
            Arrays.stream(splittedWinningNumbers)
                    .forEach(Integer::parseInt);
        } catch (RuntimeException e) {
            String message = ErrorMessage.INVALID_INPUT_STRING.format(input);
            throw new CustomException(message);
        }
    }

    /**
     * Lotto 생성을 위한 숫자 목록 검증 메서드
     * @param numbers 로또 숫자 목록
     */
    public static void validate(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateDistinctness(numbers);
        validateRange(numbers);
    }

    private static void validateNumberCount(List<Integer> numbers) {
        if (!hasCorrectNumberCount(numbers)) {
            String message = ErrorMessage.INVALID_NUMBER_COuNT.format(
                    LottoPolicy.NUMBER_COUNT_TO_CHOOSE, numbers.size()
            );
            throw new CustomException(message);
        }
    }

    private static boolean hasCorrectNumberCount(List<Integer> numbers) {
        return numbers.size() == LottoPolicy.NUMBER_COUNT_TO_CHOOSE;
    }

    private static void validateDistinctness(List<Integer> numbers) {
        if (hasDuplicateNumbers(numbers)) {
            String message = ErrorMessage.DUPLICATE_NUMBER.format(numbers);
            throw new CustomException(message);
        }
    }

    private static boolean hasDuplicateNumbers(List<Integer> numbers) {
        return numbers.size() != new HashSet<>(numbers).size();
    }

    private static void validateRange(List<Integer> numbers) {
        Integer invalidNumber = findInvalidNumber(numbers);
        if (invalidNumber != null) {
            String message = ErrorMessage.INVALID_RANGE.format(
                    LottoPolicy.MIN_NUMBER, LottoPolicy.MAX_NUMBER, invalidNumber
            );
            throw new CustomException(message);
        }
    }

    private static Integer findInvalidNumber(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> hasInvalidRange(number))
                .findFirst()
                .orElse(null);
    }

    private static boolean hasInvalidRange(int number) {
        return number < LottoPolicy.MIN_NUMBER || number > LottoPolicy.MAX_NUMBER;
    }
}
