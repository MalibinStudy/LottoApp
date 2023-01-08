package com.malibin.study.domain.lotto.ticket

import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class WinningTicketTest : StringSpec({

    "당첨 번호 6개에 보너스 번호가 포함될 수 없다" {
        // given
        val winningNumbers = LottoTicket(1, 2, 3, 4, 5, 6)
        val bonusNumber = LottoNumber.of(1)

        shouldThrowWithMessage<IllegalArgumentException>(
            "보너스 번호 (1)는 당첨번호(1, 2, 3, 4, 5, 6)와 중복될 수 없습니다."
        ) {
            // when, then
            WinningTicket(winningNumbers, bonusNumber)
        }
    }

    "당첨 티켓과 각 로또 티켓을 비교하면 로또 순위를 반환한다" {
        // given
        val winningTicket = WinningTicket(
            LottoTicket(1, 2, 3, 4, 5, 6),
            LottoNumber.of(7),
        )
        forAll(
            row(LottoTicket(1, 2, 3, 4, 5, 6), Prize.First),
            row(LottoTicket(1, 2, 3, 4, 5, 7), Prize.Second),
            row(LottoTicket(1, 2, 3, 4, 5, 13), Prize.Third),
            row(LottoTicket(1, 2, 3, 4, 12, 13), Prize.Fourth),
            row(LottoTicket(1, 2, 3, 11, 12, 13), Prize.Fifth),
            row(LottoTicket(1, 2, 10, 11, 12, 13), Prize.Lose),
            row(LottoTicket(1, 9, 10, 11, 12, 13), Prize.Lose),
            row(LottoTicket(8, 9, 10, 11, 12, 13), Prize.Lose),
        ) { lottoTicket, expectedPrize ->
            // when
            val actualPrize = winningTicket.compareWith(lottoTicket)

            // then
            actualPrize shouldBe expectedPrize
        }
    }

    "당첨 티켓과 로또 티켓뭉치를 비교하면 각 순위별 개수를 반환한다" {
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
        actualPrizes shouldBe expectedPrizes
    }
})
