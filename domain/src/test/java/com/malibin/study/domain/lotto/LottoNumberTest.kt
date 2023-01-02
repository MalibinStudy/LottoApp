package com.malibin.study.domain.lotto

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class LottoNumberTest : StringSpec({
    "로또 번호 범위 이외의 숫자를 가져올 수 없다" {
        listOf(0, 46).forEach { invalidLottoNumber ->
            val actualException =
                shouldThrow<IllegalArgumentException> { LottoNumber.of(invalidLottoNumber) }
            actualException.message shouldBe "입력 숫자 ($invalidLottoNumber)는 로또 숫자 범위(1 ~ 45) 내의 숫자가 아닙니다."
        }
    }
})
