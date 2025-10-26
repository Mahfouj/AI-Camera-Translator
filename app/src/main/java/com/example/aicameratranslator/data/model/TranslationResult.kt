package com.example.aicameratranslator.data.model

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity (
    tableName = "translation_history",
    indices = [Index(value = ["originalText", "targetLang"])]
)
data class TranslationResult(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val originalText: String,
    val translatedText: String,
    val sourceLang: String,
    val targetLang: String,
    val timestamp: Long = System.currentTimeMillis()

)
