package com.example.aicameratranslator.domain.usecase

import com.example.aicameratranslator.data.api.Language
import com.example.aicameratranslator.domain.repository.TranslationRepository
import javax.inject.Inject

class GetSupportedLanguagesUseCase @Inject constructor(

    private val repository: TranslationRepository
) {

    suspend operator fun invoke(): Result<List<Language>> {
        return try {
            Result.success(repository.getSupportedLanguages())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }



}