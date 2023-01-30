package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class WinningTicketTest {
    @Test
    fun `당첨 번호 안에는 보너스 번호가 존재할 수 없다`() {
        // given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(6)

        // when
        val actualException = runCatching { WinningTicket(winningNumbers, bonusNumber) }
            .exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("보너스 번호 (6)는 당첨번호(1, 2, 3, 4, 5, 6)와 중복될 수 없습니다.")
            }
        )
    }

    @MethodSource("providesTicketsAndResult")
    @ParameterizedTest
    fun `로또 티켓의 당첨 결과를 알 수 있다`(lottoTicket: LottoTicket, expectedPrize: Prize) {
        // given
        val winningTicket = WinningTicket(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoNumber.of(10),
        )
        // when
        val actualPrize = winningTicket.compareWith(lottoTicket)

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    @Test
    fun `로또 티켓들 각각의 당첨 결과를 알 수 있다`() {
        // given
        val winningTicket = WinningTicket(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoNumber.of(10),
        )

        val otherTickets: List<LottoTicket> = listOf(
            LottoTicket(1, 2, 3, 4, 5, 6), // 1 등
            LottoTicket(1, 2, 3, 4, 5, 10), // 2 등
            LottoTicket(1, 2, 3, 4, 5, 10),  // 2 등
            LottoTicket(1, 2, 3, 4, 15, 20), // 4등
            LottoTicket(1, 2, 3, 15, 20, 25), // 5등
            LottoTicket(1, 2, 15, 20, 25, 30), // 낙첨
            LottoTicket(1, 15, 20, 25, 30, 35), // 낙첨
            LottoTicket(15, 20, 25, 30, 35, 40) // 낙첨
        )

        // when
        val actualCount = winningTicket.compareWith(otherTickets)
        val actualCounts: List<Int?> = listOf(
            actualCount[Prize.First] ?: 0,
            actualCount[Prize.Second] ?: 0,
            actualCount[Prize.Third] ?: 0,
            actualCount[Prize.Fourth] ?: 0,
            actualCount[Prize.Fifth] ?: 0,
            actualCount[Prize.Lose] ?: 0
        )

        // then
        assertThat(actualCounts).isEqualTo(listOf(1, 2, 0, 1, 1, 3))
    }

    companion object {
        @JvmStatic
        private fun providesTicketsAndResult() = arrayOf(
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), Prize.First),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 10), Prize.Second),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 15), Prize.Third),
            Arguments.of(LottoTicket(1, 2, 3, 4, 15, 20), Prize.Fourth),
            Arguments.of(LottoTicket(1, 2, 3, 15, 20, 25), Prize.Fifth),
            Arguments.of(LottoTicket(1, 2, 15, 20, 25, 30), Prize.Lose),
            Arguments.of(LottoTicket(1, 15, 20, 25, 30, 35), Prize.Lose),
            Arguments.of(LottoTicket(15, 20, 25, 30, 35, 40), Prize.Lose),
        )
    }


}