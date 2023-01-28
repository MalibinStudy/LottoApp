package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*

internal class LottoTicketTest {

    @ParameterizedTest
    @MethodSource("providesInvalidLottoNumbers")
    fun `유효하지 않은 숫자들로 LottoTicket 을 생성할 수 없다`(lottoNumbers: IntArray) {
        // when
        val actualException = runCatching { LottoTicket(*lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @MethodSource("providesLottoNumbers")
    fun `로또 티켓과 일치 하는 번호의 존재 여부를 확인할 수 있다`(number: Int, isSame: Boolean) {
        // given
        val lottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)
        val lottoNumber = LottoNumber.of(number)

        // then
        assertThat(lottoTicket.has(lottoNumber) == isSame).isTrue()
    }

    @ParameterizedTest
    @MethodSource("providesOtherNumbers")
    fun `로또 티켓과 일치 하는 번호의 개수를 알 수 있다`(otherLottoNumber: IntArray, matchNumber: Int) {
        // given
        val lottoNumber = intArrayOf(1, 2, 3, 4, 5, 6)

        val lottoTicket = LottoTicket(*lottoNumber)
        val otherLottoTicket = LottoTicket(*otherLottoNumber)

        // when then
        assertThat(lottoTicket.countMatchingNumbers(otherLottoTicket)).isEqualTo(matchNumber)
    }

    companion object {
        @JvmStatic
        fun providesInvalidLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of(intArrayOf(1, 2, 2, 3, 4, 5)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5)),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6, 7))
            )

        @JvmStatic
        fun providesLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of(1, true),
                Arguments.of(7, false),
            )

        @JvmStatic
        fun providesOtherNumbers(): List<Arguments> =
            listOf(
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 6),
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 7), 5),
                Arguments.of(intArrayOf(1, 2, 3, 4, 7, 8), 4),
                Arguments.of(intArrayOf(1, 2, 3, 7, 8, 9), 3),
                Arguments.of(intArrayOf(1, 2, 7, 8, 9, 10), 2),
                Arguments.of(intArrayOf(1, 7, 8, 9, 10, 11), 1),
                Arguments.of(intArrayOf(7, 8, 9, 10, 11, 12), 0),
            )
    }

}