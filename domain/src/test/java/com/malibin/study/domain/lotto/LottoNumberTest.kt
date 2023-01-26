package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LottoNumberTest {
    @ParameterizedTest
    @ValueSource(ints = [1, 2, 45])
    fun `45이하의 자연수로 LottoNumber 객체를 가져올 수 있다`(num: Int) {
        // when
        val actualLottoNumber = LottoNumber.of(num)
        // then
        assertThat(actualLottoNumber.number).isEqualTo(num)
    }

    @Test
    fun `45이하의 자연수가 아닌 수로 LottoNumber 객체를 가져올 수없다`() {
        // given
        val invalidNum = 0
        // when
        val actualException = runCatching { LottoNumber.of(invalidNum) }.exceptionOrNull()
        // then
        assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
    }
}
