package com.malibin.study.domain.lotto.result

@JvmInline
value class Money(val amount: Long) {
    init {
        require(amount >= 0) { "돈의 액수는 음수가 될 수 없습니다. 입력 값 : $amount" }
    }

    operator fun plus(otherMoney: Money): Money = Money(this.amount + otherMoney.amount)

    operator fun minus(otherMoney: Money): Money = Money(this.amount - otherMoney.amount)
}
