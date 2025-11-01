package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BonusNumberTest {
    @DisplayName("보너스 번호 생성 시 잘못된 문자열이 입력되면 예외가 발생한다.")
    @Test
    void invalidInputStringTest() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new BonusNumber("1,2,3" , winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new BonusNumber("1a" , winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호에 잘못된 범위의 숫자가 있으면 예외가 발생한다..")
    @Test
    void invalidRangeTest() {
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        assertThatThrownBy(() -> new BonusNumber("46" , winningLotto))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("보너스 번호가 당첨 번호와 중복되면 예외가 발생한다..")
    @Test
    void duplicateNumberTest() {
        assertThatThrownBy(() -> new Lotto(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}