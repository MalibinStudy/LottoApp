package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.assertAll
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

    @ParameterizedTest
    @ValueSource(ints = [0, 46])
    fun `45이하의 자연수가 아닌 수로 LottoNumber 객체를 가져올 수 없다`(inValidNum: Int) {
        // when
        val actualException = runCatching { LottoNumber.of(inValidNum) }.exceptionOrNull()
        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            { assertThat(actualException?.message).isEqualTo("입력 숫자 ($inValidNum)는 로또 숫자 범위(1 ~ 45) 내의 숫자가 아닙니다.") }
        )
    }
}
