package com.malibin.study.domain.lotto.ticket

import com.malibin.study.domain.lotto.LottoNumber
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class LottoTicketTest : StringSpec({

    "로또 번호가 6개가 아닌 티켓을 만들 수 없다" {
        // given
        listOf(
            intArrayOf(1, 2, 3, 4, 5),
            intArrayOf(1, 2, 3, 4, 5, 6, 7),
        ).forEach {
            // when, then
            shouldThrow<IllegalArgumentException> { LottoTicket(*it) }
        }
    }

    "로또 티켓에 특정 번호가 있는 지 확인할 수 있다" {
        // given
        val lottoTicket = LottoTicket(1, 2, 3, 4, 5, 6)
        val lottoNumber = LottoNumber.of(1)

        // when, then
        lottoTicket.has(lottoNumber) shouldBe true
    }

    "로또 티켓끼리 비교해서 같은 번호의 개수를 알아낼 수 있다" {
        // given
        val lottoTicket1 = LottoTicket(1, 2, 3, 4, 5, 6)
        val lottoTicket2 = LottoTicket(1, 2, 3, 7, 8, 9)

        // when, then
        lottoTicket1.countMatchingNumbers(lottoTicket2) shouldBe 3
    }
})
