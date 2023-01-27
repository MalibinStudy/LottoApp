import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Money
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException
import java.lang.NumberFormatException

class LottoNumberTest {
    @ValueSource(ints = [1,2,3,4,5,45])
    @ParameterizedTest
    fun `LOTTO_NUMBER가_정상일_때_잘_동작하는지?`(number : Int) {
        val actualNumber = LottoNumber.of(number)
        assertThat(actualNumber).isEqualTo(LottoNumber.of(number))
    }

    @ValueSource(ints = [0,-2,63,47,53,46])
    @ParameterizedTest
    fun `LOTTO_NUMBER가_비정상일_때_잘_동작하는지?`(number : Int) {
        val actualException = kotlin.runCatching { LottoNumber.of(number) }.exceptionOrNull()
        assertThat(actualException).hasMessageThat().contains(number.toString())
    }

    /** 이걸 걸러낼 수는 없을까? */
    @ValueSource(ints = [1,2,3,5,5,45])
    @ParameterizedTest
    fun `LOTTO_NUMBER가_중복일_때_잘_동작하는지?`(number : Int) {
        val actualNumber = LottoNumber.of(number)
        assertThat(actualNumber).isEqualTo(LottoNumber.of(number)) /** 이건 되는데 왜 뒤에 .number 가 붙으면 실패하는지? */
    }

    //빈 숫자?, null
}