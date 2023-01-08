package com.malibin.study.domain.lotto.ticket

import com.malibin.study.domain.lotto.LottoNumber

data class LottoTicket(
    private val lottoNumbers: Set<LottoNumber>,
) {
    init {
        require(lottoNumbers.size == LOTTO_NUMBERS_AMOUNT) { "로또 티켓에 번호는 6개만 넣을 수 있습니다. 입력 값 : $lottoNumbers" }
    }

    constructor(vararg numbers: Int) : this(numbers.map { LottoNumber.of(it) })

    constructor(numbers: List<LottoNumber>) : this(numbers.toSet())

    fun has(lottoNumber: LottoNumber): Boolean {
        return lottoNumbers.contains(lottoNumber)
    }

    fun countMatchingNumbers(otherLottoTicket: LottoTicket): Int {
        return lottoNumbers.count { otherLottoTicket.has(it) }
    }

    override fun toString(): String = lottoNumbers.joinToString(", ")

    companion object {
        private const val LOTTO_NUMBERS_AMOUNT = 6
    }
}
