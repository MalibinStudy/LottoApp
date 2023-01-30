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
}
