package com.example.haikupuzzle.setting

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.haikupuzzle.R
import com.example.haikupuzzle.data.HaikuModels
import kotlinx.android.synthetic.main.show_item.view.*

class ShowAdapter(
    private val context: Context,
    private val haikuModels: MutableList<HaikuModels>
) : RecyclerView.Adapter<ShowHolder>() {

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
}

class ShowHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(haikuModels: HaikuModels) {
        haikuModels.dailyHaiku.also {
            itemView.showFirstWord.text = it.firstWord
            itemView.showSecondWord.text = it.secondWord
            itemView.showThirdWord.text = it.thirdWord
        }
        haikuModels.haikuModel.also {
            itemView.showFirstHaiku.setText(it.firstHaiku)
            itemView.showSecondHaiku.setText(it.secondHaiku)
            itemView.showThirdHaiku.setText(it.thirdHaiku)
        }
        itemView.showCreatedDate.text = haikuModels.saveDate
        itemView.showCorrectCount.text = haikuModels.correctCount.toString()

        itemView.setOnClickListener {
            object : ShowAdapter.ItemClickListener {
                override fun onItemClick(view: View, position: Int) {
//TODO
                }
            }
        }
    }
}