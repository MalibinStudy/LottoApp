package com.malibin.study.domain.lotto

import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

internal class LottoNumberTest {
    @Test
    fun `입력 숫자는 1보다 작을 수 없다`() {
        // given
        val number = 0

        // when
        val exception = kotlin.runCatching {
            LottoNumber.of(number)
        }.exceptionOrNull()

        // then
        Truth.assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `입력 숫자는 45보다 클 수 없다`() {
        // given
        val number = 46

        // when
        val exception = kotlin.runCatching {
            LottoNumber.of(number)
        }.exceptionOrNull()

        // then
        Truth.assertThat(exception).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `입력 숫자는 1 이상 45 이하의 범위여야 한다`() {
        // given
        val number = 10

        // when
        val actualNumber = LottoNumber.of(number)

        // then
        Truth.assertThat(actualNumber.number).isEqualTo(number)
    }
}
