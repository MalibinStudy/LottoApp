package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class WinningTicketTest {

    @Test
    fun `WinningTicket을 생성할 때, LottoTicket과 LottoNumber는 중복될 수 없다`() {
        // given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(6)
        // when
        val exception = runCatching { WinningTicket(winningNumbers, bonusNumber) }.exceptionOrNull()
        // then
        assertAll({ assertThat(exception).isInstanceOf(IllegalArgumentException::class.java) }, {
            assertThat(exception).hasMessageThat().isEqualTo(
                "보너스 번호 ($bonusNumber)는 당첨번호($winningNumbers)와 중복될 수 없습니다."
            )
        })
    }

    @Test
    fun `compareWith() 을 통해 LottoTicket과 일치하는 Prize를 얻을 수 있다`() {
        // given
        val winningTicket = WinningTicket(LottoTicket(1, 2, 3, 4, 5, 6), LottoNumber.of(7))
        val otherTicket = LottoTicket(1, 2, 3, 4, 5, 7)
        val expectedPrize = Prize.Second
        // when
        val actualPrize = winningTicket.compareWith(otherTicket)
        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    @ParameterizedTest
    @MethodSource("provideLottoTicketsAndPrize")
    fun `compareWith() 을 통해 LottoTicket과 일치하는 Prize를 얻을 수 있다`(
        lottoTicket: LottoTicket,
        expectedPrize: Prize
    ) {
        // given
        val winningTicket = WinningTicket(LottoTicket(1, 2, 3, 4, 5, 6), LottoNumber.of(7))
        // when
        val actualPrize = winningTicket.compareWith(lottoTicket)
        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    @Test
    fun `compareWith() 을 통해 LottoTicket들에 해당하는 Prize 목록을 얻을 수 있다`() {
        // given
        val winningTicket = WinningTicket(LottoTicket(1, 2, 3, 4, 5, 6), LottoNumber.of(7))
        val lottoTickets = listOf(
            LottoTicket(1, 2, 3, 4, 5, 6), // 1등
            LottoTicket(1, 2, 3, 4, 5, 6), // 1등
            LottoTicket(1, 2, 3, 4, 5, 7), // 2등
            LottoTicket(1, 2, 3, 4, 5, 7), // 2등
            LottoTicket(1, 2, 3, 4, 5, 8), // 3등
            LottoTicket(1, 2, 3, 4, 8, 9), // 4등
            LottoTicket(1, 2, 3, 8, 9, 10), // 5등
            LottoTicket(1, 2, 7, 8, 9, 11) // 꽝
        )
        val expectedPrizes = mapOf(
            Prize.First to 2,
            Prize.Second to 2,
            Prize.Third to 1,
            Prize.Fourth to 1,
            Prize.Fifth to 1,
            Prize.Lose to 1
        )
        // when
        val actualPrize = winningTicket.compareWith(lottoTickets)
        // then
        assertThat(actualPrize).isEqualTo(expectedPrizes)
    }

    companion object {
        @JvmStatic
        fun provideLottoTicketsAndPrize(): List<Arguments> {
            return listOf(
                Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), Prize.First), // 1등
                Arguments.of(LottoTicket(1, 2, 3, 4, 5, 7), Prize.Second), // 2등
                Arguments.of(LottoTicket(1, 2, 3, 4, 5, 8), Prize.Third), // 3등
                Arguments.of(LottoTicket(1, 2, 3, 4, 8, 9), Prize.Fourth), // 4등
                Arguments.of(LottoTicket(1, 2, 3, 8, 9, 10), Prize.Fifth), // 5등
                Arguments.of(LottoTicket(1, 2, 7, 8, 9, 11), Prize.Lose) // 꽝
            )
        }
    }
}
