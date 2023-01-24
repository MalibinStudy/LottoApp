package com.malibin.study.lotto

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.malibin.study.domain.lotto.LottoNumber
import com.malibin.study.domain.lotto.generator.LottoTicketGenerator
import com.malibin.study.domain.lotto.result.Prize
import com.malibin.study.domain.lotto.ticket.LottoTicket
import com.malibin.study.domain.lotto.ticket.WinningTicket
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val lottoTicketGenerator: LottoTicketGenerator = LottoTicketGenerator()

    private val autoLottoTickets: MutableList<LottoTicket> = mutableListOf()
    private val manualLottoTickets: MutableList<LottoTicket> = mutableListOf()
    private var winningLottoTicket: WinningTicket? = null

    private val autoLottoTicketsListAdapter: LottoTicketsListAdapter = LottoTicketsListAdapter()
    private val manualLottoTicketsListAdapter: LottoTicketsListAdapter = LottoTicketsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<RecyclerView>(R.id.itemsAutoLottoTickets).adapter = autoLottoTicketsListAdapter

        findViewById<Button>(R.id.buttonCreateAutoLottoTicket).setOnClickListener {
            val newAutoTicket = lottoTicketGenerator.createAutoTicket()
            autoLottoTickets.add(newAutoTicket)
            autoLottoTicketsListAdapter.submitList(autoLottoTickets.map { it.toUiModel() })
        }

        findViewById<Button>(R.id.buttonClearManualLottoNumbers).setOnClickListener {
            findViewById<EditText>(R.id.textManualLottoNumbers).text.clear()
        }

        findViewById<Button>(R.id.buttonCreateManualLottoTicket).setOnClickListener {
            runCatching { createManualTicket() }
                .onSuccess { newManualTicket ->
                    manualLottoTickets.add(newManualTicket)
                    manualLottoTicketsListAdapter.submitList(manualLottoTickets.map { it.toUiModel() })
                }
                .onFailure { showToast("수동 티켓의 형식을 맞춰주세요") }
        }

        findViewById<RecyclerView>(R.id.itemsManualLottoTickets).adapter =
            manualLottoTicketsListAdapter

        findViewById<Button>(R.id.buttonClearWinningLottoNumbers).setOnClickListener {
            findViewById<Button>(R.id.buttonCreateWinningLottoTicket).visibility = View.VISIBLE
            findViewById<TextView>(R.id.textWinningLottoTicket).visibility = View.GONE
            findViewById<EditText>(R.id.textWinningLottoNumbers).also {
                it.text.clear()
                it.visibility = View.VISIBLE
            }
            findViewById<TextView>(R.id.textResults).text = ""
            findViewById<TextView>(R.id.textTotalPrizeMoney).text = ""
        }

        findViewById<Button>(R.id.buttonCreateWinningLottoTicket).setOnClickListener {
            runCatching { createWinningTicket() }
                .onSuccess { newWinningTicket ->
                    winningLottoTicket = newWinningTicket
                    findViewById<Button>(R.id.buttonCreateWinningLottoTicket).visibility = View.GONE
                    findViewById<EditText>(R.id.textWinningLottoNumbers).visibility = View.GONE
                    findViewById<TextView>(R.id.textWinningLottoTicket).also {
                        it.visibility = View.VISIBLE
                        it.text = newWinningTicket.toString()
                    }
                    val lottoResult = createLottoResult()
                    findViewById<TextView>(R.id.textResults).text = lottoResult.toResultText()
                    findViewById<TextView>(R.id.textTotalPrizeMoney).text =
                        lottoResult.calculateTotalPrizeText()
                }
                .onFailure { showToast("당첨 티켓의 형식을 맞춰주세요") }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun createManualTicket(): LottoTicket {
        return findViewById<EditText>(R.id.textManualLottoNumbers).text
            .split(" ")
            .map { it.toInt() }
            .toSet()
            .let { lottoTicketGenerator.createManualTicket(it) }
    }

    private fun createWinningTicket(): WinningTicket {
        val winningLottoNumbers = findViewById<EditText>(R.id.textWinningLottoNumbers).text
            .split(" ")
            .map { it.toInt() }
        val bonusNumber = winningLottoNumbers.last()
        val matched6Numbers = (winningLottoNumbers - bonusNumber).toSet()
        return WinningTicket(
            winningNumbers = lottoTicketGenerator.createManualTicket(matched6Numbers),
            bonusNumber = LottoNumber.of(bonusNumber),
        )
    }

    private fun createLottoResult(): Map<Prize, Int> {
        return winningLottoTicket?.compareWith(autoLottoTickets + manualLottoTickets).orEmpty()
    }

    private fun Map<Prize, Int>.toResultText(): String {
        return this.entries.joinToString(separator = "\n") { "${it.key} :\t${it.value} 개" }
    }

    private fun Map<Prize, Int>.calculateTotalPrizeText(): String {
        return this.entries
            .sumOf { it.key.amount.amount * it.value }
            .let { DecimalFormat("#,###").format(it) }
    }
}
