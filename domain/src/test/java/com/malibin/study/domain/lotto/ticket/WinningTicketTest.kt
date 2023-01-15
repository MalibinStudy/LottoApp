package com.malibin.study.domain.lotto.ticket

import com.google.common.truth.Truth.assertThat
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class WinningTicketTest {

    @Test
    fun `당첨 번호 6개에 보너스 번호가 포함될 수 없다`() {
        // given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(1)

        // when
        val actualException = runCatching { WinningTicket(winningNumbers, bonusNumber) }
            .exceptionOrNull()

        // then
        assertAll(
            { assertThat(actualException).isInstanceOf(IllegalArgumentException::class.java) },
            {
                assertThat(actualException).hasMessageThat()
                    .contains("보너스 번호 (1)는 당첨번호(1, 2, 3, 4, 5, 6)와 중복될 수 없습니다.")
            }
        )
    }

    @MethodSource("providesTicketsAndPrizes")
    @ParameterizedTest
    fun `당첨 티켓과 각 로또 티켓을 비교하면 로또 순위를 반환한다`(lottoTicket: LottoTicket, expectedPrize: Prize) {
        // given
        val winningTicket = WinningTicket(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoNumber.of(7),
        )
        // when
        val actualPrize = winningTicket.compareWith(lottoTicket)

        // then
        assertThat(actualPrize).isEqualTo(expectedPrize)
    }

    @Test
    fun `당첨 티켓과 로또 티켓뭉치를 비교하면 각 순위별 개수를 반환한다`() {
        // given
        val winningTicket = WinningTicket(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoNumber.of(7),
        )
        val lottoTickets = listOf(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoTicket(1, 2, 3, 4, 5, 7),
        )
        val expectedPrizes = mapOf(
            Prize.First to 1,
            Prize.Second to 1,
        )

        // when
        val actualPrizes = winningTicket.compareWith(lottoTickets)

        // then
        assertThat(actualPrizes).isEqualTo(expectedPrizes)
    }

    companion object {
        @JvmStatic
        private fun providesTicketsAndPrizes() = arrayOf(
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 6), Prize.First),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 7), Prize.Second),
            Arguments.of(LottoTicket(1, 2, 3, 4, 5, 13), Prize.Third),
            Arguments.of(LottoTicket(1, 2, 3, 4, 12, 13), Prize.Fourth),
            Arguments.of(LottoTicket(1, 2, 3, 11, 12, 13), Prize.Fifth),
            Arguments.of(LottoTicket(1, 2, 10, 11, 12, 13), Prize.Lose),
            Arguments.of(LottoTicket(1, 9, 10, 11, 12, 13), Prize.Lose),
            Arguments.of(LottoTicket(8, 9, 10, 11, 12, 13), Prize.Lose),
        )
    }
}
