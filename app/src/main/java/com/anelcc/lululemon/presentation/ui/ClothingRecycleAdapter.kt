package com.anelcc.lululemon.presentation.ui

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anelcc.lululemon.R
import com.anelcc.lululemon.databinding.ItemClothingBinding
import com.anelcc.lululemon.domain.Clothing
import java.util.*


class ClothingRecycleAdapter (private var clothingList: List<Clothing>) : RecyclerView.Adapter<ClothingRecycleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_clothing, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = clothingList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int = clothingList.size

    fun setData(newClothingList: List<Clothing>) {
        clothingList = newClothingList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemClothingBinding.bind(itemView)

        fun bind(clothing: Clothing) {
            binding.clothingName.text = clothing.name
            binding.clothingTime.text = getDate(clothing.date)
        }

        private fun getDate(timestamp: String): String {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = timestamp.toLong() * 1000L
            return DateFormat.format("dd-MM-yyyy hh:mm:ss", cal).toString()
        }
    }

    companion object {
        private val TAG = ClothingRecycleAdapter::class.simpleName
    }
}
