package lotto.validator;

import java.util.HashSet;
import java.util.List;
import lotto.LottoPolicy;
import lotto.exception.CustomException;
import lotto.exception.ErrorMessage;

public class LottoValidator {

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
