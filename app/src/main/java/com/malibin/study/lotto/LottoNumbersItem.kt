package com.malibin.study.lotto

import com.malibin.study.domain.lotto.LottoNumber

data class LottoNumbersItem(
    val firstNumber: Int,
    val secondNumber: Int,
    val thirdNumber: Int,
    val fourthNumber: Int,
    val fifthNumber: Int,
    val sixthNumber: Int,
)

fun Set<LottoNumber>.toUiModel(): LottoNumbersItem = this.map { it.number }
    .let {
        LottoNumbersItem(
            firstNumber = it[0],
            secondNumber = it[1],
            thirdNumber = it[2],
            fourthNumber = it[3],
            fifthNumber = it[4],
            sixthNumber = it[5],
        )
    }
