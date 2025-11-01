package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaymentTest {
    @DisplayName("지불 금액이 단위 금액(1000)으로 나누어 떨어지지 않으면 예외가 발생한다.")
    @Test
    void nondivisiblePaymentTest() {
        assertThatThrownBy(() -> new Payment("100"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지불 금액이 0이면 예외가 발생한다.") // 분모가 0이 되기 때문
    @Test
    void zeroPaymentTest() {
        assertThatThrownBy(() -> new Payment("0"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지불 금액이 음수이면 예외가 발생한다.")
    @Test
    void negativePaymentTest() {
        assertThatThrownBy(() -> new Payment("-10000"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지불 금액이 한도(100000)를 초과하면 예외가 발생한다.")
    @Test
    void limitOverPaymentTest() {
        assertThatThrownBy(() -> new Payment("101000"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("지불 금액이 자연수가 아니면 예외가 발생한다.")
    @Test
    void floatPaymentTest() {
        assertThatThrownBy(() -> new Payment("1000.1"))
                .isInstanceOf(IllegalArgumentException.class);
    }

}