package lotto.exception;

public enum ErrorMessage {
    INVALID_NUMBER_COuNT("로또 번호는 %d개여야 합니다. (입력된 개수: %d)"),
    DUPLICATE_NUMBER("로또 번호는 중복될 수 없습니다. (입력 번호: %s)"),
    INVALID_RANGE("번호는 %d와 %d 사이의 숫자여야 합니다. (입력 번호: %d)"),
    INVALID_PAYMENT_INPUT("구입 금액은 %d으로 나누어떨어지는 자연수여야 합니다. (입력값: %s)"),
    PAYMENT_LIMIT_EXCEED("구입 금액은 %d원을 초과할 수 없습니다. (입력값: %d)")
    ;

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = addPrefix(message);
    }

    public String format(Object... args) {
        return String.format(message, args);
    }

    private String addPrefix(String message) {
        return PREFIX + message;
    }
}
