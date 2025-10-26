package com.example.aicameratranslator.domain.repository

import com.example.aicameratranslator.data.api.Language
import com.example.aicameratranslator.data.model.TranslationResult

interface TranslationRepository {
    suspend fun translateText(text: String, targetLang: String): String
    suspend fun getHistory(): List<TranslationResult>
    suspend fun deleteTranslation(result: TranslationResult)
    suspend fun getSupportedLanguages(): List<Language>



}