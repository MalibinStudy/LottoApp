package com.malibin.study.domain.lotto.generator

import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.ticket.LottoTicket

class LottoTicketGenerator(
    private val generatingLottoNumbersStrategy: GeneratingLottoNumbersStrategy = RandomLottoNumbersStrategy(),
) {
    fun createAutoTicket(): LottoTicket {
        return LottoTicket(generatingLottoNumbersStrategy.generate().sortedBy { it.number })
    }

    fun createManualTicket(numbers: Set<Int>): LottoTicket {
        return LottoTicket(numbers.map { LottoNumber.of(it) })
    }
}
