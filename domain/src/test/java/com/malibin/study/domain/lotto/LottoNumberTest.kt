package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LottoNumberTest {

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `로또 번호 범위 이외의 숫자를 가져올 수 없다`(invalidLottoNumber: Int) {
        // when
        val actualException = runCatching { LottoNumber.of(invalidLottoNumber) }.exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("입력 숫자 ($invalidLottoNumber)는 로또 숫자 범위(1 ~ 45) 내의 숫자가 아닙니다.")
            }
        )
    }
}
