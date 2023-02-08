package com.malibin.study.domain.lotto

import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class LottoNumberTest {

    @ValueSource(ints = [0, 46])
    @ParameterizedTest
    fun `1~45가 아닐 때 에러가 발생하는지에 대한 테스트`(number: Int) {
        // when
        val exception = kotlin.runCatching { LottoNumber.of(number) }.exceptionOrNull()

        // then
        assertThat(exception).isInstanceOf(Exception::class.java)
        assertThat(exception).hasMessageThat().contains("돈의 액수는 음수가 될 수 없습니다. 입력 값 : $number")
    }

    @ValueSource(ints = [1, 2, 3])
    @ParameterizedTest
    fun `of 함수 테스트`(number: Int) {
        // when
        val lottoNumber = LottoNumber.of(number)

        // then
        assertThat(lottoNumber.number).isEqualTo(number)
    }
}
