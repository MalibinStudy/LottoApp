import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Money
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class LottoNumberTest {
    @ValueSource(ints = [1, 2, 3, 4, 5, 45])
    @ParameterizedTest
    fun `Lotto 숫자 범위 내의 숫자들로 LottoNumber를 생성할 수 있다`(number: Int) {
        val actualNumber = LottoNumber.of(number)
        assertThat(actualNumber).isEqualTo(LottoNumber.of(number))
    }

    @Test
    fun `Lotto 숫자 범위 외의 숫자가 들어올 시 LottoNumber를 생성할 수 없다`() {
        val number = -1
        val actualException = kotlin.runCatching { LottoNumber.of(number) }.exceptionOrNull()
        assertThat(actualException).hasMessageThat().contains(number.toString())
    }
}