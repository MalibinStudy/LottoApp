package com.malibin.study.domain.lotto.generator

import com.malibin.study.domain.lotto.LottoNumber

fun interface GeneratingLottoNumbersStrategy {
    fun generate(): Set<LottoNumber>
}
