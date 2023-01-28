package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.*

internal class LottoTicketTest {

    @Test
    fun `로또 번호는 6개가 주어 진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 4, 5, 6)

        // when
        val lottoTicket = LottoTicket(*lottoNumbers)

        // then
        assertThat(lottoTicket.lottoNumbers.count()).isEqualTo(6)
    }

    @Test
    fun `로또 번호가 중복으로 주어지면 예외처리를 던진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 3, 5, 6)

        // when
        val actualException = runCatching { LottoTicket(*lottoNumbers) }.exceptionOrNull()

        // then
        if (lottoNumbers.toSet().size != lottoNumbers.size) {
            assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Test
    fun `로또 번호의 개수가 6보다 크면 예외처리를 던진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 4, 5, 6, 7)

        // when
        val actualException = runCatching { LottoTicket(*lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun `로또 번호의 개수가 6보다 작으면 예외처리를 던진다`() {
        // given
        val lottoNumbers = intArrayOf(1, 2, 3, 4, 5)

        // when
        val actualException = runCatching { LottoTicket(*lottoNumbers) }.exceptionOrNull()

        // then
        assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java)
    }

    @ParameterizedTest
    @MethodSource("providesLottoNumbers")
    fun `로또 티켓에 일치 하는 번호가 존재 하는 지 확인할 수 있다`(lottoNumbers: IntArray) {
        // given
        val lottoTicket = LottoTicket(*lottoNumbers)
        val lottoNumber = LottoNumber.of(1)

        // then
        assertThat(lottoTicket.has(lottoNumber)).isTrue()
    }

    @ParameterizedTest
    @MethodSource("providesLottoNumbers")
    fun `로또 티켓에 일치 하는 번호가 하나도 없는 지 확인할 수 있다`(lottoNumbers: IntArray) {
        // given
        val lottoTicket = LottoTicket(*lottoNumbers)
        val lottoNumber = LottoNumber.of(7)

        // then
        assertThat(lottoTicket.has(lottoNumber)).isFalse()
    }

    @ParameterizedTest
    @MethodSource("providesOtherNumbers")
    fun `로또 티켓과 일치 하는 번호의 개수를 알 수 있다`(otherLottoNumber: IntArray, matchNumber: Int) {
        // given
        val lottoNumber = intArrayOf(1, 2, 3, 20, 30, 40)

        val lottoTicket = LottoTicket(*lottoNumber)
        val otherLottoTicket = LottoTicket(*otherLottoNumber)

        // when then
        assertThat(lottoTicket.countMatchingNumbers(otherLottoTicket)).isEqualTo(matchNumber)
    }

    companion object {
        @JvmStatic
        fun providesLottoNumbers(): List<Arguments> =
            listOf(
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(intArrayOf(1, 10, 20, 30, 40, 45)),
                Arguments.of(intArrayOf(1, 12, 23, 24, 35, 42))
            )

        @JvmStatic
        fun providesOtherNumbers(): List<Arguments> =
            listOf(
                Arguments.of(intArrayOf(1, 2, 3, 4, 5, 6), 3),
                Arguments.of(intArrayOf(1, 10, 20, 30, 40, 45), 4),
                Arguments.of(intArrayOf(1, 12, 23, 24, 35, 42), 1)
            )
    }


}