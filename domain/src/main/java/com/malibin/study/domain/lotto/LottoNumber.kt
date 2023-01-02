package com.malibin.study.domain.lotto

@JvmInline
value class LottoNumber private constructor(
    private val number: Int,
) {
    init {
        require(number in MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER) { "입력 숫자 ($number)는 로또 숫자 범위(1 ~ 45) 내의 숫자가 아닙니다." }
    }

    companion object {
        private const val MIN_LOTTO_NUMBER = 1
        private const val MAX_LOTTO_NUMBER = 45

        private val cachedLottoNumbers: Map<Int, LottoNumber> =
            (MIN_LOTTO_NUMBER..MAX_LOTTO_NUMBER).associateWith { LottoNumber(it) }

        @JvmStatic
        fun of(number: Int): LottoNumber {
            return cachedLottoNumbers[number]
                ?: throw IllegalArgumentException("입력 숫자 ($number)는 로또 숫자 범위(1 ~ 45) 내의 숫자가 아닙니다.")
        }
    }
}
