package com.malibin.study.domain.lotto.generator

import com.malibin.study.domain.lotto.LottoNumber

class RandomLottoNumbersStrategy : GeneratingLottoNumbersStrategy {
    override fun generate(): Set<LottoNumber> {
        return LottoNumber.RANGE.shuffled()
            .take(6)
            .map { LottoNumber.of(it) }
            .toSet()
    }
}
