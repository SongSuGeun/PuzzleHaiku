package com.example.haikupuzzle.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.haikupuzzle.R
import com.example.haikupuzzle.data.HaikuModels
import kotlinx.android.synthetic.main.show_item.view.*

class ShowAdapter(
    private val context: Context,
    private val haikuModels: MutableList<HaikuModels>,
    private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<ShowAdapter.ShowHolder>() {

    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.show_item, parent, false)
        return ShowHolder(view)
    }

    override fun getItemCount(): Int = haikuModels.size

    override fun onBindViewHolder(holder: ShowHolder, position: Int) {
        holder.itemView.tag = position
        holder.bind(haikuModels[position])
    }

    inner class ShowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(haikuModels: HaikuModels) {
            haikuModels.dailyHaiku.also {
                itemView.showFirstWord.text = it.firstWord
                itemView.showSecondWord.text = it.secondWord
                itemView.showThirdWord.text = it.thirdWord
            }
            haikuModels.haikuModel.also {
                itemView.showFirstHaiku.text = it.firstHaiku
                itemView.showSecondHaiku.text = it.secondHaiku
                itemView.showThirdHaiku.text = it.thirdHaiku
            }
            itemView.showCreatedDate.text = haikuModels.saveDate
            itemView.showCorrectCount.text =
                context.getString(R.string.created_at_count, haikuModels.correctCount)

            itemView.removeHaikuButton.setOnClickListener {
                itemClickListener.onItemClick(itemView, adapterPosition)
            }
        }
    }
}