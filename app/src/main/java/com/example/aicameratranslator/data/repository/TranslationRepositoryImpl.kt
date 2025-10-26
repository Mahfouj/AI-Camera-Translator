package com.example.aicameratranslator.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.aicameratranslator.data.api.Language
import com.example.aicameratranslator.data.api.TranslationApiService
import com.example.aicameratranslator.data.local.TranslationDao
import com.example.aicameratranslator.data.model.TranslationResult
import com.example.aicameratranslator.domain.repository.TranslationRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val apiService: TranslationApiService,
    private val dao: TranslationDao,
    @ApplicationContext private val context: Context
) : TranslationRepository {

    override suspend fun translateText(
        text: String,
        targetLang: String
    ): String = withContext(Dispatchers.IO) {
        if (isNetworkAvailable()) {
            try {
                val response = apiService.translateText(text = text, target = targetLang)
                val result = TranslationResult(
                    originalText = text,
                    translatedText = response.translatedText,
                    sourceLang = "auto",
                    targetLang = targetLang
                )
                dao.insert(result)
                response.translatedText
            } catch (e: Exception) {
                // wrap as IOException so callers can distinguish I/O/network related failures
                throw IOException("Translation failed: ${e.message}", e)
            }
        } else {
            // Efficient DB lookup (no full-table scan)
            val cached = dao.findByTextAndLang(text, targetLang)
            cached?.translatedText ?: throw IOException("No cached translation available for this text and language")
        }
    }

    override suspend fun getHistory(): List<TranslationResult> = withContext(Dispatchers.IO) {
        dao.getAll()
    }

    override suspend fun deleteTranslation(result: TranslationResult) = withContext(Dispatchers.IO) {
        dao.delete(result)
    }

    override suspend fun getSupportedLanguages(): List<Language> = withContext(Dispatchers.IO) {
        try {
            apiService.getSupportedLanguages()
        } catch (e: Exception) {
            throw IOException("Failed to fetch languages: ${e.message}", e)
        }
    }

    private fun isNetworkAvailable(): Boolean {
        // Use modern API; avoids the older deprecated casts
        val connectivityManager = context.getSystemService(ConnectivityManager::class.java) ?: return false
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
    }
}