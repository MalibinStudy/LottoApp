package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class LottoNumberTest {
    @Test
    fun `number가 범위 내(1~45)의 정수여야 한다`() {
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