package com.malibin.study.lotto

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class LottoTicketsListAdapter :
    ListAdapter<LottoNumbersItem, LottoTicketsListAdapter.ViewHolder>(ItemComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.item_lotto_ticket, parent, false)
            .let { ViewHolder(it) }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private class ItemComparator : DiffUtil.ItemCallback<LottoNumbersItem>() {
        override fun areItemsTheSame(oldItem: LottoNumbersItem, newItem: LottoNumbersItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: LottoNumbersItem, newItem: LottoNumbersItem): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(private val rootView: View) : RecyclerView.ViewHolder(rootView) {
        fun bind(lottoNumbers: LottoNumbersItem) {
            rootView.findViewById<TextView>(R.id.textFirstNumber).text = lottoNumbers.firstNumber.toString()
            rootView.findViewById<TextView>(R.id.textSecondNumber).text = lottoNumbers.secondNumber.toString()
            rootView.findViewById<TextView>(R.id.textThirdNumber).text = lottoNumbers.thirdNumber.toString()
            rootView.findViewById<TextView>(R.id.textFourthNumber).text = lottoNumbers.fourthNumber.toString()
            rootView.findViewById<TextView>(R.id.textFifthNumber).text = lottoNumbers.fifthNumber.toString()
            rootView.findViewById<TextView>(R.id.textSixthNumber).text = lottoNumbers.sixthNumber.toString()
        }
    }
}
