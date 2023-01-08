package com.malibin.study.domain.lotto.ticket

import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.result.Prize

class WinningTicket(
    private val winningNumbers: LottoTicket,
    private val bonusNumber: LottoNumber,
) {
    init {
        require(!winningNumbers.has(bonusNumber)) { "보너스 번호 ($bonusNumber)는 당첨번호($winningNumbers)와 중복될 수 없습니다." }
    }

    fun compareWith(otherTickets: List<LottoTicket>): Map<Prize, Int> {
        return otherTickets.map { compareWith(it) }
            .groupingBy { it }
            .eachCount()
    }

    fun compareWith(otherTicket: LottoTicket): Prize {
        val matchedNumberCount = otherTicket.countMatchingNumbers(winningNumbers)
        val hasBonusNumber = otherTicket.has(bonusNumber)
        return Prize.find(matchedNumberCount, hasBonusNumber)
    }
}
