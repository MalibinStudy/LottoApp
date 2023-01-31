package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class LottoNumberTest {
    @Test
    fun `number가 범위(1~45)를 벗어날 경우 예외를 발생시킨다`() {
        //given
        val number = 46
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