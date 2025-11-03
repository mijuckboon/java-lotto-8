package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class BonusNumberTest {
    Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));

    @DisplayName("보너스 번호 생성 시 잘못된 문자열이 입력되면 예외가 발생한다.")
    @ParameterizedTest(name = "test {index}: input={0}")
    @ValueSource(strings = {"1,2,3", "1a"})
    void invalidInputStringTest(String input) {
        assertThatThrownBy(() -> new BonusNumber(input, winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호에 잘못된 범위의 숫자가 있으면 예외가 발생한다..")
    @Test
    void invalidRangeTest() {
        assertThatThrownBy(() -> new BonusNumber("46" , winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다..")
    @Test
    void duplicateNumberTest() {
        assertThatThrownBy(() -> new BonusNumber("5", winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

}