package com.example.aicameratranslator.presentation.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.aicameratranslator.data.model.TranslationResult
import com.example.aicameratranslator.databinding.ItemHistoryBinding

class HistoryAdapter(
    private val onDelete: (TranslationResult) -> Unit
) : ListAdapter<TranslationResult, HistoryAdapter.VH>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val b = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VH(b)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    inner class VH(private val b: ItemHistoryBinding) : RecyclerView.ViewHolder(b.root) {
        fun bind(item: TranslationResult) {
            b.tvOriginal.text = item.originalText
            b.tvTranslated.text = item.translatedText
            b.btnDelete.setOnClickListener { onDelete(item) }
        }
    }

    private class DiffCallback : DiffUtil.ItemCallback<TranslationResult>() {
        override fun areItemsTheSame(oldItem: TranslationResult, newItem: TranslationResult) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: TranslationResult, newItem: TranslationResult) = oldItem == newItem
    }
}
