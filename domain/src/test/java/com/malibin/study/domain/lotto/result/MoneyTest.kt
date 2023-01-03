package com.malibin.study.domain.lotto.result

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MoneyTest : StringSpec({

    "음수인 돈은 존재할 수 없다" {
        val actualException = shouldThrow<IllegalArgumentException> { Money(-1) }
        actualException.message shouldBe "돈의 액수는 음수가 될 수 없습니다. 입력 값 : -1"
    }

    "Money를 + 연산자로 더할 수 있다"{
        Money(1_000) + Money(2_000) shouldBe Money(3_000)
    }

    "Money를 - 연산자로 뺄 수 있다"{
        Money(2_000) - Money(1_000) shouldBe Money(1_000)
    }
})
