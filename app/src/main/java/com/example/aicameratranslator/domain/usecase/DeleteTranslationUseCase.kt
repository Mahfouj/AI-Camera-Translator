package com.example.aicameratranslator.domain.usecase


import com.example.aicameratranslator.data.model.TranslationResult
import com.example.aicameratranslator.domain.repository.TranslationRepository
import javax.inject.Inject

class DeleteTranslationUseCase @Inject constructor(
    private val repository: TranslationRepository
) {
    suspend operator fun invoke(result: TranslationResult): Result<Unit> {
        return try {
            repository.deleteTranslation(result)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}