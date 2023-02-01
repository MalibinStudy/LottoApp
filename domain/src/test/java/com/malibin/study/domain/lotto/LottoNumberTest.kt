package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class LottoNumberTest {
    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `number가 범위(1~45)를 벗어날 경우 예외를 발생시킨다`(number:Int) {
        //when
        val exception = runCatching { LottoNumber.of(number) }.exceptionOrNull()
        //then
        assertAll(
            { assertThat(exception).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(exception).hasMessageThat()
                    .contains("입력 숫자 ($number)는 로또 숫자 범위(1 ~ 45) 내의 숫자가 아닙니다.")
            }
        )
    }
}