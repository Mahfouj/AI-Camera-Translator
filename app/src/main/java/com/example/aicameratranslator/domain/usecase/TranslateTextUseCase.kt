package com.example.aicameratranslator.domain.usecase

import com.example.aicameratranslator.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslateTextUseCase @Inject constructor(
    private val repository: TranslationRepository

) {

    suspend operator fun invoke(text: String, targetLang: String): Result<String> {
        return try {
            Result.success(repository.translateText(text, targetLang))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}