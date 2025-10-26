package com.example.aicameratranslator.domain.usecase

import com.example.aicameratranslator.data.model.TranslationResult
import com.example.aicameratranslator.domain.repository.TranslationRepository
import javax.inject.Inject

class GetTranslationHistoryUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(): Result<List<TranslationResult>> {
        return try {
            Result.success(repository.getHistory())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}