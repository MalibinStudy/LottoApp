package com.malibin.study.domain.lotto.result

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class PrizeTest : StringSpec({

    "맞은 로또 번호 개수와 맞은 보너스 숫자 여부에 따른 결과를 찾을 수 있다"{
        forAll(
            row(6, false, Prize.First),
            row(5, true, Prize.Second),
            row(5, false, Prize.Third),
            row(4, false, Prize.Fourth),
            row(3, false, Prize.Fifth),
            row(2, false, Prize.Lose),
            row(1, false, Prize.Lose),
            row(0, false, Prize.Lose),
        ) { matchedNumberCount, isBonusNumberMatched, expectedPrize ->
            val actualPrize = Prize.find(matchedNumberCount, isBonusNumberMatched)
            actualPrize shouldBe expectedPrize
        }
    }
})
